package modelo;

/**
 * Mensaje de contacto enviado por un usuario al administrador.
 */
public class Contacto {

	private String nombre, email, asunto, mensaje;

	// Constructor con los datos del formulario de contacto.
	public Contacto(String nombre, String email, String asunto, String mensaje) {
		this.nombre = nombre;
		this.email = email;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}

	// Getters.
	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public String getAsunto() {
		return asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	// Setters.
	public void setNombre(String usuario) {
		this.nombre = usuario;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
