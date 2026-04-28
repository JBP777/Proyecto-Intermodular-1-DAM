package Modelo;

public class Usuario {

		private String nombreUsuario,email,contrasenya;
		private int fechaRegistro;
		
		public String getNombreUsuario() {
			return nombreUsuario;
		}
		public Usuario(String nombreUsuario, String email, String contrasenya, int fechaRegistro) {
			super();
			this.nombreUsuario = nombreUsuario;
			this.email = email;
			this.contrasenya = contrasenya;
			this.fechaRegistro = fechaRegistro;
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
		public int getFechaRegistro() {
			return fechaRegistro;
		}
		public void setFechaRegistro(int fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}
}
