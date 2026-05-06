package dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.Usuario;
import util.ConexionBD;


public class UsuarioDAO {
		
	public static ArrayList<Usuario> obtenerUsuarios(){
			
			Connection conn = ConexionBD.getConexion();
			
			String nombreUsuario,email,contrasenya, fechaRegistro;
			
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			
			try {
				PreparedStatement st = conn.prepareStatement("SELECT nombre_usuario, email, fecha_registro, contrasenya FROM usuario");
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					nombreUsuario = rs.getString(1);
					email = rs.getString(2);
					fechaRegistro = rs.getString(3);
					contrasenya = rs.getString(4);
					
					usuarios.add(new Usuario(nombreUsuario,email,contrasenya,fechaRegistro));
				}
				conn.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
			return usuarios;
	}
	
	
	public static void insertarUsuario(Usuario u) {
		Connection conn = ConexionBD.getConexion();
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO usuario (nombre_usuario,email,fecha_registro,contrasenya) VALUES (?,?,?,?)");
			ps.setString(1, u.getNombreUsuario());
			ps.setString(2, u.getEmail());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
			LocalDate fecha = LocalDate.parse(u.getFechaRegistro(), formatter);

			
			
			ps.setDate(3, java.sql.Date.valueOf(fecha));
			ps.setString(4, u.getContrasenya());
			
					
			ps.executeUpdate(); 
			conn.close();
		} catch (SQLException e) {
			System.out.println("NO SE HA PODIDO INSERTAR EL NUEVO USUARIO"+ e);
		}
		
	}



}
