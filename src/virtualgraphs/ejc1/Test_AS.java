package ejc1;

import java.io.IOException;
import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.GraphAlg;
import us.lsi.graphs.virtual.EGraph;

public class Test_AS {

	public static void main(String[] args) throws IOException {
		for (int indice = 1; indice < 4; indice++) {
			Locale.setDefault(new Locale("en", "US"));
			Lectura1.lectura("ficheros/PI7Ej1DatosEntrada" + indice + ".txt");

			Vertex1 e1 = Vertex1.verticeInicial();
			Vertex1 e2 = Vertex1.verticeFinal();

			System.out.println("##### PI-7 EJ1 - " + indice + " - Algoritmo A* ####");
			EGraph<Vertex1, Edge1> graph = Graphs2.simpleVirtualGraph(e1, x -> -x.getEdgeWeight());
			AStar<Vertex1, Edge1> ms = GraphAlg.aStarEnd(graph, e2, Heuristic1::heuristic);

			GraphPath<Vertex1, Edge1> path = ms.search().get();
			Vertex1.getSolucion(path);
		}
	}
}
