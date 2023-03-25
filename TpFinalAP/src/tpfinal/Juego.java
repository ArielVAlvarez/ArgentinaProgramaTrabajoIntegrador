package tpfinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Juego {

	/**
	 * Metodo que recorre el archivo de partidos y compara con un arraylist de
	 * pronosticos. cuenta los aciertos y muestra por consola la catidad de puntos
	 * del participante
	 */
	public void resolverJuego() {

		String pathPartidos = "archivos/partidos.csv";
		Partido partido;
		String participante = "";
		ArrayList<Pronostico> listaPronosticos = new ArrayList<Pronostico>();
		int puntos = 0;

		try {
			FileReader filePartidos = new FileReader(pathPartidos);
			BufferedReader brPartidos = new BufferedReader(filePartidos);
			String unPartido = brPartidos.readLine();

			listaPronosticos = getPronosticos();

			// Recorre las lineas del archivo de partidos
			while (unPartido != null) {

				partido = getDatosPartido(unPartido);

				// Recorre el arraylist de pronostico comparando
				for (Pronostico pronostico : listaPronosticos) {

					if (partido.getNroPartido() == pronostico.getNroPartido()
							&& partido.getResultado().equals(pronostico.getResultado())) {
						puntos++;
					}
					participante = pronostico.getNombre();
				}

				unPartido = brPartidos.readLine();
			}

		} catch (IOException e) {
			System.out.println("Error al leer un archivo: " + pathPartidos);
		} finally {
			System.out.println("El participante " + participante + " obtuvo " + puntos + " puntos");
		}

	}

	/**
	 * Metodo privado que lee el archivo de pronosticos y los carga en un ArrayList
	 * de Pronosticos
	 * 
	 * @return listaPronosticos
	 */

	private ArrayList<Pronostico> getPronosticos() {
		ArrayList<Pronostico> listaPronosticos = new ArrayList<Pronostico>();
		String pathPronosticos = "archivos/pronosticos.csv";
		Pronostico pronostico;
		try {
			FileReader filePronosticos = new FileReader(pathPronosticos);
			BufferedReader brPronosticos = new BufferedReader(filePronosticos);
			String unPronostico = brPronosticos.readLine();
			while (unPronostico != null) {

				pronostico = getDatosPronostico(unPronostico);
				if (pronostico != null) {
					listaPronosticos.add(pronostico);
				}
				unPronostico = brPronosticos.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error al leer un archivo: " + pathPronosticos);
		}
		return listaPronosticos;

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
		Partido partido;
		String[] datosUnPartido = unPartido.split(",");
		try {
			int nroPartido = Integer.parseInt(datosUnPartido[0]);
			String equipoLocal = datosUnPartido[1];
			String equipoVisitante = datosUnPartido[2];
			int golesLocal = Integer.parseInt(datosUnPartido[3]);
			int golesVisitante = Integer.parseInt(datosUnPartido[4]);

			partido = new Partido(nroPartido, equipoLocal, equipoVisitante, golesLocal, golesVisitante);

		} catch (Exception e) {
			System.out.println("Entrada de datos no valida, error en la linea: " + unPartido);
			partido = null;
		}
		return partido;
	}

	/**
	 * Metodo privado de la clase Se pasa por parametro un String que representa
	 * todos los datos de un pronostico, lo convierte y devuelve un Pronostico En
	 * caso de inconsistencia con los datos devuelve un null
	 * 
	 * @param unPronostico
	 * @return Pronostico
	 * 
	 */
	private Pronostico getDatosPronostico(String unPronostico) {
		Pronostico pronostico;
		String[] datosUnPronostico = unPronostico.split(",");
		try {
			int nroPartido = Integer.parseInt(datosUnPronostico[0]);
			String nombre = datosUnPronostico[1];
			Resultado resultado = Resultado.valueOf(datosUnPronostico[2].toUpperCase());

			pronostico = new Pronostico(nroPartido, nombre, resultado);

		} catch (Exception e) {
			System.out.println("Entrada de datos no valida, error en la linea: " + unPronostico);
			pronostico = null;
		}
		return pronostico;
	}

}
