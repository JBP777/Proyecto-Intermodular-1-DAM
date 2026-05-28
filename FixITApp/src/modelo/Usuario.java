package modelo;

/**
 * Datos basicos de un usuario registrado en la aplicacion.
 */
public class Usuario {

	private String nombreUsuario, email, contrasenya, fechaRegistro;

	// Constructor usado al cargar o registrar usuarios.
	public Usuario(String nombreUsuario, String email, String contrasenya, String fechaRegistro) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.contrasenya = contrasenya;
		this.fechaRegistro = fechaRegistro;
	}

	// Getters y setters.
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}
