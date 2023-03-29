package tpfinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase se va a encargar de leer desde un arhivo csv los partidos y
 * almacenaralos en un HashMap de Partido la key va a ser el nro de partido y el
 * value sera un objeto de la clase Partido
 * 
 */
public class GestorPartidos {

	private Map<Integer, Partido> partidos = new HashMap<Integer, Partido>();
	private Partido partido;

	/**
	 * Metodo que lee el archivo de pronosticos y los carga en un HashMap<Integer,
	 * Partido>
	 * 
	 * @return partidos
	 */
	public Map<Integer, Partido> cargarPartidosDesdeArchivo(String path) {
		try {
			FileReader filePartidos = new FileReader(path);
			BufferedReader brPartidos = new BufferedReader(filePartidos);
			String unPartido = brPartidos.readLine();
			while (unPartido != null) {

				partido = getDatosPartido(unPartido);
				if (partido != null) {
					partidos.put(partido.getNroPartido(), partido);
				}
				unPartido = brPartidos.readLine();
			}
			brPartidos.close();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + path);
		}

		return partidos;
	}

	/**
	 * Metodo privado de la clase Se pasa por parametro un String que representa
	 * todos los datos de un partido, lo convierte y devuelve un Partido En caso de
	 * inconsistencia con los datos devuelve un null
	 * 
	 * @param unPartido
	 * @return Partido
	 * 
	 */
	private Partido getDatosPartido(String unPartido) {
		String[] datosUnPartido = unPartido.split(",");
		Equipo equipoLocal;
		Equipo equipoVisitante;
		try {
			int nroPartido = Integer.parseInt(datosUnPartido[0]);
			String eqLocal = datosUnPartido[1];
			String eqVisitante = datosUnPartido[2];
			int golesLocal = Integer.parseInt(datosUnPartido[3]);
			int golesVisitante = Integer.parseInt(datosUnPartido[4]);
			equipoLocal = new Equipo(eqLocal);
			equipoVisitante = new Equipo(eqVisitante);

			partido = new Partido(nroPartido, equipoLocal, equipoVisitante, golesLocal, golesVisitante);

		} catch (Exception e) {
			System.out.println("Entrada de datos no valida, error en la linea: " + unPartido);
			partido = null;
		}
		return partido;
	}

}
