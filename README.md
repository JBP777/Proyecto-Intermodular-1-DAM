# FIXIT - Proyecto Intermodular 1 DAM

FIXIT es una plataforma colaborativa para gestionar incidencias tecnicas. El
proyecto combina una aplicacion de escritorio en Java Swing, una pagina web
con PHP y JavaScript, y una base de datos PostgreSQL con vistas, funciones y
triggers.

La idea principal es centralizar problemas dentro de una comunidad: un usuario
reporta una incidencia, otro usuario puede proponer una solucion y el
administrador supervisa el estado general del sistema.

## Integrantes

- Jesus Barru Petrule
- Khaled Nayeb Khil
- Thiago Bart Sesseler

## Partes del proyecto

```text
.
|-- BD/                         Scripts SQL y documentacion de base de datos
|-- Diagramas/                  Diagramas de flujo, paquetes y navegacion
|-- FixITApp/                   Aplicacion Java Swing
|-- FixITWeb/                   Web, endpoints PHP y JAR descargable
|-- Documentacion_Proyecto_Intermodular_Jesus_Khaled_Thiago.pdf
|-- PresentacionFIXIT.pdf
`-- README.md
```

## Funcionalidades principales

### Usuario estandar

- Registro e inicio de sesion contra PostgreSQL.
- Creacion de incidencias con titulo, descripcion, zona y categoria.
- Consulta de incidencias abiertas creadas por otros usuarios.
- Envio de soluciones a incidencias disponibles.
- Consulta de incidencias solucionadas propias.
- Envio de mensajes al administrador.
- Visualizacion del perfil con estadisticas de actividad.

### Administrador

- Acceso a un panel propio al iniciar sesion con un usuario administrador.
- Consulta, apertura, cierre y eliminacion de incidencias.
- Consulta y eliminacion de usuarios.
- Lectura de mensajes enviados desde la aplicacion o la web.
- Revision del estado global de la plataforma.

Actualmente el usuario administrador definido en el codigo es:

```text
JesusBP
```

## Aplicacion Java

La aplicacion de escritorio esta en `FixITApp` y esta desarrollada con Java
Swing. Sigue una estructura cercana a MVC:

- `modelo`: clases POJO que representan entidades del sistema.
- `dao`: acceso a datos mediante JDBC y consultas a PostgreSQL.
- `ventanas`: interfaces graficas Swing y navegacion de la aplicacion.
- `util`: conexion a base de datos, colores compartidos y control de
  administradores.

El punto de entrada es:

```text
ventanas.Ventana_Inicio
```

El driver incluido para PostgreSQL es:

```text
FixITApp/postgresql-42.7.11.jar
```

## Web

La web esta en `FixITWeb` y funciona como pagina complementaria del proyecto.
Incluye:

- Presentacion de FIXIT.
- Secciones informativas y categorias.
- Cambio de idioma ES/EN sin recargar la pagina.
- Ranking de colaboradores cargado desde PostgreSQL.
- Estadisticas de incidencias y colaboradores.
- Formulario de contacto conectado con la tabla `CONTACTO`.
- Descarga del archivo `FixIT.jar`.

Endpoints PHP:

- `PHP/api_incidencias.php?accion=ranking`: devuelve el top 3 de colaboradores.
- `PHP/api_incidencias.php?accion=stats`: devuelve contadores generales.
- `PHP/contacto.php`: recibe el formulario por `POST`, valida los campos y
  guarda el mensaje.

El frontend refresca ranking y estadisticas cada 30 segundos.

## Base de datos

El proyecto usa PostgreSQL. La configuracion activa en Java y PHP es:

```text
Host: localhost
Base de datos: incidencias
Puerto: 7777
Usuario: postgres
Contrasena: 12345
```

Scripts disponibles en `BD`:

- `DDL_FixIT.sql`: crea las tablas.
- `DML_FixIT.sql`: inserta datos de prueba.
- `pl_pgsql.sql`: crea vistas, funciones y triggers.
- `Consultas_FixIT.sql`: consultas de practica y operaciones SQL.
- `FixIT_BBDD_Documentacion.pdf`: documentacion especifica de la BD.

Orden recomendado de ejecucion:

```text
1. BD/DDL_FixIT.sql
2. BD/DML_FixIT.sql
3. BD/pl_pgsql.sql
```

### Modelo de datos

El modelo parte de `USUARIO`, especializado en `REPORTADOR` y `COLABORADOR`.
Todo usuario puede crear incidencias y tambien colaborar resolviendo las de
otros.

Entidades y tablas destacadas:

- `USUARIO`, `REPORTADOR`, `COLABORADOR`
- `INCIDENCIA`, `SOLUCION`, `RESOLVER`, `VALORAR`
- `ZONA`, `CATEGORIA`, `CLASIFICAR`
- `MENSAJE`, `CONTACTO`
- `LOGRO`, `RECOMPENSA`, `TIPO_RECOMPENSA`

La tabla `CONTACTO` se creo para conectar la web con la aplicacion: el
formulario guarda mensajes en PostgreSQL y el administrador puede leerlos
desde Java.

### Vistas, funciones y triggers

`pl_pgsql.sql` centraliza parte de la logica de negocio en la base de datos.

Vistas:

- `vista_incidencias_con_zona_categoria`: incidencias con zona y categorias.
- `vista_top_colaboradores`: ranking de colaboradores por incidencias
  resueltas.

Funciones:

- `fn_total_incidencias_reportador(usuario)`
- `fn_incidencias_abiertas_por_zona(zona)`
- `insertar_incidencia(...)`
- `fn_recalcular_valoraciones()`
- `eliminar_usuario(usuario)`

Triggers:

- Bloquea el borrado de incidencias en estado `En progreso`.
- Registra automaticamente cada nuevo usuario como reportador y colaborador.
- Actualiza `total_resueltas` cuando cambia la tabla `RESOLVER`.
- Impide que un usuario resuelva una incidencia creada por el mismo.

## Como ejecutar la aplicacion Java

1. Crear la base de datos `incidencias` en PostgreSQL.
2. Ejecutar los scripts SQL de `BD` en el orden indicado.
3. Abrir `FixITApp` en Eclipse, IntelliJ IDEA o un IDE compatible con Java.
4. Anadir `FixITApp/postgresql-42.7.11.jar` al classpath si el IDE no lo hace
   automaticamente.
5. Comprobar en `FixITApp/src/util/ConexionBD.java` que host, puerto, usuario
   y contrasena coinciden con tu PostgreSQL.
6. Ejecutar `ventanas.Ventana_Inicio`.

Usuarios de prueba creados por `DML_FixIT.sql`:

```text
JesusBP / 12345
KhaledKN / 12345
ThiagoBS / 12345
Usuario01 / 54321
Usuario02 / 54321
```

## Como ejecutar la web

Opcion habitual con XAMPP:

1. Copiar `FixITWeb` dentro de `htdocs`.
2. Iniciar Apache.
3. Tener PostgreSQL en marcha con la base `incidencias`.
4. Activar soporte PostgreSQL para PHP si no esta activo (`pdo_pgsql` /
   `pgsql`).
5. Abrir:

```text
http://localhost/FixITWeb/
```

Los archivos PHP usan la misma configuracion de conexion que la aplicacion
Java. Si se cambia el puerto, usuario o contrasena, hay que actualizar:

- `FixITWeb/PHP/api_incidencias.php`
- `FixITWeb/PHP/contacto.php`
- `FixITApp/src/util/ConexionBD.java`

## Despliegue documentado

La documentacion del proyecto describe un despliegue en dos maquinas
virtuales Ubuntu Server:

- VM1: servidor web con Apache2, PHP, `php-pgsql`, `libapache2-mod-php` y SSL.
- VM2: servidor PostgreSQL con la base de datos `incidencias`.

El flujo de despliegue planteado es configurar primero la VM de base de datos
y despues la VM web, para que PHP pueda apuntar a la IP final del servidor
PostgreSQL.

## Pruebas realizadas

La documentacion recoge pruebas manuales de:

- Registro correcto y registro de usuario ya existente.
- Inicio de sesion correcto, usuario inexistente y campos vacios.
- Eliminacion de incidencias y usuarios desde el panel administrador.
- Cambio de estado de incidencias.
- Filtrado de incidencias disponibles para no mostrar las propias.
- Envio de soluciones y actualizacion de incidencias cerradas.
- Creacion de nuevas incidencias.
- Envio y lectura de mensajes de contacto.
- Actualizacion de estadisticas del perfil.
- Traduccion de la web y envio del formulario web.

## Tecnologias

- Java
- Java Swing
- JDBC
- PostgreSQL
- PL/pgSQL
- HTML
- CSS
- JavaScript
- PHP
- PDO
- Apache / XAMPP
- Git y GitHub

## Documentacion adicional

- `Documentacion_Proyecto_Intermodular_Jesus_Khaled_Thiago.pdf`: memoria
  completa del proyecto.
- `PresentacionFIXIT.pdf`: presentacion del proyecto.
- `BD/FixIT_BBDD_Documentacion.pdf`: documentacion de base de datos.
- `Diagramas/`: diagramas UML, mapas de navegacion y flujos.

## Posibles mejoras futuras

La base de datos ya contiene tablas para logros, recompensas y mensajes entre
usuarios. En la version actual parte de esa funcionalidad queda preparada para
ampliaciones futuras, como:

- Sistema completo de recompensas y logros.
- Mensajeria entre usuarios.
- Gestion mas avanzada de valoraciones.
- Externalizar la configuracion de conexion para no modificar codigo al
  cambiar de entorno.
