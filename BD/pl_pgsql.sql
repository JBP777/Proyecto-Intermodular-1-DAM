-- ============================================================
-- VISTAS
-- ============================================================

-- Muestra cada incidencia con su zona y categorías concatenadas
CREATE OR REPLACE VIEW vista_incidencias_con_zona_categoria AS
SELECT 
    i.id,
    i.estado,
    i.titulo,
    i.descripcion,
    i.fecha_creacion,
    i.reportador,
    z.nombre AS nombre_zona,
    STRING_AGG(c.nombre, ', ') AS categorias
FROM INCIDENCIA i, ZONA z, CLASIFICAR cl, CATEGORIA c
WHERE i.zona = z.id
  AND cl.incidencia = i.id
  AND cl.categoria = c.id
GROUP BY i.id, i.estado, i.titulo, i.descripcion, i.fecha_creacion, i.reportador, z.nombre;


-- Ranking de colaboradores ordenado por incidencias resueltas
CREATE OR REPLACE VIEW vista_top_colaboradores AS
SELECT
    u.nombre_usuario AS usuario,
    u.email,
    c.total_resueltas AS incidencias_resueltas,
    c.valoracion_media
FROM COLABORADOR c, USUARIO u
WHERE c.usuario = u.nombre_usuario
ORDER BY c.total_resueltas DESC;


-- ============================================================
-- FUNCIONES
-- ============================================================

-- Devuelve el total de incidencias creadas por un usuario
-- USO: SELECT fn_total_incidencias_reportador('JesusBP');
CREATE OR REPLACE FUNCTION fn_total_incidencias_reportador(p_usuario VARCHAR)
RETURNS NUMERIC AS $$
DECLARE
    v_total NUMERIC;
BEGIN
    SELECT COUNT(*) INTO v_total
    FROM INCIDENCIA
    WHERE reportador = p_usuario;

    RETURN v_total;
END;
$$ LANGUAGE plpgsql;


-- Devuelve cuántas incidencias abiertas hay en una zona dada
-- USO: SELECT fn_incidencias_abiertas_por_zona('Valencia');
CREATE OR REPLACE FUNCTION fn_incidencias_abiertas_por_zona(p_zona VARCHAR)
RETURNS NUMERIC AS $$
DECLARE
    v_total NUMERIC;
BEGIN
    SELECT COUNT(*) INTO v_total
    FROM INCIDENCIA i, ZONA z
    WHERE z.id = i.zona
      AND z.nombre = p_zona
      AND i.estado = 'Abierta';

    RETURN COALESCE(v_total, 0);
END;
$$ LANGUAGE plpgsql;


-- Inserta una incidencia y su clasificación a partir de nombres (zona y categoría)
CREATE OR REPLACE FUNCTION insertar_incidencia(
    p_titulo       VARCHAR,
    p_descripcion  VARCHAR,
    p_reportador   VARCHAR,
    p_nombre_zona  VARCHAR,
    p_nombre_cat   VARCHAR
) RETURNS VOID AS $$
DECLARE
    v_id_incidencia NUMERIC;
    v_id_zona       NUMERIC;
    v_id_categoria  NUMERIC;
BEGIN
    SELECT id INTO v_id_zona      FROM ZONA      WHERE nombre = p_nombre_zona;
    SELECT id INTO v_id_categoria FROM CATEGORIA WHERE nombre = p_nombre_cat;
    SELECT COALESCE(MAX(id), 0) + 1 INTO v_id_incidencia FROM INCIDENCIA;

    INSERT INTO INCIDENCIA (id, estado, titulo, descripcion, fecha_creacion, reportador, zona)
    VALUES (v_id_incidencia, 'Abierta', p_titulo, p_descripcion, CURRENT_DATE, p_reportador, v_id_zona);

    INSERT INTO CLASIFICAR (incidencia, categoria)
    VALUES (v_id_incidencia, v_id_categoria);
END;
$$ LANGUAGE plpgsql;


-- Recorre todos los colaboradores con un cursor y recalcula su valoracion_media
-- en escala 0-5 según las entradas en VALORAR respecto a sus resueltas.
-- USO: SELECT fn_recalcular_valoraciones();
CREATE OR REPLACE FUNCTION fn_recalcular_valoraciones()
RETURNS VOID AS $$
DECLARE
    cur_colaboradores CURSOR FOR
        SELECT usuario FROM COLABORADOR;

    v_usuario   COLABORADOR.usuario%TYPE;
    v_total     NUMERIC;
    v_resueltas NUMERIC;  -- ← declarada
    v_media     NUMERIC;
