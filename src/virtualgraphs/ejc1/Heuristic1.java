package ejc1;

import java.util.function.Predicate;

import us.lsi.common.Preconditions;

public class Heuristic1 {

	public static Double heuristic(Vertex1 v1, Predicate<Vertex1> goal, Vertex1 v2) {
		Preconditions.checkElementIndex(v2.indice, Lectura1.NUM_AL + 1);
		return heuristic(v1.indice, v2.indice, v1, v2);
	}

	public static Double heuristic(Integer indice, Integer ultIndice, Vertex1 v1, Vertex1 v2) {
		Double res = 0.0;
		while (indice < ultIndice) {
			Integer afinidad = 0;
			for (int i = 0; i < Lectura1.NUM_GR; i++) {
				Boolean predicado = Lectura1.getAfinidad(indice, i) > afinidad && v1.isValid() && v2.isValid()
						&& Lectura1.getAfinidad(indice, i) > 0.0;

				if (predicado) {
					afinidad = Lectura1.getAfinidad(indice, i);
				}
			}
			afinidad++;
			indice = indice + 1;
		}
		return res;
	}

}
