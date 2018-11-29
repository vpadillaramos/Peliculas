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
	/**
	 * Guarda Pelicula en un HashMap y graba los cambios en el fichero 
	 * @param pelicula
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void guardarPelicula(Pelicula pelicula) throws FileNotFoundException, IOException {
		if(hmPeliculas.size() == 0)
			pelicula.setId(0);
		else {
			ArrayList<Pelicula> lista = getPeliculas();
			pelicula.setId(lista.get(lista.size()-1).getId()+1);
		}
		
		hmPeliculas.put(pelicula.getId(), pelicula);
		guardarDisco();
	}
	
	/**
	 * Borra Pelicula de un HashMap y graba los cambios en el fichero
	 * @param pelicula
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void borrarPelicula(Pelicula pelicula) throws FileNotFoundException, IOException {
		peliculaBorrada = pelicula; //antes de borrarla la guardo en una variable para poder recuperarla si se quisiera
		hmPeliculas.remove(pelicula.getId(), pelicula);
		guardarDisco();
	}
	
	/**
	 * Reemplaza Pelicula por la pasada por parametro. Tienen la misma Id
	 * @param pelicula
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void modificarPelicula(Pelicula pelicula) throws FileNotFoundException, IOException {
		hmPeliculas.replace(pelicula.getId(), pelicula);
		guardarDisco();
	}
	
	/**
	 * Devuelve un ArrayList con todas las peliculas almacenadas
	 * @return
	 */
	public ArrayList<Pelicula> getPeliculas() {
		return new ArrayList<Pelicula>(hmPeliculas.values());
	}
	
	/**
	 * Guarda en el fichero "peliculas.dat" de forma serializada
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void guardarDisco() throws FileNotFoundException, IOException {
		ObjectOutputStream serializador = null;
		
		serializador = new ObjectOutputStream(new FileOutputStream("peliculas.dat"));
		serializador.writeObject(hmPeliculas);
		serializador.close();
	}
	
	/**
	 * Obtiene los datos del fichero "peliculas.dat" deserializandolos
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void leerDisco() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream deserializador = null;
		
		deserializador = new ObjectInputStream(new FileInputStream("peliculas.dat"));
		hmPeliculas = (HashMap<Integer, Pelicula>) deserializador.readObject();
		deserializador.close();
	}
	
	/**
	 * Obtiene la ultima pelicula borrada y la guarda en el fichero
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void deshacerBorrado() throws FileNotFoundException, IOException {
		if(peliculaBorrada == null)
			return;
		hmPeliculas.put(peliculaBorrada.getId(), peliculaBorrada);
		guardarDisco();
	}
	
	/**
	 * Borra el HashMap que contiene todas las peliculas y guarda los cambios en el fichero
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void borrarTodo() throws FileNotFoundException, IOException {
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
