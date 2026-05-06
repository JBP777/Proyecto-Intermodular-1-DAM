-- TABLA USUARIO
INSERT INTO USUARIO (nombre_usuario, email, fecha_registro, contrasenya) VALUES
('JesusBP', 'jesbarpet@alu.edu.gva.es', '2026-01-10 10:15:00', '12345'),
('KhaledKN', 'khanay@alu.edu.gva.es', '2026-01-12 12:30:00', '12345'),
('ThiagoBS', 'thises@alu.edu.gva.es', '2026-01-15 09:45:00', '12345'),
('Usuario01', 'usuario01@alu.edu.gva.es', '2026-01-18 16:20:00', '54321'),
('Usuario02', 'usuario02@alu.edu.gva.es', '2026-01-20 18:05:00', '54321');

-- TABLA ZONA
INSERT INTO ZONA (id, nombre) VALUES
(1, 'Valencia'),
(2, 'Madrid'),
(3, 'Barcelona'),
(4, 'Sevilla'),
(5, 'Bilbao');

-- TABLA CATEGORIA
INSERT INTO CATEGORIA (id, nombre, descripcion) VALUES
(1, 'Redes', 'Incidencias relacionadas con problemas de red'),
(2, 'Base de datos', 'Incidencias relacionadas con bases de datos'),
(3, 'Frontend', 'Incidencias relacionadas con la interfaz de usuario'),
(4, 'Backend', 'Incidencias relacionadas con la lógica de servidor'),
(5, 'Seguridad','Incidencias relacionadas con seguridad');

-- TABLA TIPO_RECOMPENSA
INSERT INTO TIPO_RECOMPENSA (id, nombre, descripcion) VALUES
(1, 'Monetaria', 'Recompensa económica por resolver incidencias'),
(2, 'Puntos', 'Puntos de reputación por contribuciones'),
(3, 'Badge', 'Insignias virtuales por logros'),
(4, 'Gift Card', 'Tarjetas regalo como recompensa'),
(5, 'Certificado', 'Certificados de reconocimiento');

-- TABLA LOGRO
INSERT INTO LOGRO (id, descripcion, nombre) VALUES
(1, 'Resolver 10 incidencias como colaborador', 'Colaborador Novato'),
(2, 'Reportar 5 incidencias', 'Reportador Inicial'),
(3, 'Recibir valoración perfecta de una solución', 'Valoración Perfecta'),
(4, 'Estar especializado en 3 categorías', 'Especialista'),
(5, 'Resolver 50 incidencias', 'Colaborador Experto');

-- TABLA COLABORADOR
INSERT INTO COLABORADOR (usuario, valoracion_media, total_resueltas) VALUES
('JesusBP', 4.20, 8),
('KhaledKN', 4.90, 6),
('ThiagoBS', 4.50, 9),
('Usuario01', 4.10, 5),
('Usuario02', 3.85, 3);

-- TABLA REPORTADOR
INSERT INTO REPORTADOR (usuario, total_creadas) VALUES
('JesusBP', 8),
('KhaledKN', 5),
('ThiagoBS', 6),
('Usuario01', 3),
('Usuario02', 4);

-- TABLA SER_DE
INSERT INTO SER_DE (usuario, id_zona) VALUES
('JesusBP', 1),   -- Valencia
('KhaledKN', 2),  -- Madrid
('ThiagoBS', 3),  -- Barcelona
('Usuario01', 4), -- Sevilla
('Usuario02', 5); -- Bilbao

-- TABLA ESTAR_ESPECIALIZADO
INSERT INTO ESTAR_ESPECIALIZADO (usuario, categoria) VALUES
('JesusBP', 1),
('KhaledKN', 2),
('ThiagoBS', 3),
('Usuario01', 4),
('Usuario02', 5);

-- TABLA INCIDENCIA
INSERT INTO INCIDENCIA (id, estado, titulo, descripcion, fecha_creacion, reportador, zona) VALUES
(1, 'Abierta', 'Error al cargar la página', 'La página principal no carga correctamente en ciertos navegadores.', '2026-02-01 09:00:00', 'JesusBP', 1),
(2, 'En progreso', 'Problema con la base de datos', 'Al actualizar los registros, algunos datos no se guardan.', '2026-02-02 10:30:00', 'KhaledKN', 2),
(3, 'Cerrada', 'Permisos incorrectos de usuario', 'Algunos usuarios no pueden acceder a ciertas funciones.', '2026-02-03 11:15:00', 'ThiagoBS', 3),
(4, 'Abierta', 'Consulta lenta en el sistema', 'La consulta SQL tarda demasiado tiempo en ejecutarse.', '2026-02-04 12:45:00', 'Usuario01', 4),
(5, 'En progreso', 'Validación de formularios insuficiente', 'Se pueden enviar datos erróneos sin avisos.', '2026-02-05 14:00:00', 'Usuario02', 5);

