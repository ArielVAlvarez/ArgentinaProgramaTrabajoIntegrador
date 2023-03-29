package tpfinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Esta clase se va a encargar de leer desde un arhivo csv los pronosticos 
 * y almacenaralos en un ArrayList de Pronostico
 * 
 */
public class GestorPronosticos {

	private ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
	private Pronostico pronostico;

	/**
	 * Metodo que lee el archivo de pronosticos y los carga en un ArrayList de
	 * Pronosticos
	 * 
	 * @return pronosticos
	 */
	public ArrayList<Pronostico> getPronosticos(String path) {

		try {
			FileReader filePronosticos = new FileReader(path);
			BufferedReader brPronosticos = new BufferedReader(filePronosticos);
			String unPronostico = brPronosticos.readLine();
			while (unPronostico != null) {

				pronostico = getDatosPronostico(unPronostico);
				if (pronostico != null) {
					pronosticos.add(pronostico);
				}
				unPronostico = brPronosticos.readLine();
			}
			brPronosticos.close();
		} catch (IOException e) {
			System.out.println("Error al leer un archivo: " + path);
		} 
		return pronosticos;

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
