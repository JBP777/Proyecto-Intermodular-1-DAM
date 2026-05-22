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

    -- Suma +1 al contador de incidencias creadas del reportador
    UPDATE REPORTADOR
    SET total_creadas = total_creadas + 1
    WHERE usuario = p_reportador;

    INSERT INTO CLASIFICAR (incidencia, categoria)
    VALUES (v_id_incidencia, v_id_categoria);
END;
$$ LANGUAGE plpgsql;