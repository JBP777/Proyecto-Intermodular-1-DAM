package modelo;

/**
 * Representa una zona donde puede registrarse una incidencia.
 */
public class Zona {
	private String nombre;
	private int id;

	// Constructor con todos los datos de la zona.
	public Zona(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}

	// Getters y setters.
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
