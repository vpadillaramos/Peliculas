package com.vpr.principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;

public class Vista extends JFrame {
	//Componentes
	//Panel
	JPanel panel = new JPanel();
	
	//Etiquetas
	public JLabel lbTitulo = new JLabel("T�tulo");
	public JLabel lbSinopsis = new JLabel("T�tulo");
	public JLabel lbGenero = new JLabel("T�tulo");
	public JLabel lbRating = new JLabel("T�tulo");
	public JLabel lbDirector = new JLabel("T�tulo");
	public JLabel lbFechaEstreno = new JLabel("T�tulo");
	public JLabel lbFehcaVista = new JLabel("T�tulo");
	public JLabel lbDuracion = new JLabel("T�tulo");
	public JLabel lbPortada = new JLabel("T�tulo");
	public JLabel lbImagenes = new JLabel("T�tulo");
	public JLabel lbNota = new JLabel("T�tulo");
	public JLabel lbNotas = new JLabel("T�tulo");
	
	//Chekbox
	public JCheckBox chbVista = new JCheckBox("Vista");
	
	//Campos
	public JTextField tfTitulo = new JTextField();
	public JTextField tfDirector = new JTextField();
	public JTextField tfDuracion = new JTextField();
	public JTextField tfNota = new JTextField();
	
	//Desplegables
	public JComboBox cbGenero = new JComboBox();
	public JComboBox cbRating = new JComboBox();
	
	//areas
	public JTextArea taSinopsis = new JTextArea();
	public JTextArea taNotas = new JTextArea();
	
	
	public Vista() {
		setTitle("Pel�culas");
		setSize(700,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		SpringLayout spl = new SpringLayout();
		
		//Adicion de componentes
		add(panel);
		panel.setBackground(Color.BLACK);
		panel.setLayout(spl);
		
		setVisible(true);
		repaint();
	}

}