package modelo;

import java.sql.*;


public class CargaMenusDesplegables {

	Conexion miConexion;
	public ResultSet rs;
	public ResultSet rs2;
	
	public CargaMenusDesplegables() {
		
		miConexion= new Conexion();
		
		
	}
	
	
	/*public String ejecutaConsultas() {
		
		Participantes miParticipante=null;
		
		//uso metodo que establece coneccion con la bd maraton
		Connection accesoBD= miConexion.dameConexion();
		
		
		try {
			
			
			Statement poblacion= accesoBD.createStatement();
			
			rs= poblacion.executeQuery("SELECT DISTINCTROW POBLACION FROM PARTICIPANTES");
			
			
			while(rs.next()) {
				
				
				miParticipante= new Participantes();
				
				miParticipante.setPoblacion(rs.getString(1));
				
				return miParticipante.getPoblacion();
			}
			
			
			
			rs.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
		return miParticipante.getPoblacion();
		
	}*/
	
	
	public ResultSet ejecutaConsultas() {
		
		
		
		//uso metodo de clase conexion, esta en el mismo paquete asi que no tengo que importar
		Connection accesoBD= miConexion.dameConexion();
		
		try {
			
			//creo un statement relacionado a la conexion 
			Statement poblacion= accesoBD.createStatement();
			
			Statement sexo= accesoBD.createStatement();
			
			//establezco consulta sql en el resultset y la ejecuto
			rs= poblacion.executeQuery("SELECT DISTINCTROW POBLACION FROM PARTICIPANTES");
			
			rs2=sexo.executeQuery("SELECT DISTINCTROW SEXO FROM PARTICIPANTES");
			
		
			
			//devuelvo resulset con consulta guardada dentro
			return rs;
			
			
		
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		//si hay algun error y entra en el catch devuelve null
		return null;
		
		
		
		
	}
	
	
}
