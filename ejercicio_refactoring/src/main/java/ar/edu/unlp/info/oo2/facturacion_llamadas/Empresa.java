package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Empresa {
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<Llamada> llamadas = new ArrayList<Llamada>();
	private SortedSet<String> lineas = new TreeSet<String>();
	private Generador generador;
	private ConstructorLlamada constructorLlamada;
	
	static double descuentoJur = 0.15;
	static double descuentoFis = 0;
	
	public Empresa() {
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

	public void setearConstructorLlamada(ConstructorLlamada constructor) {
		
		this.constructorLlamada = constructor;
	}
	
	public boolean agregarNumeroTelefono(String str) {
		boolean encontre = getLineas().contains(str);
		if (!encontre) {
			getLineas().add(str);
			encontre= true;
			return encontre;
		}
		else {
			encontre= false;
			return encontre;
		}
	}


	public Cliente registrarUsuario(String data, String nombre, String tipo) {
		Cliente var = new Cliente();
		if (tipo.equals("fisica")) {
			var.setNombre(nombre);
			String tel = this.obtenerNumeroLibre();
			var.setTipo(tipo);
			var.setNumeroTelefono(tel);
			var.setDNI(data);
		}
		else if (tipo.equals("juridica")) {
			String tel = this.obtenerNumeroLibre();
			var.setNombre(nombre);
			var.setTipo(tipo);
			var.setNumeroTelefono(tel);
			var.setCuit(data);
		}
		clientes.add(var);
		return var;
	}

	public Llamada registrarLlamada(Cliente origen, Cliente destino, int duracion) {
		Llamada llamada = this.constructorLlamada.crearLlamada(origen.getNumeroTelefono(), destino.getNumeroTelefono(), duracion);
		llamadas.add(llamada);
		origen.addLlamada(llamada);
		return llamada;
	}
	
	public double calcularMontoTotalLlamadas(Cliente cliente) {
		double c = 0;
		for (Llamada l : cliente.getLlamadas()) {
			double auxc = 0;
			auxc += l.getDuracion() * l.getPrecio() + (l.getDuracion() * l.getAdicional() * l.iva) + l.getAdicional();
			if (cliente.getTipo() == "fisica") {
				auxc -= auxc*descuentoFis;
			} else if(cliente.getTipo() == "juridica") {
				auxc -= auxc*descuentoJur;
			}
			c += auxc;
		}
		return c;
	}

	public int cantidadDeUsuarios() {
		return clientes.size();
	}

	public boolean existeUsuario(Cliente persona) {
		return clientes.contains(persona);
	}

	
}
