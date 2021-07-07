package ejercicios;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;

public class E2 {

	public static void apartadoA() {
		Graph<String, DefaultEdge> grafo = lecturaGrafo();

		GreedyColoring coloreado = new GreedyColoring(grafo);

		System.out.println(coloreado.getColoring());
		System.out.println(coloreado.getColoring().getColorClasses());
	}

	public static void apartadoB() {
		Graph<String, DefaultEdge> grafo = lecturaGrafo();

		GreedyColoring coloreado = new GreedyColoring(grafo);
		List<Set<String>> colors = coloreado.getColoring().getColorClasses();
		Graphs2.<String, DefaultEdge>toDot(grafo, "ficheros/E2/E2B.gv", v -> String.format("%s", v),
				e -> String.format(""),
				v -> GraphColors.getColor(
						colors.get(0).contains(v) ? Color.black : colors.get(1).contains(v) ? Color.red : Color.yellow),
				e -> GraphColors.getColor(Color.black));
	}

	public static Graph<String, DefaultEdge> lecturaGrafo() {
		Graph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

		List<String> lineasFichero = Files2.linesFromFile("ficheros/PI5Ej2DatosEntrada.txt");

		for (int i = 0; i < lineasFichero.size(); i++) {
			String linea = lineasFichero.get(i);
			String[] trozos = linea.split(": ");
			String[] grupos = trozos[1].split(",");
			for (int j = 0; j < grupos.length; j++) {
				for (int k = 0; k < grupos.length; k++) {
					if (j != k) {
						grafo.addVertex(grupos[j].trim());
						grafo.addVertex(grupos[k].trim());
						grafo.addEdge(grupos[j].trim(), grupos[k].trim());
					}

				}
			}

		}
		return grafo;
	}

	public static void main(String[] args) {
		apartadoA();
		apartadoB();
	}

}
