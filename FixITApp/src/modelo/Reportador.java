package modelo;

/**
 * Usuario que registra incidencias en la aplicacion.
 */
public class Reportador extends Usuario {
	private int totalCreadas;

	// Mantiene los datos de Usuario y anade el total de incidencias creadas.
	public Reportador(String nombreUsuario, String email, String contrasenya, String fechaRegistro, int totalCreadas) {
		super(nombreUsuario, email, contrasenya, fechaRegistro);
		this.totalCreadas = totalCreadas;
	}

	// Getters y setters.
	public int getTotalCreadas() {
		return totalCreadas;
	}

	public void setTotalCreadas(int totalCreadas) {
		this.totalCreadas = totalCreadas;
	}
}
