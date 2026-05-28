package util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Gestiona la lista de usuarios con permisos de administrador.
 */
public class Administrator {

	// Usuarios que deben entrar con la ventana de administrador.
	public static ArrayList<String> ADMINS = new ArrayList<>(Arrays.asList("JesusBP"));

	// Comprueba si el nombre de usuario aparece en la lista de administradores.
	public static boolean esAdmin(String usuario) {
		return ADMINS.contains(usuario);
	}
}
