package Modelo;

public class Reportador extends Usuario{
	private int totalCreadas;

	public Reportador(String nombreUsuario, String email, String contrasenya, int fechaRegistro, int totalCreadas) {
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
