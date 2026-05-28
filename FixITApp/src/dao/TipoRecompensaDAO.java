package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.TipoRecompensa;
import util.ConexionBD;

/**
 * Acceso a datos de los tipos de recompensa.
 */
public class TipoRecompensaDAO {

	// Obtiene todos los tipos de recompensa registrados.
	public ArrayList<TipoRecompensa> obtenerTodas() {
		ArrayList<TipoRecompensa> lista = new ArrayList<>();
		String sql = "SELECT * FROM tiporecompensa";

		try {
			Connection conn = ConexionBD.getConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TipoRecompensa tr = new TipoRecompensa(
					rs.getString("nombre"),
					rs.getString("descripcion"),
					rs.getInt("id")
				);
				lista.add(tr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
}
