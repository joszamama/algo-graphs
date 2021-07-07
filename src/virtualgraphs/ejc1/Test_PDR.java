package ejc1;

import java.io.IOException;
import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.DPR;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;

public class Test_PDR {

	public static void main(String[] args) throws IOException {
		for (int indice = 1; indice < 4; indice++) {
			Locale.setDefault(new Locale("en", "US"));
			Lectura1.lectura("ficheros/PI7Ej1DatosEntrada" + indice + ".txt");
			Vertex1 v1 = Vertex1.verticeInicial();
			Vertex1 v2 = Vertex1.verticeFinal();

			System.out.println("##### PI-7 EJ1 - " + indice + " - Algoritmo A* ####");
			EGraph<Vertex1, Edge1> grafo = Graphs2.simpleVirtualGraph(v1, x -> x.getEdgeWeight());
			DynamicProgrammingReduction<Vertex1, Edge1> pd = DPR.dynamicProgrammingReductionEnd(grafo, v2,
					Heuristic1::heuristic, PDType.Max);
			GraphPath<Vertex1, Edge1> camino = pd.search().get();
			Vertex1.getSolucion(camino);
		}
	}

}
