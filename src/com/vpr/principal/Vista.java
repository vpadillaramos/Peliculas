package com.vpr.principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class Vista extends JFrame {
	//Componentes
	JPanel panel = new JPanel();
	public JLabel lbTitulo;
	
	
	public Vista() {
		setTitle("Películas");
		setSize(700,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Título");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTitulo.setForeground(Color.WHITE);
		panel.add(lbTitulo);
		
		setVisible(true);
		repaint();
	}

}