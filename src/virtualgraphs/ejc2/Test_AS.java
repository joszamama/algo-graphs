package ejc2;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.GraphAlg;
import us.lsi.graphs.virtual.ActionSimpleEdge;
import us.lsi.graphs.virtual.EGraph;

public class Test_AS {

	public static void main(String[] args) {
		for (int indice = 1; indice < 3; indice++) {
			Locale.setDefault(new Locale("en", "US"));
			Lectura2.lectura("ficheros/PI7Ej2DatosEntrada" + indice + ".txt");

			// Vértices clave
			Vertex2 start = Vertex2.initialVertex();
			Predicate<Vertex2> finalVertex = v -> Vertex2.goal(v);

			// Grafo
			EGraph<Vertex2, ActionSimpleEdge<Vertex2, Integer>> graph = Graphs2.simpleVirtualGraphLast(start,
					x -> x.getcMax());

			// Algoritmo A*
			System.out.println("##### PI-7 EJ2 - " + indice + " - Algoritmo A* ####");
			AStar<Vertex2, ActionSimpleEdge<Vertex2, Integer>> aStar = GraphAlg.aStarGoal(graph, finalVertex,
					(v1, p, v2) -> 0.);

			List<Integer> gp_as = aStar.search().get().getEdgeList().stream().map(x -> x.action)
					.collect(Collectors.toList());

			Solucion2 s_as = Solucion2.create(gp_as);
			System.out.println(s_as);
		}
	}
}
