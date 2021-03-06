package com.vpr.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Pelicula implements Serializable{
	
	//Atributos
	private int id;
	private String titulo;
	private String sinopsis;
	private static String[] aGenero = {"", "Drama"};
	private String genero;
	private static String[] aRating = {"", "A", "7", "12", "16", "18", "X"};
	private String rating;
	private String director;
	private Date fechaEstreno;
	private Date fechaVista;
	private int duracion;
	private String portada;
	private float nota;
	private String notas;
	private boolean vista;
	
	//Constructor
	public Pelicula() {
		
	}
	
	//Metodos
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static String[] getAGenero() {
		return aGenero;
	}
	
	public static String[] getARating() {
		return aRating;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(int i) {
		genero = aGenero[i];
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(int i) {
		rating = aRating[i];
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Date getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public Date getFechaVista() {
		return fechaVista;
	}

	public void setFechaVista(Date fechaVista) {
		this.fechaVista = fechaVista;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public boolean isVista() {
		return vista;
	}

	public void setVista(boolean vista) {
		this.vista = vista;
	}

	@Override
	public String toString() {
		String aux;
		if(vista)
			aux = "[V]";
		else
			aux = "[X]";
		return aux + " " + titulo;
	}
}
