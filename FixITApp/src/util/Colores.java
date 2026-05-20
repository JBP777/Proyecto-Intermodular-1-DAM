package util;

import java.awt.Color;

public class Colores {

    // FONDOS
    public static final Color AMARILLO_FONDO    = new Color(255, 200, 0);      // amarillo principal, ligeramente mas saturado
    public static final Color AMARILLO_PASTEL   = new Color(255, 245, 130);    // amarillo claro para botones secundarios

    // TEXTOS Y DETALLES OSCUROS
    public static final Color AMARILLO_OSCURO   = new Color(80, 50, 0);        // marron oscuro, mas legible sobre fondo amarillo

    // VERDES
    public static final Color VERDE_BRILLANTE   = new Color(0, 190, 120);      // verde principal, mas brillante y saturado
    public static final Color VERDE_OSCURO      = new Color(0, 110, 55);       // verde oscuro para texto sobre verde brillante

    // AÑADIDOS — colores de estado para incidencias y botones de accion
    public static final Color ROJO_ADMIN        = new Color(180, 0, 0);        // rojo para etiqueta ADMIN
    public static final Color ROJO_ELIMINAR     = new Color(200, 40, 40);      // rojo para botones de eliminar
    public static final Color ROJO_ELIMINAR_TXT = new Color(255, 255, 255);    // blanco para texto sobre rojo

    // AÑADIDOS — colores para las tablas
    public static final Color CABECERA_TABLA    = new Color(60, 40, 0);        // cabecera oscura, contrasta con el fondo
    public static final Color FILA_TABLA        = new Color(255, 250, 210);    // amarillo muy palido para filas
    public static final Color SELECCION_TABLA   = new Color(0, 160, 100);      // verde al seleccionar fila
    public static final Color BORDE_TABLA       = new Color(200, 170, 0);      // borde dorado entre celdas

    private Colores() {} // para que no se pueda instanciar la clase
}