BEGIN
    OPEN cur_colaboradores;

    LOOP
        FETCH cur_colaboradores INTO v_usuario;
        EXIT WHEN NOT FOUND;

        SELECT COUNT(*) INTO v_total
        FROM RESOLVER r, VALORAR v
        WHERE r.incidencia = v.incidencia
          AND r.solucion = v.solucion
          AND r.colaborador = v_usuario;

        SELECT total_resueltas INTO v_resueltas  -- ← rellenada
        FROM COLABORADOR
        WHERE usuario = v_usuario;

        IF v_total > 0 AND v_resueltas > 0 THEN
            v_media := ROUND((v_total * 5.0) / v_resueltas, 2);
        ELSE
            v_media := 0.00;
        END IF;

        UPDATE COLABORADOR
        SET valoracion_media = LEAST(v_media, 5.00)
        WHERE usuario = v_usuario;

    END LOOP;

    CLOSE cur_colaboradores;
END;
$$ LANGUAGE plpgsql;


-- ============================================================
-- FUNCIONES DE TRIGGER
-- ============================================================

-- Impide eliminar una incidencia que está 'En progreso'
CREATE OR REPLACE FUNCTION fn_check_borrado_incidencia()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.estado = 'En progreso' THEN
        RAISE EXCEPTION
            'No se puede eliminar la incidencia "%" porque está En progreso. Ciérrala primero.',
            OLD.titulo;
    END IF;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;


-- Al crear un usuario, lo registra automáticamente como colaborador y reportador
CREATE OR REPLACE FUNCTION fn_auto_registrar_roles()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO COLABORADOR (usuario, valoracion_media, total_resueltas)
    VALUES (NEW.nombre_usuario, 0.00, 0);

    INSERT INTO REPORTADOR (usuario, total_creadas)
    VALUES (NEW.nombre_usuario, 0);

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;


-- Mantiene total_resueltas del colaborador ante INSERT, UPDATE y DELETE en RESOLVER
CREATE OR REPLACE FUNCTION fn_gestionar_total_resueltas()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        UPDATE COLABORADOR SET total_resueltas = total_resueltas + 1 WHERE usuario = NEW.colaborador;

    ELSIF TG_OP = 'DELETE' THEN
        UPDATE COLABORADOR SET total_resueltas = total_resueltas - 1 WHERE usuario = OLD.colaborador;

    ELSIF TG_OP = 'UPDATE' THEN
        IF OLD.colaborador IS DISTINCT FROM NEW.colaborador THEN
            UPDATE COLABORADOR SET total_resueltas = total_resueltas - 1 WHERE usuario = OLD.colaborador;
            UPDATE COLABORADOR SET total_resueltas = total_resueltas + 1 WHERE usuario = NEW.colaborador;
        END IF;
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;


-- Impide que un colaborador resuelva una incidencia que él mismo reportó
CREATE OR REPLACE FUNCTION fn_check_colaborador_no_es_reportador()
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM INCIDENCIA i
        WHERE i.id = NEW.incidencia
          AND i.reportador = NEW.colaborador
    ) THEN
        RAISE EXCEPTION
            'El colaborador "%" no puede resolver su propia incidencia (id: %).',
            NEW.colaborador, NEW.incidencia;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- ============================================================
-- TRIGGERS
-- ============================================================

-- Bloquea el borrado de incidencias en estado 'En progreso'
CREATE OR REPLACE TRIGGER trg_no_borrar_en_progreso
BEFORE DELETE ON INCIDENCIA
FOR EACH ROW
EXECUTE FUNCTION fn_check_borrado_incidencia();


-- Registra roles de colaborador y reportador al insertar un nuevo usuario
CREATE OR REPLACE TRIGGER trg_nuevo_usuario_roles
AFTER INSERT ON USUARIO
FOR EACH ROW
EXECUTE FUNCTION fn_auto_registrar_roles();


-- Actualiza el contador de resueltas al modificar asignaciones en RESOLVER
CREATE OR REPLACE TRIGGER trg_resolver_gestiona_contador
AFTER INSERT OR UPDATE OR DELETE ON RESOLVER
FOR EACH ROW
EXECUTE FUNCTION fn_gestionar_total_resueltas();


-- Evita que un colaborador se asigne a resolver su propia incidencia
CREATE OR REPLACE TRIGGER trg_colaborador_no_resuelve_su_incidencia
BEFORE INSERT ON RESOLVER
FOR EACH ROW
EXECUTE FUNCTION fn_check_colaborador_no_es_reportador();