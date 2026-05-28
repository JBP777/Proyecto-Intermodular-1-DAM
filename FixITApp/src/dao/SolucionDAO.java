package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Incidencia;
import modelo.Usuario;
import util.ConexionBD;

public class SolucionDAO {

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
            System.out.println("NO SE HA PODIDO INSERTAR LA SOLUCION: " + e);
            return false;
        }
    }
}