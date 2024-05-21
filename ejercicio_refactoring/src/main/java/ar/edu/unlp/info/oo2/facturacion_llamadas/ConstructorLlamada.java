package ar.edu.unlp.info.oo2.facturacion_llamadas;

public abstract class ConstructorLlamada {
	
	private double precio;
	private double adicional;
	
	public ConstructorLlamada(double precio, double adicional) {
		
		this.precio = precio;
		this.adicional = adicional;
	}
	
	
	public Llamada crearLlamada(String origen, String destino, int duracion) {
		
		Llamada llamada = new Llamada(origen, destino, duracion);
		
		llamada.setPrecio(this.getPrecio());
		llamada.setAdicional(this.getAdicional());
		
		return llamada;
	}
	
	
	public double getPrecio() {
		
		return precio;
}

	public double getAdicional() { 
	
	return adicional;
}
}
