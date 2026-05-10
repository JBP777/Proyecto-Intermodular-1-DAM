package util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;



public class ConexionBD {

	// CONEXION DE KHALED
//	private static final String nombredb = "incidencias";
//	private static final String puerto = "5432";
//	private static final String url =  "jdbc:postgresql://localhost:5432/incidencias";
//	private static final String user = "postgres";
//	private static final String password = "1234";
	
	// CONEXION DE JESÚS
	private static final String nombredb = "incidencias";
	private static final String puerto = "7777";
	private static final String url =  "jdbc:postgresql://localhost:"+puerto+"/"+nombredb;
	private static final String user = "postgres";
	private static final String password = "12345";
	
			
	public static Connection getConexion() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
           
        } catch (SQLException e) {
            System.out.println("ERROR CON LA CONEXION A LA BASE DE DATOS: "+e);
        }
        return conn;
    }
	
	
	}


	


