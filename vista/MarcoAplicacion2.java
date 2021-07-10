package vista;

import java.awt.*;


import javax.swing.*;

import controlador.*;



public class MarcoAplicacion2 extends JFrame{
	public JComboBox poblacion, sexo;
	public JTextArea resultado;
	
	public MarcoAplicacion2() {

		// .................................................................marco con
		// BorderLayout
		setTitle("consulta BBDD modulada");
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
		
		add(botonConsulta,BorderLayout.SOUTH);
		
		//pongo el boton a la escucha del evento creado en la clase ControladorBotonConsulta y le paso el propio marco como parametro
		botonConsulta.addActionListener(new ControladorBotonConsulta(this));
		
		//..............................................................pongo el marco a la escucha
		addWindowListener(new ControladorCargaMenusDesplegables(this));
	
	}
}