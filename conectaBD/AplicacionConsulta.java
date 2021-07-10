package conectaBD;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

//los desplegables se tienen que cargar dinamicamente conectandose con la bbdd

public class AplicacionConsulta {

	public static void main(String[] args) {

		JFrame mimarco = new MarcoAplicacion();

		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mimarco.setVisible(true);

	}

}

class MarcoAplicacion extends JFrame {

	private JComboBox poblacion, sexo;
	private JTextArea resultado;
	private PreparedStatement enviaConsultaPoblacion;
	private PreparedStatement enviaConsultaSexo;
	private PreparedStatement enviaConsultaPoblaSex;
	private final String consultaPoblacion = "SELECT * FROM PARTICIPANTES WHERE POBLACION=?";
	private final String consultaSexo = "SELECT * FROM PARTICIPANTES WHERE SEXO=?";
	private final String consultaPoblaSex = "SELECT * FROM PARTICIPANTES WHERE POBLACION=? AND SEXO=?";
	private Connection miConexion;

	public MarcoAplicacion() {

		// .................................................................marco con
		// BorderLayout
		setTitle("consulta BBDD");
		setBounds(500, 300, 400, 400);
		setLayout(new BorderLayout());

		// ................................................................lamina menus
		// con FlowLayout norte
		JPanel menus = new JPanel();
		menus.setLayout(new FlowLayout());

		// ....................................desplegable dentro de menus
		poblacion = new JComboBox();
		poblacion.setEditable(false);
		poblacion.addItem("Todos");
		menus.add(poblacion);

		sexo = new JComboBox();
		sexo.setEditable(false);
		sexo.addItem("Todos");
		menus.add(sexo);

		add(menus, BorderLayout.NORTH);
		// ...............................................................TextArea
		// dentro de marco centro
		resultado = new JTextArea();
		resultado.setEditable(false);
		add(resultado);

		add(resultado, BorderLayout.CENTER);

		// ...............................................................boton dentro
		// de marco sur
		JButton botonConsulta = new JButton("Consulta");

		botonConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ejecutaConsulta();

			}

		});

		add(botonConsulta, BorderLayout.SOUTH);

		// ..............................................................CONEXION CON
		// BBDD

		try {

			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/maraton", "root", "");

			Statement sentencia = miConexion.createStatement();

			// .........................................carga desplegable poblacion
			String consulta = "SELECT DISTINCTROW POBLACION FROM PARTICIPANTES ";

			ResultSet rs = sentencia.executeQuery(consulta);

			while (rs.next()) {

				// System.out.println(rs.getString("POBLACION"));
				poblacion.addItem(rs.getString("POBLACION"));

			}

			rs.close();

			// .........................................carga desplegable sexo
			consulta = "SELECT DISTINCTROW SEXO FROM PARTICIPANTES ";

			rs = sentencia.executeQuery(consulta);

			while (rs.next()) {

				// System.out.println(rs.getString("SEXO"));
				sexo.addItem(rs.getString("SEXO"));

			}

			rs.close();

		} catch (Exception e) {

			System.out.println("No conecta");
			e.printStackTrace();

		}

	}

	// ................................................................consulta preparada
	
	private void ejecutaConsulta() {

		ResultSet rs=null;

		try {
			//resetee el cuadro de texto
			resultado.setText("");
			
			
			// seleccionado en desplegable
			String selectPob = (String) poblacion.getSelectedItem();
			String selectSex= (String) sexo.getSelectedItem();

			//solo elige poblacion
			if(!selectPob.equals("Todos") && sexo.equals("Todos")) {
				
			// preparedStatement
			enviaConsultaPoblacion = miConexion.prepareStatement(consultaPoblacion);

			// poblacion=resultado de eleccion en desplegable
			enviaConsultaPoblacion.setString(1, selectPob);

			//ejecuta consulta
			rs = enviaConsultaPoblacion.executeQuery();
			
			
			
			//solo elige sexo
			}else if (selectPob.equals("Todos")&& !sexo.equals("Todos")) {
				
				enviaConsultaSexo= miConexion.prepareStatement(consultaSexo);
				
				enviaConsultaSexo.setString(1, selectSex);
				
				rs= enviaConsultaSexo.executeQuery();
				
				
				
				}else if((!selectPob.equals("Todos")&& !sexo.equals("Todos"))) {
					
					enviaConsultaPoblaSex= miConexion.prepareStatement(consultaPoblaSex);
					
					enviaConsultaPoblaSex.setString(1, selectPob);
					enviaConsultaPoblaSex.setString(2, selectSex);
					
					rs=enviaConsultaPoblaSex.executeQuery();
					
					
				}

			while (rs.next()) {

				resultado.append("--NOMBRE: " + rs.getString(2));
				resultado.append(", ");
				resultado.append("--APELLIDO: " + rs.getString(3));
				resultado.append(", ");
				resultado.append("--SEXO: " + rs.getString("SEXO"));
				resultado.append(", ");
				resultado.append("--DORSAL: " + rs.getString("DORSAL"));
				resultado.append(", ");
				resultado.append("--POBLACION: " + rs.getString("POBLACION"));

				resultado.append("\n");

			}

		} catch (Exception e) {

		}

	}

}
