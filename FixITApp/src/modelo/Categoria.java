package modelo;

/**
 * Categoria usada para clasificar incidencias.
 */
public class Categoria {
	private String nombre, descripcion;
	private int id;

	// Constructor con todos los datos de la categoria.
	public Categoria(String nombre, String descripcion, int id) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.id = id;
	}

	// Getters y setters.
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
