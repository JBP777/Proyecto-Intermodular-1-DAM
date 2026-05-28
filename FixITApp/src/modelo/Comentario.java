package modelo;

/**
 * Comentario escrito por un usuario.
 */
public class Comentario {
	private int id, fecha;
	private String texto;
	private Usuario usuario;

	// Constructor con todos los datos del comentario.
	public Comentario(int id, int fecha, String texto, Usuario usuario) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.texto = texto;
		this.usuario = usuario;
	}

	// Getters y setters.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