-- TABLA CLASIFICAR
INSERT INTO CLASIFICAR (incidencia, categoria) VALUES
(1, 3), -- Error al cargar la página -> Frontend
(2, 2), -- Problema con la base de datos -> Base de datos
(3, 5), -- Permisos incorrectos -> Seguridad
(4, 4), -- Consulta lenta -> Backend
(5, 1); -- Validación insuficiente -> Redes (ejemplo)

-- TABLA RECOMPENSA
INSERT INTO RECOMPENSA (id, valor, descripcion, tipo_recompensa) VALUES
(1, 50.00, '50€ por resolver incidencia crítica', 1),
(2, 100.00, '100 puntos por colaboración destacada', 2),
(3, 0.00, 'Badge de Especialista en Seguridad', 3),
(4, 25.00, 'Gift card Amazon', 4),
(5, 0.00, 'Certificado de participación', 5);

-- TABLA SOLUCION
INSERT INTO SOLUCION (id, es_aceptada, descripcion) VALUES
(1, TRUE, 'Reiniciar el servidor y limpiar la caché.'),
(2, FALSE, 'Actualizar la base de datos a la versión más reciente.'),
(3, TRUE, 'Cambiar la configuración de permisos de usuario.'),
(4, FALSE, 'Optimizar la consulta SQL que genera el error.'),
(5, TRUE, 'Agregar validación de datos en el formulario de entrada.');

-- TABLA RESOLVER (si tuvieras datos)
-- INSERT INTO RESOLVER (colaborador, incidencia, solucion) VALUES ...;

-- TABLA VALORAR (si tuvieras datos)
-- INSERT INTO VALORAR (incidencia, solucion, reportador) VALUES ...;

-- TABLA COMENTARIO
INSERT INTO COMENTARIO (id, texto, fecha, usuario) VALUES
(1, 'Creo que esta incidencia es urgente.', '2026-02-05 10:00:00', 'JesusBP'),
(2, 'Estoy revisando la incidencia 12.', '2026-02-05 11:15:00', 'KhaledKN'),
(3, 'Necesito más información sobre la zona afectada.', '2026-02-06 09:30:00', 'ThiagoBS'),
(4, 'He completado mi revisión de la incidencia.', '2026-02-06 14:45:00', 'Usuario01'),
(5, 'Gracias por la ayuda, todo solucionado.', '2026-02-07 16:20:00', 'Usuario02');

-- TABLA TENER
INSERT INTO TENER (comentario, solucion) VALUES
(1, 1),  -- Comentario de JesusBP pertenece a la solución 1
(2, 2),  -- Comentario de KhaledKN pertenece a la solución 2
(3, 3),  -- Comentario de ThiagoBS pertenece a la solución 3
(4, 4),  -- Comentario de Usuario01 pertenece a la solución 4
(5, 5);  -- Comentario de Usuario02 pertenece a la solución 5

-- TABLA SEGUIR
INSERT INTO SEGUIR (usuario1, usuario2) VALUES
('JesusBP', 'KhaledKN'),
('JesusBP', 'ThiagoBS'),
('KhaledKN', 'JesusBP'),
('ThiagoBS', 'Usuario01'),
('Usuario02', 'JesusBP');

-- TABLA MENSAJE
INSERT INTO MENSAJE (id, contenido, fecha_envio, usuario_envia, usuario_recibe) VALUES
(1, 'Hola Khaled, ¿has visto la incidencia 12?', '2026-02-01 09:10:00', 'JesusBP', 'KhaledKN'),
(2, 'Sí, estoy trabajando en ella', '2026-02-01 09:15:00', 'KhaledKN', 'JesusBP'),
(3, 'Thiago, necesito tu ayuda con la zona 3', '2026-02-02 11:30:00', 'JesusBP', 'ThiagoBS'),
(4, 'Usuario01, revisa tu reporte de ayer', '2026-02-03 14:20:00', 'Usuario02', 'Usuario01'),
(5, 'Perfecto, lo revisaré', '2026-02-03 14:35:00', 'Usuario01', 'Usuario02');


-- TABLA RESOLVER
INSERT INTO RESOLVER (colaborador, incidencia, solucion) VALUES
('JesusBP', 1, 1),
('KhaledKN', 2, 2),
('ThiagoBS', 3, 3),
('Usuario01', 4, 4),
('Usuario02', 5, 5);


-- TABLA RECOMPENSAR
INSERT INTO RECOMPENSAR (incidencia, recompensa) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- TABLA VALORAR
INSERT INTO VALORAR (incidencia, solucion, reportador) VALUES
(1, 1, 'JesusBP'),
(2, 2, 'KhaledKN'),
(3, 3, 'ThiagoBS'),
(4, 4, 'Usuario01'),
(5, 5, 'Usuario02');