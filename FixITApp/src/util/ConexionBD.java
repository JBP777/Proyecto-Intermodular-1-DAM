package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Centraliza los datos de conexion y crea conexiones nuevas a PostgreSQL.
 */
public class ConexionBD {

	// Configuracion alternativa de conexion(KHALED).
//	private static final String nombredb = "incidencias";
//	private static final String puerto = "5432";
//	private static final String url =  "jdbc:postgresql://localhost:5432/incidencias";
//	private static final String user = "postgres";
//	private static final String password = "1234";

	// Configuracion activa de la base de datos(JESUS).
	private static final String nombredb = "incidencias";
	private static final String puerto = "7777";
	private static final String url = "jdbc:postgresql://localhost:" + puerto + "/" + nombredb;
	private static final String user = "postgres";
	private static final String password = "12345";

	// Devuelve una conexion lista para usar por los DAO.
	public static Connection getConexion() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
				"No se ha podido conectar con la base de datos.",
				"Error de conexión", JOptionPane.ERROR_MESSAGE);
		}

		return conn;
	}
}
