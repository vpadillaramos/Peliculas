package com.vpr.pojo;

import java.io.Serializable;
import java.util.Date;

public class Pelicula implements Serializable{
	
	
	
	//Atributos
	private int id;
	private String titulo;
	private String sinopsis;
	private static String[] genero = {"", "Drama"};
	private static String[] rating = {"", "A", "7", "12", "16", "18", "X"};
	private String director;
	private Date fechaEstreno;
	private Date fechaVista;
	private int duracion;
	private String portada;
	private String[] imagenes;
	private float nota;
	private String notas;
	private boolean vista;
	
	private static int cont;
	//Constructor
	public Pelicula() {
		cont++;
		id = cont;
	}
	
	//Metodos
	public static String[] getGenero() {
		return genero;
	}
	
	public static String[] getRating() {
		return rating;
	}
}
