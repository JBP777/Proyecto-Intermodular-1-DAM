
-- Vista 1: ranking de usuarios con más incidencias resueltas;

CREATE OR REPLACE VIEW VW_RANKING_USUARIOS_RESUELTAS AS
SELECT 
    c.usuario,
    u.email,
    COUNT(*) AS incidencias_resueltas
FROM COLABORADOR c, USUARIO u, RESOLVER r, SOLUCION s
WHERE c.usuario = u.nombre_usuario
  AND c.usuario = r.colaborador
  AND r.solucion = s.id
  AND s.es_aceptada = TRUE
GROUP BY c.usuario, u.email
ORDER BY incidencias_resueltas;

SELECT * FROM VW_RANKING_USUARIOS_RESUELTAS;

--Vista 2: detalle de incidencias con reportador, zona, categoría y recompensa

CREATE OR REPLACE VIEW VW_DETALLE_INCIDENCIAS AS
SELECT
    i.id AS id_incidencia,
    i.titulo,
    i.estado,
    i.fecha_creacion,
    r.usuario AS reportador,
    z.nombre AS zona,
    c.nombre AS categoria,
    rec.valor AS valor_recompensa,
    rec.descripcion AS descripcion_recompensa
FROM INCIDENCIA i, REPORTADOR r, USUARIO u, ZONA z, CLASIFICAR cl, CATEGORIA c, RECOMPENSAR rp, RECOMPENSA rec
WHERE i.reportador = r.usuario
  AND r.usuario = u.nombre_usuario
  AND i.zona = z.id
  AND i.id = cl.incidencia
  AND cl.categoria = c.id
  AND i.id = rp.incidencia
  AND rp.recompensa = rec.id;

  DROP VIEW VW_DETALLE_INCIDENCIAS

SELECT * FROM VW_DETALLE_INCIDENCIAS


--Función 1: total de incidencias creadas por un reportador


CREATE OR REPLACE FUNCTION FN_TOTAL_INCIDENCIAS_REPORTADOR(p_usuario VARCHAR(50)) 
RETURNS INT AS $$
	DECLARE
    	v_total int;
	BEGIN
		
	    SELECT COUNT(*)
	    INTO STRICT v_total
	    FROM REPORTADOR r, INCIDENCIA i
	    WHERE r.usuario = i.reportador
	      AND r.usuario = p_usuario;

    	RETURN v_total;
		
	END;
$$ LANGUAGE plpgsql;

SELECT FN_TOTAL_INCIDENCIAS_REPORTADOR('JesusBP');

--Función 2: total de incidencias resueltas aceptadas por colaborador

CREATE OR REPLACE FUNCTION FN_TOTAL_RESUELTAS_ACEPTADAS(p_colaborador VARCHAR(50)) 
RETURNS int AS $$
	DECLARE
	    v_total int;
	BEGIN
	    SELECT COUNT(*)
	    INTO v_total
	    FROM RESOLVER r, SOLUCION s
	    WHERE r.solucion = s.id
	      AND r.colaborador = p_colaborador
	      AND s.es_aceptada = TRUE;
	
	    RETURN v_total;
	END;
$$ LANGUAGE plpgsql;


--Función 3: obtener la recompensa de una incidencia

CREATE OR REPLACE FUNCTION FN_VALOR_RECOMPENSA_INCIDENCIA(p_incidencia int) 
RETURNS int AS $$
	DECLARE
    	v_valor RECOMPENSA.valor%TYPE;
	BEGIN
	    SELECT r.valor
	    INTO v_valor
	    FROM RECOMPENSAR rp, RECOMPENSA r
	    WHERE rp.recompensa = r.id
	    AND rp.incidencia = p_incidencia;
	
	    RETURN v_valor;
		
		EXCEPTION
		    WHEN NO_DATA_FOUND THEN
		        RETURN 0;
	END;
$$ LANGUAGE plpgsql;

SELECT FN_VALOR_RECOMPENSA_INCIDENCIA(50);


-- FALTA 1 FUNCION y 4 TRIGGERS, AÑADIR EXPLICACION Y MOTIVO.