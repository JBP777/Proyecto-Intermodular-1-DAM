package modelo;

/**
 * Incidencia mostrada y gestionada por la aplicacion.
 */
public class Incidencia {
	private int id;
	private String estado, titulo, descripcion, reportador, zona, fechaCreacion, categorias;

	// Constructor con todos los datos que llegan desde la vista de incidencias.
	public Incidencia(int id, String estado, String titulo, String descripcion, String reportador, String zona,
			String fechaCreacion, String categorias) {
		this.id = id;
		this.estado = estado;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.reportador = reportador;
		this.zona = zona;
		this.fechaCreacion = fechaCreacion;
		this.categorias = categorias;
	}

	// Getters.
	public int getId() {
		return id;
	}

	public String getEstado() {
		return estado;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getReportador() {
		return reportador;
	}

	public String getZona() {
		return zona;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public String getCategorias() {
		return categorias;
	}

	// Setters.
	public void setId(int id) {
		this.id = id;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setReportador(String reportador) {
		this.reportador = reportador;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}
}
