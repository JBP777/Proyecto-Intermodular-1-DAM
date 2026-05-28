package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Usuario;
import util.ConexionBD;

/**
 * Acceso a datos y operaciones principales sobre usuarios.
 */
public class UsuarioDAO {

	// Obtiene todos los usuarios registrados en la base de datos.
	public static ArrayList<Usuario> obtenerUsuarios() {
		Connection conn = ConexionBD.getConexion();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			PreparedStatement st = conn.prepareStatement(
				"SELECT nombre_usuario, email, fecha_registro, contrasenya FROM usuario");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String nombreUsuario = rs.getString(1);
				String email = rs.getString(2);
				String fechaRegistro = rs.getString(3);
				String contrasenya = rs.getString(4);

				usuarios.add(new Usuario(nombreUsuario, email, contrasenya, fechaRegistro));
			}

			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
				"No se han podido cargar los usuarios.",
				"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		}

		return usuarios;
	}

	// Inserta un usuario nuevo usando el formato de fecha esperado por la BD.
	public static void insertarUsuario(Usuario u) {
		Connection conn = ConexionBD.getConexion();

		try {
			PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO usuario (nombre_usuario,email,fecha_registro,contrasenya) VALUES (?,?,?,?)");
			ps.setString(1, u.getNombreUsuario());
			ps.setString(2, u.getEmail());

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
			LocalDate fecha = LocalDate.parse(u.getFechaRegistro(), formatter);

			ps.setDate(3, java.sql.Date.valueOf(fecha));
			ps.setString(4, u.getContrasenya());
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
				"No se ha podido registrar el usuario.",
				"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Busca un usuario por nombre y contrasena para el inicio de sesion.
	public static Usuario obtenerUsuario(String usuario, String passwd) {
		Connection conn = ConexionBD.getConexion();

		try {
			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM usuario WHERE nombre_usuario = ? AND contrasenya = ?");
			ps.setString(1, usuario);
			ps.setString(2, passwd);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nombreUsuario = rs.getString(1);
				String email = rs.getString(2);
				String fechaRegistro = rs.getString(3);
				String contrasenya = rs.getString(4);

				return new Usuario(nombreUsuario, email, contrasenya, fechaRegistro);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
				"No se ha podido comprobar el usuario.",
				"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		}

		return null;
	}

	// Sobrecarga para comprobar si existe un nombre de usuario durante el registro.
	public static Usuario obtenerUsuario(String usuario) {
		Connection conn = ConexionBD.getConexion();

		try {
			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM usuario WHERE nombre_usuario = ?");
			ps.setString(1, usuario);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nombreUsuario = rs.getString(1);
				String email = rs.getString(2);
				String fechaRegistro = rs.getString(3);
				String contrasenya = rs.getString(4);

				return new Usuario(nombreUsuario, email, contrasenya, fechaRegistro);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
				"No se ha podido comprobar el usuario.",
				"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		}

		return null;
	}

	// Devuelve estadisticas de reportador y colaborador para el perfil.
	public static String[] obtenerEstadisticas(Usuario u) {
		Connection conn = ConexionBD.getConexion();
		String[] stats = {"0", "0", "0.00"};

		try {
			PreparedStatement st = conn.prepareStatement(
				"SELECT r.total_creadas, c.total_resueltas, c.valoracion_media " +
				"FROM REPORTADOR r, COLABORADOR c " +
				"WHERE r.usuario = ? AND c.usuario = ?");
			st.setString(1, u.getNombreUsuario());
			st.setString(2, u.getNombreUsuario());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				stats[0] = String.valueOf(rs.getInt("total_creadas"));
				stats[1] = String.valueOf(rs.getInt("total_resueltas"));
				stats[2] = String.valueOf(rs.getDouble("valoracion_media"));
			}

			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
				"No se han podido cargar las estadísticas.",
				"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		}

		return stats;
	}

	// Ejecuta la funcion de BD que elimina el usuario y sus datos relacionados.
	public static void eliminarUsuario(Usuario u) {
		Connection conn = ConexionBD.getConexion();

		try {
			PreparedStatement st = conn.prepareStatement("SELECT eliminar_usuario(?)");
			st.setString(1, u.getNombreUsuario());
			st.executeQuery();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
