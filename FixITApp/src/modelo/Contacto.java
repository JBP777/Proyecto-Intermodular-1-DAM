package modelo;

public class Contacto {
		private int id;
		private String nombre, email, asunto, mensaje;

		public Contacto(int id, String nombre, String email, String asunto, String mensaje) {
			this.id = id;
			this.nombre = nombre;
			this.email = email;
			this.asunto = asunto;
			this.mensaje = mensaje;
		}

		public int getId() {
			return id;
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

		public void setId(int id) {
			this.id = id;
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
