# FIXIT - Proyecto Intermodular 1 DAM

FIXIT es una aplicacion creada como trabajo del modulo de Proyecto Intermodular de 1o DAM.

El objetivo del proyecto es ofrecer una plataforma para registrar incidencias, consultar problemas abiertos, proponer soluciones y gestionar usuarios desde una aplicacion de escritorio, acompanada por una web informativa con ranking y formulario de contacto.

## Integrantes

- Jesus
- Khaled
- Thiago

## Que hace la aplicacion

FIXIT permite organizar incidencias por zonas y categorias. Los usuarios pueden crear incidencias, ver problemas disponibles y ofrecerse para resolverlos. Los administradores pueden consultar incidencias, usuarios y mensajes enviados desde la web.

La idea principal es simular una comunidad donde las personas reportan problemas y otros usuarios colaboran para solucionarlos.

## Partes del proyecto

### FixITApp

Aplicacion de escritorio desarrollada en Java con Swing.

Incluye:

- Inicio de sesion y registro de usuarios.
- Panel principal de usuario.
- Creacion de incidencias.
- Listado de incidencias abiertas.
- Envio de soluciones.
- Perfil de usuario con estadisticas.
- Panel de administrador.
- Gestion de incidencias, usuarios y mensajes de contacto.

### FixITWeb

Web del proyecto.

Incluye:

- Presentacion de FIXIT.
- Secciones de informacion y categorias.
- Ranking de colaboradores cargado desde PHP.
- Estadisticas generales.
- Formulario de contacto conectado con la base de datos.

### BD

Carpeta con los scripts de base de datos.

Incluye:

- Creacion de tablas.
- Insercion de datos de prueba.
- Consultas SQL.
- Vistas, funciones y triggers en PL/pgSQL.

## Base de datos

El proyecto usa PostgreSQL.

Configuracion usada durante el desarrollo:

- Base de datos: `incidencias`
- Usuario: `postgres`
- Puerto: `7777`
- Contrasena: `12345`

Para preparar la base de datos, ejecutar los scripts de la carpeta `BD` en este orden:

1. `DDL_ProgramaIncidencias.sql`
2. `DML_ProgramaIncidencias.sql`
3. `pl_pgsql.sql`

## Como ejecutar la aplicacion Java

1. Abrir la carpeta `FixITApp` en Eclipse o en un IDE compatible con Java.
2. Comprobar que el driver `postgresql-42.7.11.jar` esta anadido al classpath.
3. Tener PostgreSQL iniciado con la base de datos `incidencias`.
4. Ejecutar la clase:

```text
ventanas.Ventana_Inicio
```

## Como ejecutar la web

1. Copiar la carpeta `FixITWeb` dentro de `htdocs` si se usa XAMPP.
2. Iniciar Apache.
3. Asegurarse de que PostgreSQL esta funcionando.
4. Abrir en el navegador:

```text
http://localhost/FixITWeb/
```

## Tecnologias usadas

- Java
- Swing
- PostgreSQL
- PL/pgSQL
- HTML
- CSS
- JavaScript
- PHP

## Objetivo del proyecto

Este trabajo busca unir varias partes vistas durante el curso: programacion orientada a objetos, interfaces graficas, bases de datos, SQL, procedimientos almacenados y desarrollo web basico.

FIXIT no es solo una aplicacion aislada, sino un proyecto completo con app, web y base de datos trabajando juntas.
