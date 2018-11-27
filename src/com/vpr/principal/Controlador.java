package com.vpr.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.vpr.modales.Nota;
import com.vpr.modales.Sinopsis;
import com.vpr.pojo.Pelicula;
import com.vpr.util.Util;

public class Controlador implements ActionListener, MouseListener, ListSelectionListener, KeyListener{
	
	//Constantes
	private final String DEFAULT_PORTADA = "default.png";
	
	//Atributos
	private Modelo modelo;
	private Vista vista;
	private String sinopsis, notas;
	private File ficheroSeleccionado;
	
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		
		modoEdicion(false);
		addListeners();
		poblarRating();
		poblarGenero();
		refrescarLista();
	}
	
	public void poblarRating() {
		for(String rating: Pelicula.getARating()) {
			vista.cbRating.addItem(rating);
		}
	}
	
	public void poblarGenero() {
		for(String genero: Pelicula.getAGenero()) {
			vista.cbGenero.addItem(genero);
		}
	}
	
	public void refrescarLista() {
		vista.modeloPelicula.removeAllElements();
		
		for(Pelicula pelicula: modelo.getPeliculas()) {
			vista.modeloPelicula.addElement(pelicula);
			vista.anadirBuscar(pelicula.getTitulo());
		}
	}
	
	public void limpiar() {
		vista.tfDirector.setText("");
		vista.tfDuracion.setText("");
		vista.tfNota.setText("");
		vista.tfTitulo.setText("");
		vista.cbGenero.setSelectedIndex(0);
		vista.cbRating.setSelectedIndex(0);
		vista.chbVista.setSelected(false);
		
		vista.dcFechaEstreno.setDate(null);
		vista.dcFechaVista.setDate(null);
	}
	
	public void addListeners() {
		vista.btBorrar.addActionListener(this);
		vista.btCancelar.addActionListener(this);
		vista.btEditar.addActionListener(this);
		vista.btGuardar.addActionListener(this);
		vista.btNueva.addActionListener(this);
		vista.btDeshacer.addActionListener(this);
		vista.btBorrarTodo.addActionListener(this);
		
		vista.btSinopsis.addActionListener(this);
		vista.btNotas.addActionListener(this);
		vista.chbAvanzado.addActionListener(this);
		
		vista.listPeliculas.addListSelectionListener(this);
		
		vista.tfTitulo.addKeyListener(this);
	}
	
	public void modoEdicion(boolean editando) {
		if(editando) {
			vista.btCancelar.setEnabled(editando);
			vista.btEditar.setEnabled(!editando);
			vista.btNueva.setEnabled(!editando);
			vista.btBorrar.setEnabled(!editando);
			vista.btGuardar.setEnabled(editando);
			
			vista.btSinopsis.setEnabled(editando);
			vista.btNotas.setEnabled(editando);
			
			vista.tfDirector.setEditable(editando);
			vista.tfDuracion.setEditable(editando);
			vista.tfNota.setEditable(editando);
			vista.tfTitulo.setEditable(editando);
			vista.tfNota.setEnabled(editando);
			vista.cbGenero.setEnabled(editando);
			vista.cbRating.setEnabled(editando);
			vista.chbVista.setEnabled(editando);
			vista.dcFechaEstreno.setEnabled(editando);
			vista.dcFechaVista.setEnabled(editando);
			vista.lbPortada.addMouseListener(this);
			
			vista.listPeliculas.setEnabled(!editando);
		}
		else {
			vista.btCancelar.setEnabled(editando);
			vista.btEditar.setEnabled(editando);
			vista.btNueva.setEnabled(!editando);
			vista.btBorrar.setEnabled(editando);
			vista.btGuardar.setEnabled(editando);
			
			vista.btSinopsis.setEnabled(editando);
			vista.btNotas.setEnabled(editando);
			
			vista.tfDirector.setEditable(editando);
			vista.tfDuracion.setEditable(editando);
			vista.tfNota.setEditable(editando);
			vista.tfTitulo.setEditable(editando);
			vista.tfNota.setEnabled(editando);
			vista.cbGenero.setEnabled(editando);
			vista.cbRating.setEnabled(editando);
			vista.chbVista.setEnabled(editando);
			vista.dcFechaEstreno.setEnabled(editando);
			vista.dcFechaVista.setEnabled(editando);
			vista.lbPortada.removeMouseListener(this);
			
			vista.listPeliculas.setEnabled(!editando);
			vista.listPeliculas.clearSelection();
		}
	}
	
	public String escribirSinopsis() {
		Sinopsis sinopsis = new Sinopsis();
		sinopsis.ponerVisible(true);
		return sinopsis.getSinopsis();
	}
	
	public String escribirNotas() {
		Nota nota = new Nota();
		nota.ponerVisible(true);
		return nota.getNotas();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(vista.chbAvanzado.isSelected())
			vista.panAvanzado.setVisible(true);
		else
			vista.panAvanzado.setVisible(false);
		
		
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
			try {
				modelo.borrarPelicula(vista.listPeliculas.getSelectedValue());
				Util.mensajeInformacion("Eliminado", vista.listPeliculas.getSelectedValue().getTitulo() + " eliminada");
			} catch (FileNotFoundException fnfe) {
				Util.mensajeError("Almacenamiento no encontrado");
				fnfe.printStackTrace();
			} catch (IOException ioe) {
				Util.mensajeError("No se pudo acceder al almacenamiento");
				ioe.printStackTrace();
			}
			
			refrescarLista();
			modoEdicion(false);
			break;
		case "guardar":
			//****CONTROL DE ENTRADA DE DATOS******
			if(vista.tfTitulo.getText().equals("")) {
				Util.mensajeError("El título es obligatorio");
				vista.tfTitulo.requestFocus();
				return;
			}
			
			if(vista.tfDuracion.getText().equals("")) 
				vista.tfDuracion.setText("0");
			if(!Util.isInt(vista.tfDuracion.getText())) {
				Util.mensajeError("La duración es incorrecta");
				vista.tfDuracion.requestFocus();
				vista.tfDuracion.selectAll();
				return;
			}
			
			if(vista.tfNota.getText().equals(""))
				vista.tfNota.setText("0.0");
			if(!Util.isFloat(vista.tfNota.getText())) {
				Util.mensajeError("La nota es incorrecta");
				vista.tfNota.requestFocus();
				vista.tfNota.selectAll();
				return;
			}
				
			
			Pelicula pelicula = new Pelicula();
			
			pelicula.setTitulo(vista.tfTitulo.getText());
			pelicula.setSinopsis(sinopsis);
			pelicula.setGenero(vista.cbGenero.getSelectedIndex());
			pelicula.setRating(vista.cbRating.getSelectedIndex());
			pelicula.setDirector(vista.tfDirector.getText());
			pelicula.setDuracion(Integer.parseInt(vista.tfDuracion.getText()));
			pelicula.setNotas(notas);
			pelicula.setVista(vista.chbVista.isSelected());
			
			
			//Decimal
			if(vista.tfNota.getText().contains(","))
				try {
					//TODO no se muestra el decimal con una coma debido a la asignacion de variables en setNota
					pelicula.setNota(Util.parseDecimal(vista.tfNota.getText()));
				} catch (ParseException pe) {
					Util.mensajeError("Error al formatear la nota");
					pe.printStackTrace();
				}
			else
				pelicula.setNota(Float.parseFloat(vista.tfNota.getText()));
			
			//Fechas
			String fechaEstreno;
			String fechaVista;
			Locale fCastellano = new Locale("es","Es","Traditional.win");
			if(vista.dcFechaEstreno.getDate() == null)
				fechaEstreno = "";
			else
				fechaEstreno = DateFormat.getDateInstance(DateFormat.SHORT, fCastellano).format(vista.dcFechaEstreno.getDate());
			if(vista.dcFechaVista.getDate() == null)
				fechaVista = "";
			else
				fechaVista = DateFormat.getDateInstance(DateFormat.SHORT, fCastellano).format(vista.dcFechaVista.getDate());
			
			pelicula.setFechaEstreno(fechaEstreno);
			pelicula.setFechaVista(fechaVista);
			
			//Imagen
			String nombreImagen;
			if(ficheroSeleccionado != null) {
				nombreImagen = ficheroSeleccionado.getName();
				
				try {
					Util.copiarImagen(ficheroSeleccionado.getAbsolutePath(), nombreImagen);
					pelicula.setPortada(ficheroSeleccionado.getAbsolutePath() + File.separator + nombreImagen);
				} catch (IOException e1) {
					Util.mensajeError("No se puedo copiar la imagen");
					e1.printStackTrace();
				}
			}
			//si no selecciono fichero pongo la imagen por defecto
			else {
				nombreImagen = DEFAULT_PORTADA;
				pelicula.setPortada(nombreImagen);
			}
			
			
			//GUARDA PELICULA
			try {
				modelo.guardarPelicula(pelicula);
				refrescarLista();
			} catch (FileNotFoundException e1) {
				Util.mensajeError("No se encontró disco de almacenamiento");
				e1.printStackTrace();
			} catch (IOException e1) {
				Util.mensajeError("No se pudo guardar la película");
				e1.printStackTrace();
			}
			
			
			Util.mensajeInformacion("Guardado", "Película guardada correctamente");
			limpiar();
			if(vista.chbAdicionRapida.isSelected()) 
				vista.tfTitulo.requestFocus();
			else
				modoEdicion(false);
			
			break;
		case "btSinopsis":
			sinopsis = escribirSinopsis();
			break;
		case "btNotas":
			notas = escribirNotas();
			break;
		case "deshacer":
			try {
				modelo.deshacerBorrado();
				refrescarLista();
			} catch (FileNotFoundException e1) {
				Util.mensajeError("No se encontró almacenamiento");
				e1.printStackTrace();
			} catch (IOException e1) {
				Util.mensajeError("No se pudo acceder al almacenamiento");
				e1.printStackTrace();
			}
			break;
		
		case "borrarTodo":
			try {
				modelo.alertaBorrarTodo();
				refrescarLista();
			} catch (FileNotFoundException e1) {
				Util.mensajeError("No se encontró almacenamiento");
				e1.printStackTrace();
			} catch (IOException e1) {
				Util.mensajeError("No se pudo acceder al almacenamiento");
				e1.printStackTrace();
			}
			break;
		default:
			
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == vista.lbPortada) {
			
			//Abro un selector de fichero
			JFileChooser fc = new JFileChooser();
			
			//si le da a cancelar se hace nada
			if(fc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
				return;
			
			ficheroSeleccionado = fc.getSelectedFile();
			vista.lbPortada.setIcon(new ImageIcon(ficheroSeleccionado.getAbsolutePath()));
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(vista.listPeliculas.getSelectedIndex() == -1)
			return;
		vista.btEditar.setEnabled(true);
		vista.btBorrar.setEnabled(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//TODO se pulsa otra vez despues de guardar y salta mensaje error
		if(vista.btGuardar.isEnabled() && e.getKeyCode() == KeyEvent.VK_ENTER)
			vista.btGuardar.doClick();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
