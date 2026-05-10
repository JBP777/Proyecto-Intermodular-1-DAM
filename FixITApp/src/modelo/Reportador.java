package modelo;

public class Reportador extends Usuario {
	private int totalCreadas;

	public Reportador(String nombreUsuario, String email, String contrasenya, String fechaRegistro, int totalCreadas) {
		super(nombreUsuario, email, contrasenya, fechaRegistro);
		this.totalCreadas = totalCreadas;
	}

	public int getTotalCreadas() {
		return totalCreadas;
	}

	public void setTotalCreadas(int totalCreadas) {
		this.totalCreadas = totalCreadas;
	}

}
