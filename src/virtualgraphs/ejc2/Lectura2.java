package ejc2;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.Lists2;
import us.lsi.common.Tuple;
import us.lsi.common.Tuple2;

public class Lectura2 {

	public static Integer NUM_AB, NUM_CA;

	private static List<Tuple2<String, List<Integer>>> abogados;
	private static List<Integer> casos;

	public static void lectura(String ruta) {
		List<String> lineas = Files2.linesFromFile(ruta);
		abogados = Lists2.empty();
		casos = Lists2.empty();

		for (String linea : lineas) {
			String[] args = linea.split(":");
			String abogado = args[0];
			String[] valores = args[1].split(",");
			List<Integer> horasCaso = Lists2.empty();
			for (String val : valores) {
				Integer v = Integer.valueOf(val.trim());
				horasCaso.add(v);
			}

			abogados.add(Tuple.create(abogado, horasCaso));
		}
		for (int i = 0; i < abogados.get(0).getV2().size(); i++) {
			casos.add(i);
		}
		NUM_AB = abogados.size();
		NUM_CA = casos.size();
	}

	public static List<Tuple2<String, List<Integer>>> getAbogados() {
		return abogados;
	}

	public static Tuple2<String, List<Integer>> getAbogado(int index) {
		return abogados.get(index);
	}

	public static void toConsole() {
		for (int i = 0; i < abogados.size(); i++) {
			System.out.println(getAbogado(i).v1 + ":" + getAbogado(i).v2);
		}
		System.out.println(NUM_CA);
	}

	public static void main(String[] args) {
		lectura("ficheros/PI7Ej2DatosEntrada1.txt");
		toConsole();

	}

}
