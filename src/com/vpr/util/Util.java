package com.vpr.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Util {
	
	public static void mensajeInformacion(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	public static boolean isInt(String cadena) {
		boolean resultado = false;
		if(cadena.matches("\\d*"))	//esto es una expresion regular que comprueba si son numeros
			resultado = true;
		return resultado;
	}
	
	public static boolean isFloat(String cadena) {
		boolean resultado = false;
		if(cadena.matches("\\d*\\.\\d*") || cadena.matches("\\d*\\,\\d*"))
			resultado = true;
		return resultado;
	}
	
	public static float parseDecimal(String decimal) throws ParseException {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(',');
		DecimalFormat df = new DecimalFormat();
		df.setDecimalFormatSymbols(dfs);
		return df.parse(decimal).floatValue();
	}
	
	public static void copiarImagen(String rutaOrigen, String nombreImagen) throws IOException {
		Path origen = FileSystems.getDefault().getPath(rutaOrigen);
		FileOutputStream destino = new FileOutputStream(
				new File(System.getProperty("user.dir") + File.separator + "portadas" + File.separator + nombreImagen)
				);
		Files.copy(origen, destino);
	}
	
	public static void backup() {
		File origen = new File("peliculas.dat");
		File destino;
		
		//Selecciono el directorio de destino
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		if(fc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
			return;
		Path aux = Paths.get(fc.getSelectedFile().toString() + "//peliculas.dat");
		destino = aux.toFile();
		try {
			Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e1) {
			Util.mensajeError("No pudo copiarse el fichero");
			e1.printStackTrace();
		}
	}
	
	//Metodo que devuelve un numero entero aleatorio
	public static int intRandom(int min, int max) {
		Random num = new Random();
		return (min + num.nextInt(max-min+1));
	}
}
