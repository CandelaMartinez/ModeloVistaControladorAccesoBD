package controlador;
import java.awt.event.*;

import modelo.*;//uso metodo
import vista.*;//uso desplegable


//evento al abrir el marco
public class ControladorCargaMenusDesplegables extends WindowAdapter{
	
	CargaMenusDesplegables obj= new CargaMenusDesplegables();
	private MarcoAplicacion2 elMarco;

	public ControladorCargaMenusDesplegables (MarcoAplicacion2 elMarco) {
		
		this.elMarco= elMarco;
		
		
	}
	
	
	
	//sobreescribo 
	public void windowOpened(WindowEvent e) {
		
		//ejecutar la consulta sql, construye el resultset rs, rs2
		obj.ejecutaConsultas();
		
		try {
			
			//acceso a campo de clase de CargaPoblacion
			while(obj.rs.next()) {
				
				//acceso a desplegable mediante instancia de MarcoAplicacion2, le agrego el campo de clase que me devuelve la consulta
				elMarco.poblacion.addItem(obj.rs.getString(1));
				
				
				
			}
			
			while(obj.rs2.next()) {
				
				elMarco.sexo.addItem(obj.rs2.getString(1));
				
			}
			
			
			
			
		}catch(Exception e2) {
			
			e2.printStackTrace();
			
		}
		
		
	}
	
	
	
}
