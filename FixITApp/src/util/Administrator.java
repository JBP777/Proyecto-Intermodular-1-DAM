package util;

import java.util.ArrayList;

public class Administrator {
		// ArrayList para establecer si un usuario es admin.
		public static ArrayList<String> ADMINS = new ArrayList<String>();
		
		// Metodo para comprobar si el usuario es admin
		public static boolean esAdmin(String usuario) {
			return ADMINS.contains(usuario);
		}
		// Metodo para añadir administradores.
		public static void addAdmin(String usuario) {
			ADMINS.add(usuario);
		}
		// Metodo para eliminar administradores
		public static void removeAdmin(String usuario) {
			ADMINS.remove(usuario);
		}
		
}
