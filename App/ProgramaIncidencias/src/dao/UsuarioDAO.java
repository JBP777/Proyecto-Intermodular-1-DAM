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
		// contenedor de los usuarios de la base de datos
	public static ArrayList<Usuario> obtenerUsuarios(){
			
			Connection conn = ConexionBD.getConexion();
			
			//campos usuario
			String nombreUsuario,email,contrasenya, fechaRegistro;
			
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			
			try {
				// consulta usada para seleccionar todos los usuario de la tabla 
				PreparedStatement st = conn.prepareStatement("SELECT nombre_usuario, email, fecha_registro, contrasenya FROM usuario");
				// ejecutar la consulta
				ResultSet rs = st.executeQuery();
				
				
				// bucle para ir creando los usuarios y añadirlos al contenedor
				while(rs.next()) {
					nombreUsuario = rs.getString(1);
					email = rs.getString(2);
					fechaRegistro = rs.getString(3);
					contrasenya = rs.getString(4);
					
					usuarios.add(new Usuario(nombreUsuario,email,contrasenya,fechaRegistro));
				}
				
				// importante cerrar la conexion siempre
				conn.close();
			} catch (SQLException e) {
				// modificar? esto es para ver que error da la base de datos
				System.out.println(e);
			}
			
			return usuarios;
	}
	
	
	public static void insertarUsuario(Usuario u) {
		Connection conn = ConexionBD.getConexion();
		
		try {
			
			//consulta usada
			PreparedStatement ps = conn.prepareStatement("INSERT INTO usuario (nombre_usuario,email,fecha_registro,contrasenya) VALUES (?,?,?,?)");
			// se rellenan con campos '?' de la consulta
			ps.setString(1, u.getNombreUsuario());
			ps.setString(2, u.getEmail());
			
			// esto es para poder insertar la fecha en la tabla usuario
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd"); // <-- Importante que la fecha del usuario tenga este formato
			LocalDate fecha = LocalDate.parse(u.getFechaRegistro(), formatter);

			
			
			ps.setDate(3, java.sql.Date.valueOf(fecha));
			ps.setString(4, u.getContrasenya());
			
			// se ejecuta la consulta y se cierra		
			ps.executeUpdate(); 
			conn.close();
		} catch (SQLException e) {
			// modificar?
			System.out.println("NO SE HA PODIDO INSERTAR EL NUEVO USUARIO"+ e);
		}
		
	}
	
	public static Usuario obtenerUsuario(String usuario, String passwd) {
		
		Connection conn = ConexionBD.getConexion();
		try {
			// consulta 
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuario WHERE nombre_usuario = ? AND cotrasenya = ?");
			
			// campos '?'
			ps.setString(1, usuario);
			ps.setString(2,passwd);
			
			// ejecutar consulta
			ResultSet rs = ps.executeQuery();
			
			// si devuelve algo se crea el objeto del usuario
			if(rs.next()) {
				String nombreUsuario = rs.getString(1);
				String email = rs.getString(2);
				String fechaRegistro = rs.getString(3);
				String contrasenya = rs.getString(4);
				
				return new Usuario(nombreUsuario,email,contrasenya,fechaRegistro);
			}
			
			
		}catch(SQLException e ) {
			//modificar??
			System.out.println(e);
		}
		return null;
	}
	
	
	
	// Sobrecarga del metodo obtenerUsuario para usarlo solo con el nombre. Usado para el apartado de registro.
	public static Usuario obtenerUsuario(String usuario) {
		
		Connection conn = ConexionBD.getConexion();
		try {
			// consulta 
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuario WHERE nombre_usuario = ?");
			
			// campos '?'
			ps.setString(1, usuario);
			
			// ejecutar consulta
			ResultSet rs = ps.executeQuery();
			
			// si devuelve algo se crea el objeto del usuario
			if(rs.next()) {
				String nombreUsuario = rs.getString(1);
				String email = rs.getString(2);
				String fechaRegistro = rs.getString(3);
				String contrasenya = rs.getString(4);
				
				return new Usuario(nombreUsuario,email,contrasenya,fechaRegistro);
			}
			
			
		}catch(SQLException e ) {
			//modificar??
			System.out.println(e);
		}
		return null;
	}



}
