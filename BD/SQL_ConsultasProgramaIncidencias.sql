--a. 5 Consultas simples de una sola tabla
	--a.1
		SELECT nombre_usuario, email, fecha_registro
		FROM USUARIO;
	--a.2
		SELECT usuario, valoracion_media
		FROM COLABORADOR
		WHERE valoracion_media > 4.5;
	--a.3
		SELECT id, titulo, fecha_creacion
		FROM INCIDENCIA
		WHERE estado = 'Abierta';
	--a.4
		SELECT id, descripcion, valor
		FROM RECOMPENSA
		ORDER BY valor DESC;
	--a.5
		SELECT nombre_usuario, fecha_registro
		FROM USUARIO
		WHERE fecha_registro > '2026-01-15'
		ORDER BY fecha_registro;
--b. 2 Actualizaciones y 2 Borrados en cualquier tabla
	UPDATE INCIDENCIA
	SET estado = 'Cerrada'
	WHERE id = 1;
	
	UPDATE COLABORADOR
	SET valoracion_media = 4.75
	WHERE usuario = 'Usuario02';
	
	DELETE FROM ESTAR_ESPECIALIZADO
	WHERE usuario = 'Usuario02'
	AND categoria = 5;
	
	DELETE FROM MENSAJE
	WHERE id = 5;

--c.3 Consultas con más de 1 tabla
	--c.1 
		SELECT INCIDENCIA.id, INCIDENCIA.titulo, REPORTADOR.usuario AS reportador
		FROM INCIDENCIA, REPORTADOR
		WHERE INCIDENCIA.reportador = REPORTADOR.usuario;
	--c.2
		SELECT COLABORADOR.usuario AS colaborador, SOLUCION.descripcion AS solucion
		FROM COLABORADOR, RESOLVER, SOLUCION
		WHERE COLABORADOR.usuario = RESOLVER.colaborador
  		AND RESOLVER.solucion = SOLUCION.id;
	--c.3
		SELECT INCIDENCIA.id AS incidencia, INCIDENCIA.titulo, CATEGORIA.nombre AS categoria
		FROM INCIDENCIA, CLASIFICAR, CATEGORIA
		WHERE INCIDENCIA.id = CLASIFICAR.incidencia
	 	AND CLASIFICAR.categoria = CATEGORIA.id;

--d. 3 Consultas usando funciones
	--d.1
		SELECT COUNT(*) AS total_usuarios
		FROM USUARIO;
	--d.2
		SELECT AVG(valoracion_media) AS valoracion_promedio
		FROM COLABORADOR;
	--d.3
		SELECT MAX(valor) AS recompensa_mayor
		FROM RECOMPENSA;
	
--e. 2 Consultas usando group by
	--e.1
		SELECT ZONA.nombre AS zona, COUNT(INCIDENCIA.id) AS total_incidencias
		FROM INCIDENCIA, ZONA
		WHERE INCIDENCIA.zona = ZONA.id
		GROUP BY ZONA.nombre;
	--e.2
		SELECT USUARIO.nombre_usuario, MIN(COMENTARIO.fecha) AS primera_fecha_comentario
		FROM USUARIO, COMENTARIO
		WHERE USUARIO.nombre_usuario = COMENTARIO.usuario
		GROUP BY USUARIO.nombre_usuario;
--f. 2 Consultas utilizando subconsultas
	--f.1
		SELECT usuario
		FROM REPORTADOR
		WHERE total_creadas > (
    		SELECT AVG(tot	al_creadas)
    		FROM REPORTADOR
			);
	--f.2
		SELECT INCIDENCIA.id, INCIDENCIA.titulo
		FROM INCIDENCIA, RECOMPENSAR
		WHERE INCIDENCIA.id = RECOMPENSAR.incidencia
		  AND RECOMPENSAR.recompensa IN (
		      SELECT id
		      FROM RECOMPENSA
		      WHERE valor > (SELECT AVG(valor) FROM RECOMPENSA)
		  );
--g. 2 Consultas usando group by con having
	--g.1
		SELECT COMENTARIO.usuario, COUNT(*) AS total_comentarios
		FROM COMENTARIO
		GROUP BY COMENTARIO.usuario
		HAVING COUNT(*) >= 1;
	--g.2
		SELECT Z.nombre AS zona, COUNT(I.id) AS total_incidencias
		FROM INCIDENCIA I, ZONA Z
		WHERE I.zona = Z.id
		GROUP BY Z.nombre
		HAVING COUNT(I.id) >= 1;
--h. 3 actualizaciones usando subconsultas en where y set.
	UPDATE INCIDENCIA
	SET estado = 'Cerrada'
	WHERE id IN (
	    SELECT R.incidencia
	    FROM RESOLVER R, SOLUCION S
	    WHERE R.solucion = S.id
	      AND S.es_aceptada = TRUE
	);

	UPDATE COLABORADOR C
	SET total_resueltas = (
	    SELECT COUNT(*)
	    FROM RESOLVER R
	    WHERE R.colaborador = C.usuario
	);

	UPDATE COLABORADOR C
	SET total_resueltas = (
	    SELECT COUNT(*)
	    FROM RESOLVER R
	    WHERE R.colaborador = C.usuario
);