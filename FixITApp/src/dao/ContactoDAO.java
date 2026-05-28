package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Contacto;
import util.ConexionBD;

/**
 * Acceso a datos de mensajes enviados al administrador.
 */
public class ContactoDAO {

	// Carga todos los mensajes de contacto para la bandeja del administrador.
	public static ArrayList<Contacto> obtenerMensajesContacto() {
		Connection conn = ConexionBD.getConexion();
		ArrayList<Contacto> mensajes = new ArrayList<Contacto>();

		try {
			PreparedStatement st = conn.prepareStatement("SELECT  nombre, email, asunto, mensaje FROM CONTACTO");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String asunto = rs.getString("asunto");
				String mensaje = rs.getString("mensaje");

				mensajes.add(new Contacto(nombre, email, asunto, mensaje));
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return mensajes;
	}

	// Inserta el mensaje escrito por el usuario en la tabla CONTACTO.
	public static void enviarMensaje(Contacto c) {
		Connection conn = ConexionBD.getConexion();

		try {
			PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO Contacto (nombre, email, asunto, mensaje) VALUES (?,?,?,?)");
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getEmail());
			ps.setString(3, c.getAsunto());
			ps.setString(4, c.getMensaje());

			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			System.out.println("NO SE HA PODIDO ENVIAR EL MENSAJE" + e);
		}
	}
}
