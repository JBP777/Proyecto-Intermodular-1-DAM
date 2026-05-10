package dao;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.util.ArrayList;

import modelo.Categoria;

import util.ConexionBD;

public class CategoriaDAO {
	
	 public ArrayList<Categoria> obtenerTodas() {

	        ArrayList<Categoria> lista = new ArrayList<>();

	        String sql = "SELECT * FROM categoria";

	        try {

	            Connection conn = ConexionBD.getConexion();

	            PreparedStatement ps = conn.prepareStatement(sql);

	            ResultSet rs = ps.executeQuery();

	            while(rs.next()) {

	            	Categoria c = new Categoria(

	            		    rs.getString("nombre"),
	            		    rs.getString("descripcion"),
	            		    rs.getInt("id")

	            		);

	                lista.add(c);

	            }

	        } catch(Exception e) {

	            e.printStackTrace();

	        }

	        return lista;

	    }

}
