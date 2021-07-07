package ejc3;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.GraphAlg;
import us.lsi.graphs.virtual.EGraph;

public class Test_AS {

	public static void main(String[] args) {
		for (int indice = 1; indice < 4; indice++) {
			Locale.setDefault(new Locale("en", "US"));
			Lectura3.lectura("ficheros/PI7Ej3DatosEntrada" + indice + ".txt");

			// Vértices clave
			Vertex3 start = Vertex3.initialVertex();
			Predicate<Vertex3> finalVertex = v -> Vertex3.goal(v);

			// Grafo (AStar siempre minimiza)
			EGraph<Vertex3, Edge3> graph = Graphs2.simpleVirtualGraph(start, x -> x.getEdgeWeight());

			System.out.println("##### PI-7 EJ1 - " + indice + " - Algoritmo A* ####");

			// Algoritmo A*
			AStar<Vertex3, Edge3> aStar = GraphAlg.aStarGoal(graph, finalVertex, Heuristic3::heuristic);

			List<Integer> gp_as = aStar.search().get().getEdgeList().stream().map(x -> x.alternativa)
					.collect(Collectors.toList());

			Solucion3 s_as = Solucion3.create(gp_as);
			System.out.println(s_as);
		}

	}

}
