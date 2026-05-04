CREATE TABLE USUARIO (
    nombre_usuario VARCHAR(50) PRIMARY KEY,
    email VARCHAR(100),
    fecha_registro TIMESTAMP,
    contrasenya VARCHAR(255) 
);

CREATE TABLE ZONA (
    id numeric(10,0) PRIMARY KEY,
	nombre VARCHAR(100)
);

CREATE TABLE CATEGORIA (
    id NUMERIC(10,0) PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(500)
);

CREATE TABLE TIPO_RECOMPENSA (
    id NUMERIC(10,0) PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(500)
);

CREATE TABLE LOGRO (
    id NUMERIC(10,0) PRIMARY KEY,
    descripcion VARCHAR(500),
    nombre VARCHAR(100)
);

CREATE TABLE COLABORADOR (
    usuario VARCHAR(50) PRIMARY KEY,
    valoracion_media NUMERIC(3,2),
    total_resueltas NUMERIC(10,0),
    FOREIGN KEY (usuario) REFERENCES USUARIO(nombre_usuario)
);

CREATE TABLE REPORTADOR (
    usuario VARCHAR(50) PRIMARY KEY,
    total_creadas NUMERIC(10,0), 
    FOREIGN KEY (usuario) REFERENCES USUARIO(nombre_usuario)
);


CREATE TABLE SER_DE (
    usuario VARCHAR(50),
    id_zona NUMERIC(10,0),
    PRIMARY KEY (usuario, id_zona),
    FOREIGN KEY (usuario) REFERENCES USUARIO(nombre_usuario),
    FOREIGN KEY (id_zona) REFERENCES ZONA(id)
);


CREATE TABLE ESTAR_ESPECIALIZADO (
    usuario VARCHAR(50),
    categoria NUMERIC(10,0),
    PRIMARY KEY (usuario, categoria),
    FOREIGN KEY (usuario) REFERENCES USUARIO(nombre_usuario),
    FOREIGN KEY (categoria) REFERENCES CATEGORIA(id)
);

CREATE TABLE SEGUIR (
    usuario1 VARCHAR(50),
    usuario2 VARCHAR(50),
    PRIMARY KEY (usuario1, usuario2),
    FOREIGN KEY (usuario1) REFERENCES USUARIO(nombre_usuario),
    FOREIGN KEY (usuario2) REFERENCES USUARIO(nombre_usuario)
);

CREATE TABLE MENSAJE (
    id NUMERIC(10,0) PRIMARY KEY,
    contenido VARCHAR(1000),
    fecha_envio TIMESTAMP,
    usuario_envia VARCHAR(50),
    usuario_recibe VARCHAR(50),
    FOREIGN KEY (usuario_envia) REFERENCES USUARIO(nombre_usuario),
    FOREIGN KEY (usuario_recibe) REFERENCES USUARIO(nombre_usuario)
);

CREATE TABLE INCIDENCIA (
    id NUMERIC(10,0) PRIMARY KEY,
    estado VARCHAR(50),
    titulo VARCHAR(200),
    descripcion VARCHAR(1000),
    fecha_creacion TIMESTAMP,
    reportador VARCHAR(50) NOT NULL,
    zona numeric(10,0),
    FOREIGN KEY (reportador) REFERENCES REPORTADOR(usuario),
    FOREIGN KEY (zona) REFERENCES ZONA(id)
);

CREATE TABLE SOLUCION (
    id NUMERIC(10,0) PRIMARY KEY,
    es_aceptada BOOLEAN,
    descripcion VARCHAR(1000)
);

CREATE TABLE RESOLVER (
    colaborador VARCHAR(50),
    incidencia NUMERIC(10,0),
    solucion NUMERIC(10,0),
    PRIMARY KEY (incidencia, solucion),
    UNIQUE (incidencia, colaborador),
    FOREIGN KEY (incidencia) REFERENCES INCIDENCIA(id),
    FOREIGN KEY (solucion) REFERENCES SOLUCION(id),
    FOREIGN KEY (colaborador) REFERENCES COLABORADOR(usuario)
);

CREATE TABLE VALORAR (
    incidencia NUMERIC(10,0),
    solucion NUMERIC(10,0),
    reportador VARCHAR(50),
    PRIMARY KEY (incidencia, solucion, reportador),
    FOREIGN KEY (incidencia, solucion)
        REFERENCES RESOLVER(incidencia, solucion),
    FOREIGN KEY (reportador) REFERENCES REPORTADOR(usuario)
);

CREATE TABLE COMENTARIO (
    id NUMERIC(10,0) PRIMARY KEY,
    texto VARCHAR(1000),
    fecha TIMESTAMP,
    usuario VARCHAR(50),
    FOREIGN KEY (usuario) REFERENCES USUARIO(nombre_usuario)
);

CREATE TABLE TENER (
    comentario NUMERIC(10,0),
    solucion NUMERIC(10,0),
    PRIMARY KEY (comentario, solucion),
    FOREIGN KEY (comentario) REFERENCES COMENTARIO(id),
    FOREIGN KEY (solucion) REFERENCES SOLUCION(id)
);

CREATE TABLE RECOMPENSA (
    id NUMERIC(10,0) PRIMARY KEY,
    valor NUMERIC(10,2),
    descripcion VARCHAR(500),
    tipo_recompensa NUMERIC(10,0),
    FOREIGN KEY (tipo_recompensa) REFERENCES TIPO_RECOMPENSA(id)
);

CREATE TABLE RECOMPENSAR (
    incidencia NUMERIC(10,0) PRIMARY KEY,
    recompensa NUMERIC(10,0) UNIQUE,
    FOREIGN KEY (incidencia) REFERENCES INCIDENCIA(id),
    FOREIGN KEY (recompensa) REFERENCES RECOMPENSA(id)
);

CREATE TABLE CLASIFICAR (
    incidencia NUMERIC(10,0),
    categoria NUMERIC(10,0),
    PRIMARY KEY (incidencia, categoria),
    FOREIGN KEY (incidencia) REFERENCES INCIDENCIA(id),
    FOREIGN KEY (categoria) REFERENCES CATEGORIA(id)
);

CREATE TABLE OBTENER (
    logro NUMERIC(10,0),
    usuario VARCHAR(50),
    PRIMARY KEY (logro, usuario),
    FOREIGN KEY (logro) REFERENCES LOGRO(id),
    FOREIGN KEY (usuario) REFERENCES USUARIO(nombre_usuario)
);
