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
				String estado, titulo, descripcion, reportador, zona, fechaCreacion,categorias;
				
				ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
				
				try {
					// consulta usada para seleccionar todos los usuario de la tabla 
					PreparedStatement st = conn.prepareStatement("SELECT * FROM vista_incidencias_con_zona_categoria;");
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
						zona = rs.getString("nombre_zona");
						categorias = rs.getString("categorias");
						
						incidencias.add(new Incidencia(id, estado, titulo, descripcion, reportador, zona,
			fechaCreacion,categorias));
						
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
				
		        PreparedStatement stClasificar = conn.prepareStatement(
		                "DELETE FROM clasificar WHERE incidencia = ?");
		            stClasificar.setInt(1, i.getId());
		            stClasificar.executeUpdate();
				
				
				
				
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
		
		public static boolean agregarIncidencia(Incidencia i) { 
			Connection conn = ConexionBD.getConexion();
			boolean exito = false;
			try {
				PreparedStatement st = conn.prepareStatement("SELECT insertar_incidencia(?,?,?,?,?);");
				st.setString(1, i.getTitulo());
				st.setString(2, i.getDescripcion());
				st.setString(3, i.getReportador());
				st.setString(4, i.getZona());
				st.setString(5, i.getCategorias());
				
				st.executeQuery();
				
				exito = true;
				
				
				conn.close();
			} catch (SQLException e) {
				System.out.println("Error al insertar incidencia: " + e.getMessage());
				e.printStackTrace();
			}
			return exito;
		}
		
		// metodo para abrir incidencias con el estado cerrado
		public static boolean abrirIncidencia(Incidencia i) {
		    boolean actualizada = false;
		    Connection conn = ConexionBD.getConexion();

		    try {
		        if (i.getEstado().equals("Cerrada")) {
		            PreparedStatement st = conn.prepareStatement(
		                "UPDATE INCIDENCIA SET estado = 'Abierta' WHERE id = ?");
		            st.setInt(1, i.getId());
		            st.executeUpdate();
		            actualizada = true;
		        }
		        conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return actualizada;
		}

		// cerrar incidencias con el estado abierto
		public static boolean cerrarIncidencia(Incidencia i) {
		    boolean actualizada = false;
		    Connection conn = ConexionBD.getConexion();

		    try {
		        if (i.getEstado().equals("Abierta")) {
		            PreparedStatement st = conn.prepareStatement(
		                "UPDATE INCIDENCIA SET estado = 'Cerrada' WHERE id = ?");
		            st.setInt(1, i.getId());
		            st.executeUpdate();
		            actualizada = true;
		        }
		        conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return actualizada;
		}
		
}
