package util;

import java.util.ArrayList;
import java.util.Arrays;

public class Administrator {
		// ArrayList para establecer si un usuario es admin. Se inicializa el array list para meter un administrador.
		public static ArrayList<String> ADMINS = new ArrayList<>(
			    Arrays.asList("JesusBP")
				);
		
		// Metodo para comprobar si el usuario es admin
		public static boolean esAdmin(String usuario) {
			return ADMINS.contains(usuario);
		}
		
		
}
