package ejc2;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.DPR;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.GraphAlg;
import us.lsi.graphs.alg.GreedySearch;
import us.lsi.graphs.virtual.ActionSimpleEdge;
import us.lsi.graphs.virtual.EGraph;

public class Test_PDR {

	public static void main(String[] args) {
		for (int indice = 1; indice < 3; indice++) {
			Locale.setDefault(new Locale("en", "US"));

			Lectura2.lectura("ficheros/PI7Ej2DatosEntrada" + indice + ".txt");

			Vertex2 start = Vertex2.initialVertex();
			Predicate<Vertex2> goal = v -> Vertex2.goal(v);

			System.out.println("##### PI-7 EJ2 - " + indice + " - Algoritmo PDR ####");
			EGraph<Vertex2, ActionSimpleEdge<Vertex2, Integer>> graph = Graphs2.simpleVirtualGraphLast(start,
					x -> x.getcMax());

			GreedySearch<Vertex2, ActionSimpleEdge<Vertex2, Integer>> rr = GraphAlg.greedy(graph, Vertex2::greadyEdge,
					goal);

			DynamicProgrammingReduction<Vertex2, ActionSimpleEdge<Vertex2, Integer>> pdr = DPR
					.dynamicProgrammingReductionGoal(graph, goal, (v1, p, v2) -> 0., PDType.Min);

			pdr.bestValue = rr.weightToEnd().get();

			List<Integer> gp_pdr = pdr.search().get().getEdgeList().stream().map(x -> x.action)
					.collect(Collectors.toList());

			Solucion2 s_pdr = Solucion2.create(gp_pdr);
			System.out.println(s_pdr);

		}
	}

}
