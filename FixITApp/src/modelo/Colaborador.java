package modelo;

/**
 * Usuario que puede resolver incidencias.
 */
public class Colaborador extends Usuario {

	private double valoracionMedia;
	private int totalResueltas;

	// Mantiene los datos de Usuario y anade estadisticas de colaborador.
	public Colaborador(String nombreUsuario, String email, String contrasenya, String fechaRegistro,
			double valoracionMedia, int totalResueltas) {
		super(nombreUsuario, email, contrasenya, fechaRegistro);
		this.valoracionMedia = valoracionMedia;
		this.totalResueltas = totalResueltas;
	}

	// Getters y setters.
	public double getValoracionMedia() {
		return valoracionMedia;
	}

	public void setValoracionMedia(double valoracionMedia) {
		this.valoracionMedia = valoracionMedia;
	}

	public int getTotalResueltas() {
		return totalResueltas;
	}

	public void setTotalResueltas(int totalResueltas) {
		this.totalResueltas = totalResueltas;
	}
}
