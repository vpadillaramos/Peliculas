package com.vpr.principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vpr.pojo.Pelicula;

public class Modelo {
	
	//Atributos
	private HashMap<String, Pelicula> hmPeliculas;
	
	//Constructor
	public Modelo() throws FileNotFoundException, ClassNotFoundException, IOException {
		if(new File("peliculas.dat").exists())
			leerDisco();
		else
			hmPeliculas = new HashMap<>();
	}
	
	//Metodos
	public void anadirPelicula(Pelicula pelicula) throws FileNotFoundException, IOException {
		hmPeliculas.put(pelicula.toString(), pelicula);
		guardarDisco();
	}
	
	public void borrarPelicula(Pelicula pelicula) {
		
	}
	
	public void modificarPelicual(Pelicula pelicula) {
		
	}
	
	public ArrayList<Pelicula> getPeliculas() {
		return null;
	}
	
	public void guardarDisco() throws FileNotFoundException, IOException {
		ObjectOutputStream serializador = null;
		
		serializador = new ObjectOutputStream(new FileOutputStream("peliculas.dat"));
		serializador.writeObject(hmPeliculas);
		serializador.close();
	}
	
	public void leerDisco() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream deserializador = null;
		
		deserializador = new ObjectInputStream(new FileInputStream("peliculas.dat"));
		hmPeliculas = (HashMap<String, Pelicula>) deserializador.readObject();
		deserializador.close();
	}
}
