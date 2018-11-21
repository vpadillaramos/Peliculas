package com.vpr.pojo;

import java.util.Date;

public class Pelicula {
	
	
	
	//Atributos
	private String titulo;
	private String sinopsis;
	private String[] genero;
	private enum Rating{
		RA, R7, R2, R16, R18, RX;
	}
	private String director;
	private Date fechaEstreno;
	private Date fechaVista;
	private int duracion;
	private String portada;
	private String[] imagenes;
	private float nota;
	private String notas;
	private boolean vista;
	
	
	//Constructor
	public Pelicula() {
		
	}
	
	//Metodos
}
