package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Zona;
import util.ConexionBD;

/**
 * Acceso a datos de la tabla zona.
 */
public class ZonaDAO {

    // Carga todas las zonas disponibles para mostrarlas en formularios.
    public static ArrayList<Zona> obtenerZonas() {
        ArrayList<Zona> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM zona";

        try {
            Connection conn = ConexionBD.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Zona z = new Zona(
                    rs.getString("nombre"),
                    rs.getInt("id")
                );
                lista.add(z);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
