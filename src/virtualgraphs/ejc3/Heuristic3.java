package ejc3;

import java.util.function.Predicate;

public class Heuristic3 {

	public static Double heuristic(Vertex3 v1, Predicate<Vertex3> goal, Vertex3 v2) {
		return heuristic(v1, Lectura3.NUM_PR);
	}

	public static Double heuristic(Vertex3 vertice, int lastIndex) {
		if (vertice.funcionalidadesFaltantes.isEmpty()) {
			return 0.;
		} else {
			Double menor_peso = Double.MAX_VALUE;
			for (Integer item = vertice.index; item < lastIndex; item++) {
				Double peso = Lectura3.getPrecio(item);
				if (peso < menor_peso) {
					menor_peso = peso;
				}
			}
			return menor_peso;
		}
	}
}
