package modelo;

public class Contacto {

		private String nombre, email, asunto, mensaje;

		public Contacto(String nombre, String email, String asunto, String mensaje) {
			
			this.nombre = nombre;
			this.email = email;
			this.asunto = asunto;
			this.mensaje = mensaje;
		}

		

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
