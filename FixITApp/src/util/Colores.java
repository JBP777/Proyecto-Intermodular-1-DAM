package util;

import java.awt.Color;

/**
 * Paleta comun de colores usada por las ventanas Swing.
 */
public class Colores {

    // Fondos.
    public static final Color AMARILLO_FONDO    = new Color(255, 200, 0);
    public static final Color AMARILLO_PASTEL   = new Color(255, 245, 130);

    // Textos y detalles oscuros.
    public static final Color AMARILLO_OSCURO   = new Color(80, 50, 0);

    // Verdes.
    public static final Color VERDE_BRILLANTE   = new Color(0, 190, 120);
    public static final Color VERDE_OSCURO      = new Color(0, 110, 55);

    // Colores de estado y botones de accion.
    public static final Color ROJO_ADMIN        = new Color(180, 0, 0);
    public static final Color ROJO_ELIMINAR     = new Color(200, 40, 40);
    public static final Color ROJO_ELIMINAR_TXT = new Color(255, 255, 255);

    // Colores para tablas.
    public static final Color CABECERA_TABLA    = new Color(60, 40, 0);
    public static final Color FILA_TABLA        = new Color(255, 250, 210);
    public static final Color SELECCION_TABLA   = new Color(0, 160, 100);
    public static final Color BORDE_TABLA       = new Color(200, 170, 0);

    // Evita crear objetos de una clase que solo contiene constantes.
    private Colores() {}
}
