package com.vpr.principal;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.vpr.util.Util;

public class Aplicacion {
	public static void main(String[] args) {
		try {
			Modelo modelo = new Modelo();
			Vista vista = new Vista();
			Controlador controlador = new Controlador(modelo, vista);
		} catch (FileNotFoundException e) {
			Util.mensajeError("No se encontró almacenamiento");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Util.mensajeError("No se puedo leer del almacenamiento");
			e.printStackTrace();
		} catch (IOException e) {
			Util.mensajeError("No se pudo acceder al almacenamiento");
			e.printStackTrace();
		}
	}
}
