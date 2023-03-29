package tpfinal;

public class Partido {

	private int nroPartido;
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private int golesLocal;
	private int golesVisitante;

	public Partido(int nroPartido, Equipo equipoLocal, Equipo equipoVisitante, int golesLocal, int golesVisitante) {
		super();
		this.nroPartido = nroPartido;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}

	public Resultado getResultado() {
		if (this.golesLocal == this.golesVisitante) {
			return Resultado.EMPATE;
		} else if (this.golesLocal > this.golesVisitante) {
			return Resultado.LOCAL;
		}
		return Resultado.VISITANTE;
	}

	public int getNroPartido() {
		return nroPartido;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

}
