package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Contacto;
import modelo.Usuario;
import util.ConexionBD;

public class ContactoDAO {
	
		public static ArrayList<Contacto> obtenerMensajesContacto(){
			Connection conn = ConexionBD.getConexion();
			
			int id;
			String nombre, email, asunto, mensaje;
			
			ArrayList<Contacto> mensajes = new ArrayList<Contacto>();
			try {
				// consulta usada para seleccionar todos los mensajes de la tabla 
				PreparedStatement st = conn.prepareStatement("SELECT id, nombre, email, asunto, mensaje FROM CONTACTO");
				// ejecutar la consulta
				ResultSet rs = st.executeQuery();
				
				
				// bucle para ir creando los usuarios y añadirlos al contenedor
				while(rs.next()) {
					id = rs.getInt("id");
					nombre = rs.getString("nombre");
					email = rs.getString("email");
					asunto = rs.getString("asunto");
					mensaje = rs.getString("mensaje");
					
					mensajes.add(new Contacto(id, nombre, email, asunto, mensaje));
				}
				
				// importante cerrar la conexion siempre
				conn.close();
			} catch (SQLException e) {
				// modificar? esto es para ver que error da la base de datos
				System.out.println(e);
			}
			
			return mensajes;
			
		}
		
		
}
