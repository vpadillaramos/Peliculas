package com.vpr.principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.vpr.pojo.Pelicula;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class Vista extends JFrame {
	//Componentes
	public JLabel lbTitulo;
	public JLabel lbSinopsis;
	public JLabel lbGenero;
	public JLabel lbRating;
	public JLabel lbDirector;
	public JLabel lbFechaEstreno;
	public JLabel lbFechaVista;
	public JLabel lbDuracion;
	public JLabel lbPortada;
	public JLabel lbImagenes;
	public JLabel lblNota;
	public JLabel lblNotas;
	public JTextField tfTitulo;
	public JTextField tfDirector;
	public JTextField tfDuracion;
	public JTextField tfNota;
	public JComboBox cbGenero;
	public JComboBox cbRating;
	public JButton btNueva;
	public JButton btEditar;
	public JButton btBorrar;
	public JButton btCancelar;
	public JButton btGuardar;
	public JButton btEscribirSinopsis;
	public JButton btNotas;
	public JScrollPane scrollPane;
	public JList<Pelicula> listPeliculas;
	public DefaultListModel<Pelicula> modeloPelicula;
	
	
	public Vista() {
		setTitle("Películas");
		setSize(700, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lbTitulo = new JLabel("T\u00EDtulo");
		lbTitulo.setBounds(10, 31, 46, 14);
		getContentPane().add(lbTitulo);
		
		lbSinopsis = new JLabel("Sinopsis");
		lbSinopsis.setBounds(10, 56, 46, 14);
		getContentPane().add(lbSinopsis);
		
		lbGenero = new JLabel("G\u00E9nero");
		lbGenero.setBounds(10, 81, 46, 14);
		getContentPane().add(lbGenero);
		
		lbRating = new JLabel("Rating de edad");
		lbRating.setBounds(10, 107, 99, 14);
		getContentPane().add(lbRating);
		
		lbDirector = new JLabel("Director/a");
		lbDirector.setBounds(10, 142, 69, 14);
		getContentPane().add(lbDirector);
		
		lbFechaEstreno = new JLabel("Fecha de estreno");
		lbFechaEstreno.setBounds(10, 171, 111, 14);
		getContentPane().add(lbFechaEstreno);
		
		lbFechaVista = new JLabel("Fecha vista");
		lbFechaVista.setBounds(10, 199, 84, 14);
		getContentPane().add(lbFechaVista);
		
		lbDuracion = new JLabel("Duraci\u00F3n");
		lbDuracion.setBounds(10, 231, 69, 14);
		getContentPane().add(lbDuracion);
		
		lbPortada = new JLabel("Portada");
		lbPortada.setBounds(10, 264, 46, 14);
		getContentPane().add(lbPortada);
		
		lbImagenes = new JLabel("Im\u00E1genes");
		lbImagenes.setBounds(10, 301, 69, 14);
		getContentPane().add(lbImagenes);
		
		lblNota = new JLabel("Nota");
		lblNota.setBounds(10, 326, 46, 14);
		getContentPane().add(lblNota);
		
		lblNotas = new JLabel("Notas");
		lblNotas.setBounds(10, 363, 46, 14);
		getContentPane().add(lblNotas);
		
		tfTitulo = new JTextField();
		tfTitulo.setBounds(117, 28, 86, 20);
		getContentPane().add(tfTitulo);
		tfTitulo.setColumns(10);
		
		tfDirector = new JTextField();
		tfDirector.setBounds(117, 139, 86, 20);
		getContentPane().add(tfDirector);
		tfDirector.setColumns(10);
		
		tfDuracion = new JTextField();
		tfDuracion.setBounds(117, 228, 86, 20);
		getContentPane().add(tfDuracion);
		tfDuracion.setColumns(10);
		
		tfNota = new JTextField();
		tfNota.setBounds(117, 323, 86, 20);
		getContentPane().add(tfNota);
		tfNota.setColumns(10);
		
		cbGenero = new JComboBox();
		cbGenero.setBounds(117, 78, 86, 20);
		getContentPane().add(cbGenero);
		
		cbRating = new JComboBox();
		cbRating.setBounds(119, 104, 84, 20);
		getContentPane().add(cbRating);
		
		btNueva = new JButton("Nueva");
		btNueva.setActionCommand("nueva");
		btNueva.setBounds(277, 27, 89, 23);
		getContentPane().add(btNueva);
		
		btEditar = new JButton("Editar");
		btEditar.setActionCommand("editar");
		btEditar.setBounds(277, 72, 89, 23);
		getContentPane().add(btEditar);
		
		btBorrar = new JButton("Borrar");
		btBorrar.setActionCommand("borrar");
		btBorrar.setBounds(277, 117, 89, 23);
		getContentPane().add(btBorrar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setActionCommand("cancelar");
		btCancelar.setBounds(277, 167, 89, 23);
		getContentPane().add(btCancelar);
		
		btGuardar = new JButton("Guardar");
		btGuardar.setActionCommand("guardar");
		btGuardar.setBounds(277, 214, 89, 23);
		getContentPane().add(btGuardar);
		
		btEscribirSinopsis = new JButton("Escribir sinopsis");
		btEscribirSinopsis.setActionCommand("escribirSinopsis");
		btEscribirSinopsis.setBounds(104, 52, 120, 23);
		getContentPane().add(btEscribirSinopsis);
		
		btNotas = new JButton("Notas");
		btNotas.setActionCommand("notas");
		btNotas.setBounds(114, 359, 89, 23);
		getContentPane().add(btNotas);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(482, 31, 202, 291);
		getContentPane().add(scrollPane);
		
		listPeliculas = new JList<Pelicula>();
		scrollPane.setViewportView(listPeliculas);
		modeloPelicula = new DefaultListModel<Pelicula>();
		listPeliculas.setModel(modeloPelicula);
		
		setVisible(true);
	}
}
