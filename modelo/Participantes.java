package modelo;

public class Participantes {

	//encapsulo los datos que se van a acceder de la bbdd
	private String nombre;
	private String apellido;
	private String sexo;
	private String dorsal;
	private String poblacion;
	
	public Participantes(){
		
		nombre="";
		apellido="";
		sexo="";
		dorsal="";
		poblacion="";
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDorsal() {
		return dorsal;
	}

	public void setDorsal(String dorsal) {
		this.dorsal = dorsal;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	
	
	
	
}
