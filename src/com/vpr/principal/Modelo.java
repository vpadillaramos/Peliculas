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

import javax.swing.JOptionPane;

import com.vpr.pojo.Pelicula;

public class Modelo {
	
	//Atributos
	private HashMap<Integer, Pelicula> hmPeliculas;
	private Pelicula peliculaBorrada = null;
	
	//Constructor
	public Modelo() throws FileNotFoundException, ClassNotFoundException, IOException {
		if(new File("peliculas.dat").exists())
			leerDisco();
		else
			hmPeliculas = new HashMap<Integer, Pelicula>();
	}
	
	//Metodos
	public void guardarPelicula(Pelicula pelicula) throws FileNotFoundException, IOException {
		int i = 0;
		do {
			i++;
		}while(hmPeliculas.containsKey(i));
		pelicula.setId(i);
		hmPeliculas.put(pelicula.getId(), pelicula);
		guardarDisco();
	}
	
	public void borrarPelicula(Pelicula pelicula) throws FileNotFoundException, IOException {
		peliculaBorrada = pelicula;
		hmPeliculas.remove(pelicula.getId(), pelicula);
		guardarDisco();
	}
	
	public void modificarPelicula(Pelicula pelicula) {
		
	}
	
	public ArrayList<Pelicula> getPeliculas() {
		return new ArrayList<Pelicula>(hmPeliculas.values());
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
		hmPeliculas = (HashMap<Integer, Pelicula>) deserializador.readObject();
		deserializador.close();
	}
	
	public void deshacerBorrado() throws FileNotFoundException, IOException {
		if(peliculaBorrada == null)
			return;
		hmPeliculas.put(peliculaBorrada.getId(), peliculaBorrada);
		guardarDisco();
	}
	
	public void alertaBorrarTodo() throws FileNotFoundException, IOException {
		if(hmPeliculas.isEmpty())
			return;
		int ventana = JOptionPane.showConfirmDialog(null, "¿Quieres borrar todos los datos?", "¡ATENCIÓN!", JOptionPane.YES_NO_OPTION);
		if(ventana == JOptionPane.NO_OPTION || ventana == JOptionPane.CLOSED_OPTION) 
			return;
		peliculaBorrada = null; //si despues de borrar todo le da a deshacer borrado no recuperara si borro solo 1 pelicula antes
		hmPeliculas.clear();
		guardarDisco();
	}
}
