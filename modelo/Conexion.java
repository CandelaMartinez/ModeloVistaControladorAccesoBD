package modelo;

import java.sql.*;

public class Conexion {
	
	public Conexion() {
		
		
		
	}
	
	//devuelve el objeto Connection que conecta a la bbdd maraton
	public Connection dameConexion() {
		
		 Connection miConexion=null;
		
		try {
		
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/maraton", "root", "");
		
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return miConexion;
		
		
	}
	

}
