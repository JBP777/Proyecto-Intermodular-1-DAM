package modelo;

public class Zona {
	private String nombre;
	private int id;

	public Zona(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}

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
