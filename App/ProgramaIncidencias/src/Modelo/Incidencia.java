package Modelo;

public class Incidencia {
	private int id,fechaCreacion;
	private String estado,titulo,descripcion;
	private Reportador reportador;
	private Zona zona;
	
	
	
	public Incidencia(int id, int fechaCreacion, String estado, String titulo, String descripcion,
			Reportador reportador, Zona zona) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.reportador = reportador;
		this.zona = zona;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(int fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Reportador getReportador() {
		return reportador;
	}
	public void setReportador(Reportador reportador) {
		this.reportador = reportador;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	
}
