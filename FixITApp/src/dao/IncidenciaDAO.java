package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import modelo.Incidencia;
import util.ConexionBD;

public class IncidenciaDAO {
	// contenedor de los usuarios de la base de datos
		public static ArrayList<Incidencia> obtenerIncidencias(){
				
				Connection conn = ConexionBD.getConexion();
				
				//campos incidencia
				int id;
				String estado, titulo, descripcion, reportador, zona, fechaCreacion;
				
				ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
				
				try {
					// consulta usada para seleccionar todos los usuario de la tabla 
					PreparedStatement st = conn.prepareStatement("SELECT INCIDENCIA.id, INCIDENCIA.estado, INCIDENCIA.titulo,INCIDENCIA.descripcion,INCIDENCIA.fecha_creacion,INCIDENCIA.reportador,ZONA.nombre FROM INCIDENCIA,ZONA WHERE INCIDENCIA.zona = ZONA.id;");
					// ejecutar la consulta
					ResultSet rs = st.executeQuery();
					
					
					// bucle para ir creando los usuarios y añadirlos al contenedor
					while(rs.next()) {
						
						id = rs.getInt("id");
						estado = rs.getString("estado");
						titulo = rs.getString("titulo");
						descripcion = rs.getString("descripcion");
						fechaCreacion = rs.getString("fecha_creacion");
						reportador = rs.getString("reportador");
						zona = rs.getString("nombre");
						
						incidencias.add(new Incidencia(id, estado, titulo, descripcion, reportador, zona,
			fechaCreacion));
						
					}
					
					// importante cerrar la conexion siempre
					conn.close();
				} catch (SQLException e) {
					// modificar? esto es para ver que error da la base de datos
					System.out.println(e);
				}
				// esto es para que el array se ordene por ID 
				incidencias.sort(Comparator.comparingInt(Incidencia::getId));
				return incidencias;
		}
		
		public static void eliminarIncidencia(Incidencia i) {
			Connection conn = ConexionBD.getConexion();
			
			try {
				PreparedStatement st = conn.prepareStatement("DELETE FROM INCIDENCIA WHERE id = ?");
				
				// se le pasa el id de la consulta que queremos eliminar
				st.setInt(1, i.getId());
				// ejecutar la consulta
				st.executeUpdate();
				
				
				
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
