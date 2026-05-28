package modelo;

/**
 * Recompensa asociada a un tipo y a un valor concreto.
 */
public class Recompensa {
	private double valor;
	private int id;
	private String descripcion;
	private TipoRecompensa tipoRecompensa;

	// Constructor con todos los datos de la recompensa.
	public Recompensa(double valor, int id, String descripcion, TipoRecompensa tipoRecompensa) {
		super();
		this.valor = valor;
		this.id = id;
		this.descripcion = descripcion;
		this.tipoRecompensa = tipoRecompensa;
	}

	// Getters y setters.
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoRecompensa getTipoRecompensa() {
		return tipoRecompensa;
	}

	public void setTipoRecompensa(TipoRecompensa tipoRecompensa) {
		this.tipoRecompensa = tipoRecompensa;
	}
}
