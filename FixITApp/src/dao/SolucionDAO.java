package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Incidencia;
import modelo.Usuario;
import util.ConexionBD;

/**
 * Acceso a datos de las soluciones propuestas para incidencias.
 */
public class SolucionDAO {

    // Inserta la solucion y la relaciona con el colaborador y la incidencia.
    public static boolean insertarSolucion(String descripcion, Usuario u, Incidencia i) {
        Connection conn = ConexionBD.getConexion();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT COALESCE(MAX(id), 0) + 1 FROM SOLUCION");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int nuevoId = rs.getInt(1);

            ps = conn.prepareStatement("INSERT INTO SOLUCION (id, es_aceptada, descripcion) VALUES (?, true, ?)");
            ps.setInt(1, nuevoId);
            ps.setString(2, descripcion);
            ps.executeUpdate();

            ps = conn.prepareStatement("INSERT INTO RESOLVER (colaborador, incidencia, solucion) VALUES (?, ?, ?)");
            ps.setString(1, u.getNombreUsuario());
            ps.setInt(2, (int) i.getId());
            ps.setInt(3, nuevoId);
            ps.executeUpdate();

            conn.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "No se ha podido enviar la solución.",
                "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
 
	    // Obtiene la descripcion de la solucion asociada a una incidencia concreta.
	 // Devuelve null si la incidencia no tiene solucion registrada.
	 public static String obtenerDescripcionPorIncidencia(Incidencia i) {
	     Connection conn = ConexionBD.getConexion();
	     String descripcion = null;
	
	     try {
	         PreparedStatement ps = conn.prepareStatement(
	        		 "SELECT s.descripcion FROM SOLUCION s, RESOLVER r "
	        				 + "WHERE r.solucion = s.id AND r.incidencia = ?");
	         ps.setInt(1, i.getId());
	         ResultSet rs = ps.executeQuery();
	
	         if (rs.next()) {
	             descripcion = rs.getString("descripcion");
	         }
	
	         conn.close();
	     } catch (SQLException e) {
	         JOptionPane.showMessageDialog(null,
	             "No se ha podido cargar la solución.",
	             "Error de base de datos", JOptionPane.ERROR_MESSAGE);
	     }
	
	     return descripcion;
	 }
	
	//Obtiene el nombre del colaborador que resolvio la incidencia indicada.
	//Devuelve null si no hay ningun colaborador registrado.
	public static String obtenerColaboradorPorIncidencia(Incidencia i) {
	  Connection conn = ConexionBD.getConexion();
	  String colaborador = null;
	
	  try {
	      PreparedStatement ps = conn.prepareStatement(
	          "SELECT r.colaborador FROM RESOLVER r " +
	          "WHERE r.incidencia = ?");
	      ps.setInt(1, i.getId());
	      ResultSet rs = ps.executeQuery();
	
	      if (rs.next()) {
	          colaborador = rs.getString("colaborador");
	      }
	
	      conn.close();
	  } catch (SQLException e) {
	      JOptionPane.showMessageDialog(null,
	          "No se ha podido cargar el colaborador.",
	          "Error de base de datos", JOptionPane.ERROR_MESSAGE);
	  }
	
	  return colaborador;
}

	// Elimina la solucion y su relacion RESOLVER asociadas a la incidencia indicada.
	// Se llama al reabrir una incidencia para que el colaborador pueda registrar una nueva.
	// Borra primero RESOLVER (FK) y luego SOLUCION para evitar violar la restriccion de clave foranea.
	public static boolean eliminarSolucionPorIncidencia(Incidencia i) {
	    Connection conn = ConexionBD.getConexion();

	    try {
	        // Paso 1 — obtener el id de la solucion ligada a esta incidencia via RESOLVER.
	        PreparedStatement psSelect = conn.prepareStatement(
	            "SELECT solucion FROM RESOLVER WHERE incidencia = ?");
	        psSelect.setInt(1, (int) i.getId());
	        ResultSet rs = psSelect.executeQuery();

	        // Si no hay solucion registrada no hay nada que borrar.
	        if (!rs.next()) {
	            conn.close();
	            return false;
	        }

	        int idSolucion = rs.getInt("solucion");

	        // Paso 2 — borrar primero la fila de RESOLVER (tiene FK hacia SOLUCION).
	        PreparedStatement psResolver = conn.prepareStatement(
	            "DELETE FROM RESOLVER WHERE incidencia = ?");
	        psResolver.setInt(1, (int) i.getId());
	        psResolver.executeUpdate();

	        // Paso 3 — borrar la solucion de la tabla SOLUCION.
	        PreparedStatement psSolucion = conn.prepareStatement(
	            "DELETE FROM SOLUCION WHERE id = ?");
	        psSolucion.setInt(1, idSolucion);
	        psSolucion.executeUpdate();

	        conn.close();
	        return true;

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null,
	            "No se ha podido eliminar la solución.",
	            "Error de base de datos", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	}


}
