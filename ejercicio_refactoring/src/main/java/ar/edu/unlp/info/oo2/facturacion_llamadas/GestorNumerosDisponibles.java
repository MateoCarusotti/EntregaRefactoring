package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;

public class GestorNumerosDisponibles {
	private SortedSet<String> lineas = new TreeSet<String>();
	private Generador generador;
	
	public GestorNumerosDisponibles() {
		
		this.generador = new GeneradorUltimo();
		
	}
	
	public SortedSet<String> getLineas() {
		return lineas;
	}
	
	public String obtenerNumeroLibre() {
		String linea = this.generador.obtenerNumeroLibre(this.lineas);
		this.lineas.remove(linea);
		return linea;
	
	}
	public void cambiarTipoGenerador(Generador generador) {
		this.generador = generador;
	}
}
