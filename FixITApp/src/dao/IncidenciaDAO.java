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
					PreparedStatement st = conn.prepareStatement("SELECT * FROM vista_incidencias_con_zona;");
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
		
		public static boolean agregarIncidencia(String titulo, String descripcion, String fecha, String reportador, int idZona) {
			Connection conn = ConexionBD.getConexion();
			boolean exito = false;
			try {
				// 1. Obtener el próximo ID disponible
				PreparedStatement stMaxId = conn.prepareStatement("SELECT COALESCE(MAX(id), 0) + 1 AS next_id FROM INCIDENCIA");
				ResultSet rsMaxId = stMaxId.executeQuery();
				int nextId = 1;
				if (rsMaxId.next()) {
					nextId = rsMaxId.getInt("next_id");
				}
				
				// 2. Asegurar que el usuario existe en REPORTADOR
				PreparedStatement stCheckReportador = conn.prepareStatement("SELECT * FROM REPORTADOR WHERE usuario = ?");
				stCheckReportador.setString(1, reportador);
				ResultSet rsRep = stCheckReportador.executeQuery();
				
				if (rsRep.next()) {
					// Actualizar total creadas
					PreparedStatement stUpdRep = conn.prepareStatement("UPDATE REPORTADOR SET total_creadas = total_creadas + 1 WHERE usuario = ?");
					stUpdRep.setString(1, reportador);
					stUpdRep.executeUpdate();
				} else {
					// Insertar nuevo reportador
					PreparedStatement stInsRep = conn.prepareStatement("INSERT INTO REPORTADOR (usuario, total_creadas) VALUES (?, 1)");
					stInsRep.setString(1, reportador);
					stInsRep.executeUpdate();
				}
				
				// 3. Insertar la incidencia
				PreparedStatement stInsInc = conn.prepareStatement("INSERT INTO INCIDENCIA (id, estado, titulo, descripcion, fecha_creacion, reportador, zona) VALUES (?, 'Abierta', ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)");
				stInsInc.setInt(1, nextId);
				stInsInc.setString(2, titulo);
				stInsInc.setString(3, descripcion);
				stInsInc.setString(4, fecha);
				stInsInc.setString(5, reportador);
				stInsInc.setInt(6, idZona);
				
				int filasAfectadas = stInsInc.executeUpdate();
				if (filasAfectadas > 0) exito = true;
				
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
