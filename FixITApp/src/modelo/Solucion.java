package modelo;

/**
 * Solucion propuesta para resolver una incidencia.
 */
public class Solucion {
	private int id;
	private String descripcion;
	private boolean esAceptada;

	// Constructor con todos los datos de la solucion.
	public Solucion(int id, String descripcion, boolean esAceptada) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.esAceptada = esAceptada;
	}

	// Getters y setters.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEsAceptada() {
		return esAceptada;
	}

	public void setEsAceptada(boolean esAceptada) {
		this.esAceptada = esAceptada;
	}
}
