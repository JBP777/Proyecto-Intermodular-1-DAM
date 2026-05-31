package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JOptionPane;

import modelo.Incidencia;
import modelo.Usuario;
import util.ConexionBD;

/**
 * Acceso a datos y operaciones principales sobre incidencias.
 */
public class IncidenciaDAO {

	// Obtiene las incidencias desde la vista que ya trae zona y categorias.
	public static ArrayList<Incidencia> obtenerIncidencias() {
		Connection conn = ConexionBD.getConexion();
		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();

		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM vista_incidencias_con_zona_categoria;");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String estado = rs.getString("estado");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String fechaCreacion = rs.getString("fecha_creacion");
				String reportador = rs.getString("reportador");
				String zona = rs.getString("nombre_zona");
				String categorias = rs.getString("categorias");

				incidencias.add(new Incidencia(id, estado, titulo, descripcion, reportador, zona,
					fechaCreacion, categorias));
			}

			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
				"No se han podido cargar las incidencias.",
				"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		}

		// Ordena por ID para que la tabla sea estable al recargar.
		incidencias.sort(Comparator.comparingInt(Incidencia::getId));
		return incidencias;
	}
	
	
	// Obtiene las incidencias cerradas cuyo reportador es el usuario indicado.
	public static ArrayList<Incidencia> obtenerIncidenciasCerradasDeUsuario(Usuario u) {
	    Connection conn = ConexionBD.getConexion();
	    ArrayList<Incidencia> incidencias = new ArrayList<>();

	    try {
	        PreparedStatement st = conn.prepareStatement(
	            "SELECT * FROM vista_incidencias_con_zona_categoria " +
	            "WHERE estado = 'Cerrada' AND reportador = ?");
	        st.setString(1, u.getNombreUsuario());
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String estado = rs.getString("estado");
	            String titulo = rs.getString("titulo");
	            String descripcion = rs.getString("descripcion");
	            String fechaCreacion = rs.getString("fecha_creacion");
	            String reportador = rs.getString("reportador");
	            String zona = rs.getString("nombre_zona");
	            String categorias = rs.getString("categorias");

	            incidencias.add(new Incidencia(id, estado, titulo, descripcion,
	                reportador, zona, fechaCreacion, categorias));
	        }

	        conn.close();
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null,
	            "No se han podido cargar las incidencias resueltas.",
	            "Error de base de datos", JOptionPane.ERROR_MESSAGE);
	    }

	    incidencias.sort(Comparator.comparingInt(Incidencia::getId));
	    return incidencias;
	}
	

	// Elimina primero las clasificaciones relacionadas y despues la incidencia.
	public static void eliminarIncidencia(Incidencia i) {
		Connection conn = ConexionBD.getConexion();

		try {
			
			//borrar solución antes de borrar la incidencia
	        SolucionDAO.eliminarSolucionPorIncidencia(i);
			
	        PreparedStatement stClasificar = conn.prepareStatement(
				"DELETE FROM clasificar WHERE incidencia = ?");
			stClasificar.setInt(1, i.getId());
			stClasificar.executeUpdate();

			PreparedStatement st = conn.prepareStatement("DELETE FROM INCIDENCIA WHERE id = ?");
			st.setInt(1, i.getId());
			st.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Inserta una incidencia llamando a la funcion creada en la base de datos.
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
			JOptionPane.showMessageDialog(null,
				"No se ha podido crear la incidencia.",
				"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		}

		return exito;
	}

	// Cambia a abierta una incidencia si actualmente esta cerrada.
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
			JOptionPane.showMessageDialog(null,
				"No se ha podido abrir la incidencia.",
				"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		}

		return actualizada;
	}

	// Cambia a cerrada una incidencia si actualmente esta abierta.
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
			JOptionPane.showMessageDialog(null,
				"No se ha podido cerrar la incidencia.",
				"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		}

		return actualizada;
	}
}
