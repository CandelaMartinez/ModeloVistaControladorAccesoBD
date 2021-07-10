package controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.*;//usa metodo controlador boton consulta
import vista.*;//recibe marco

//gestiona el evento click del boton
public class ControladorBotonConsulta implements ActionListener {

	private MarcoAplicacion2 elMarco;
	private EjecutaConsultas obj = new EjecutaConsultas();
	private ResultSet resultadoConsulta;

	public ControladorBotonConsulta(MarcoAplicacion2 elMarco) {

		this.elMarco = elMarco;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// almaceno la seleccion del desplegable
		String selecPobla = (String) elMarco.poblacion.getSelectedItem();

		String selecSex = (String) elMarco.sexo.getSelectedItem();

		resultadoConsulta = obj.filtraBBDD(selecPobla, selecSex);

		// resetea el cuadro de texto a blanco
		elMarco.resultado.setText("");

		try {

			while (resultadoConsulta.next()) {

				elMarco.resultado.append(resultadoConsulta.getString(1));

				elMarco.resultado.append(", ");

				elMarco.resultado.append(resultadoConsulta.getString(2));

				elMarco.resultado.append(", ");

				elMarco.resultado.append(resultadoConsulta.getString(3));

				elMarco.resultado.append("\n");

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}

}
