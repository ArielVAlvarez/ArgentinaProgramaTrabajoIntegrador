package tpfinal;

public class Pronostico {

	private int nroPartido;
	private String nombre;
	private Resultado resultado;

	public int getNroPartido() {
		return nroPartido;
	}

	public String getNombre() {
		return nombre;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public Pronostico(int nroPartido, String nombre, Resultado resultado) {
		super();
		this.nroPartido = nroPartido;
		this.nombre = nombre;
		this.resultado = resultado;
	}

}
