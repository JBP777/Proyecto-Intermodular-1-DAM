package modelo;

public class Mensaje {
	private int id, fechaEnvio;
	private String contenido;
	private Usuario usuarioEnvia;
	private Usuario usuarioRecibe;
	private boolean leido;

	public Mensaje(int id, int fechaEnvio, String contenido, Usuario usuarioEnvia, Usuario usuarioRecibe,
			boolean leido) {
		super();
		this.id = id;
		this.fechaEnvio = fechaEnvio;
		this.contenido = contenido;
		this.usuarioEnvia = usuarioEnvia;
		this.usuarioRecibe = usuarioRecibe;
		this.leido = leido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(int fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Usuario getUsuarioEnvia() {
		return usuarioEnvia;
	}

	public void setUsuarioEnvia(Usuario usuarioEnvia) {
		this.usuarioEnvia = usuarioEnvia;
	}

	public Usuario getUsuarioRecibe() {
		return usuarioRecibe;
	}

	public void setUsuarioRecibe(Usuario usuarioRecibe) {
		this.usuarioRecibe = usuarioRecibe;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

}
