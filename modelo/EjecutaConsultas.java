package modelo;

import java.sql.*;

public class EjecutaConsultas {

	private Conexion miConexion;
	private ResultSet rs;

	private PreparedStatement enviaConsultaPob;
	private PreparedStatement enviaConsultaSex;
	private PreparedStatement enviaConsultaPobSex;
	private final String consultaPoblacion = "SELECT * FROM PARTICIPANTES WHERE POBLACION=?";
	private final String consultaSex = "SELECT * FROM PARTICIPANTES WHERE SEXO=?";
	private final String consultaPoblaSex = "SELECT * FROM PARTICIPANTES WHERE POBLACION=? AND SEXO=?";

	public EjecutaConsultas() {

		miConexion = new Conexion();

	}

	// le paso por parametro las elecciones del menu desplegable
	public ResultSet filtraBBDD(String pobla, String sex) {

		Connection accesoBD = miConexion.dameConexion();

		rs = null;

		try {
			// usuario elegio una poblacion, pero no sexo
			if (!pobla.equals("Todos") && sex.equals("Todos")) {

				enviaConsultaPob = accesoBD.prepareStatement(consultaPoblacion);

				enviaConsultaPob.setString(1, pobla);

				rs = enviaConsultaPob.executeQuery();

				// usuario eligio sexo pero no poblacion
			} else if (pobla.equals("Todos") && !sex.equals("Todos")) {

				enviaConsultaSex = accesoBD.prepareStatement(consultaSex);

				enviaConsultaSex.setString(1, sex);

				rs = enviaConsultaSex.executeQuery();

				// usuario eligio ambos
			} else {

				enviaConsultaPobSex = accesoBD.prepareStatement(consultaPoblaSex);

				enviaConsultaPobSex.setString(1, pobla);
				enviaConsultaPobSex.setString(2, sex);

				rs = enviaConsultaPobSex.executeQuery();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return rs;

	}

}
