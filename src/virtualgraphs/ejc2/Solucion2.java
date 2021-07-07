package ejc2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import us.lsi.common.Tuple;
import us.lsi.common.Tuple2;

public class Solucion2 {

	private SortedMap<String, Tuple2<List<Integer>, List<String>>> dict;

	public static Solucion2 create(List<Integer> lista) {
		return new Solucion2(lista);
	}

	private Solucion2(List<Integer> lista) {

		dict = new TreeMap<>();

		for (int i = 0; i < lista.size(); i++) {
			Integer ab = lista.get(i);
			String key = Lectura2.getAbogado(ab).v1;

			String caso = "Caso " + (i + 1);

			Integer tiempo = Lectura2.getAbogado(ab).v2.get(i);

			Tuple2<List<Integer>, List<String>> tupla = dict.getOrDefault(key,
					Tuple.create(new ArrayList<>(), new ArrayList<>()));

			tupla.v1.add(tiempo);
			tupla.v2.add(caso);

			dict.put(key, tupla);
		}

	}

	public static Integer totalHoras(Map<String, Tuple2<List<Integer>, List<String>>> map) {
		int suma = 0;
		for (Entry<String, Tuple2<List<Integer>, List<String>>> par : map.entrySet()) {
			List<Integer> lista = par.getValue().v1;
			suma += HorasAbogado(lista);
		}

		return suma;
	}

	public static Integer HorasAbogado(List<Integer> lista) {
		int suma = 0;

		for (int i = 0; i < lista.size(); i++) {
			suma += lista.get(i);
		}

		return suma;
	}

	public static Integer maximoHoras(Map<String, Tuple2<List<Integer>, List<String>>> map) {
		int res = Integer.MIN_VALUE;
		for (Entry<String, Tuple2<List<Integer>, List<String>>> par : map.entrySet()) {
			List<Integer> lista = par.getValue().v1;
			Integer suma = HorasAbogado(lista);
			if (suma > res) {
				res = suma;
			}
		}

		return res;
	}

	public String toString() {

		String toString = "";
		for (Map.Entry<String, Tuple2<List<Integer>, List<String>>> par : dict.entrySet()) {
			toString += par.getKey() + "\n";
			toString += "       Horas empleadas: " + HorasAbogado(par.getValue().v1) + "\n";
			toString += "       Casos estudiados: " + par.getValue().v2 + "\n";
			toString += String.format("       Media (horas/caso): %.2f \n",
					(double) HorasAbogado(par.getValue().v1) / par.getValue().v2.size());
			toString += "· -- - -- · -- - -- · -- - -- · -- - -- · -- - -- · -- - -- · -- - -- · -- - -- · --\n";
		}

		toString += "El estudio de todos los casos ha supuesto un total de " + totalHoras(dict)
				+ " horas de trabajo para el \r\n"
				+ "bufete, que al trabajar en paralelo se ha podido llevar a cabo en " + maximoHoras(dict) + " horas";

		return toString;
	}

}
