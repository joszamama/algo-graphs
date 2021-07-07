package ejc3;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.DPR;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;

public class Test_PDR {

	public static void main(String[] args) {
		for (int indice = 1; indice < 4; indice++) {
			Locale.setDefault(new Locale("en", "US"));
			Lectura3.lectura("ficheros/PI7Ej3DatosEntrada" + indice + ".txt");

			// Vértices clave
			Vertex3 start = Vertex3.initialVertex();
			Predicate<Vertex3> finalVertex = v -> Vertex3.goal(v);

			// Grafo
			EGraph<Vertex3, Edge3> graph = Graphs2.simpleVirtualGraph(start, x -> x.getEdgeWeight());

			System.out.println("##### PI-7 EJ" + indice + " PDR ####");

			// Algoritmo PD
			DynamicProgrammingReduction<Vertex3, Edge3> pdr = DPR.dynamicProgrammingReductionGoal(graph, finalVertex,
					Heuristic3::heuristic, PDType.Min);

			List<Integer> gp_pdr = pdr.search().get().getEdgeList().stream().map(x -> x.alternativa)
					.collect(Collectors.toList());
			System.out.println(gp_pdr);

			Solucion3 s_pdr = Solucion3.create(gp_pdr);
			System.out.println(s_pdr);
		}
	}
}
