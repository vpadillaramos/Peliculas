package com.vpr.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.vpr.modales.Sinopsis;
import com.vpr.pojo.Pelicula;
import com.vpr.util.Util;

public class Controlador implements ActionListener{
	
	//Atributos
	private Modelo modelo;
	private Vista vista;
	
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		
		modoEdicion(false);
		addListeners();
		poblarRating();
		poblarGenero();
	}
	
	public void poblarRating() {
		for(String rating: Pelicula.getRating()) {
			vista.cbRating.addItem(rating);
		}
	}
	
	public void poblarGenero() {
		for(String genero: Pelicula.getGenero()) {
			vista.cbGenero.addItem(genero);
		}
	}
	
	public void refrescarLista() {
		vista.modeloPelicula.removeAllElements();
		
		for(Pelicula pelicula: modelo.getPeliculas()) {
			vista.modeloPelicula.addElement(pelicula);
		}
	}
	
	public void limpiar() {
		vista.tfDirector.setText("");
		vista.tfDuracion.setText("");
		vista.tfNota.setText("");
		vista.tfTitulo.setText("");
		vista.cbGenero.setSelectedIndex(0);
		vista.cbRating.setSelectedIndex(0);
	}
	
	public void addListeners() {
		vista.btBorrar.addActionListener(this);
		vista.btCancelar.addActionListener(this);
		vista.btEditar.addActionListener(this);
		vista.btGuardar.addActionListener(this);
		vista.btNueva.addActionListener(this);
		
		vista.btEscribirSinopsis.addActionListener(this);
		vista.btNotas.addActionListener(this);
	}
	
	public void modoEdicion(boolean editando) {
		if(editando) {
			vista.btCancelar.setEnabled(editando);
			vista.btEditar.setEnabled(!editando);
			vista.btNueva.setEnabled(!editando);
			vista.btBorrar.setEnabled(!editando);
			vista.btGuardar.setEnabled(editando);
			
			vista.btEscribirSinopsis.setEnabled(editando);
			vista.btNotas.setEnabled(editando);
			
			vista.tfDirector.setEditable(editando);
			vista.tfDuracion.setEditable(editando);
			vista.tfNota.setEditable(editando);
			vista.tfTitulo.setEditable(editando);
			
			vista.cbGenero.setEnabled(editando);
			vista.cbRating.setEnabled(editando);
			
			vista.listPeliculas.setEnabled(!editando);
		}
		else {
			vista.btCancelar.setEnabled(editando);
			vista.btEditar.setEnabled(editando);
			vista.btNueva.setEnabled(!editando);
			vista.btBorrar.setEnabled(editando);
			vista.btGuardar.setEnabled(editando);
			
			vista.btEscribirSinopsis.setEnabled(editando);
			vista.btNotas.setEnabled(editando);
			
			vista.tfDirector.setEditable(editando);
			vista.tfDuracion.setEditable(editando);
			vista.tfNota.setEditable(editando);
			vista.tfTitulo.setEditable(editando);
			
			vista.cbGenero.setEnabled(editando);
			vista.cbRating.setEnabled(editando);
			
			vista.listPeliculas.setEnabled(!editando);
		}
	}
	
	public String escribirSinopsis() {
		Sinopsis sinopsis = new Sinopsis();
		sinopsis.ponerVisible(true);
		return sinopsis.getSinopsis();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "cancelar":
			limpiar();
			modoEdicion(false);
			break;
		case "editar":
			
			break;
		case "nueva":
			modoEdicion(true);
			vista.tfTitulo.requestFocus();
			break;
		case "borrar":
			
			break;
		case "guardar":
			limpiar();
			modoEdicion(false);
			
			if(vista.tfTitulo.getText().equals("")) {
				Util.mensajeError("El título es obligatorio");
				vista.tfTitulo.requestFocus();
			}
			
			
			
			break;
		case "escribirSinopsis":
			escribirSinopsis();
			break;
		case "notas":
			
			break;
		default:
			
			break;
		}
	}
}
