package ejercicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;

public class E3 {

	public static void apartadoA() {
		Graph<String, DefaultEdge> grafo = lecturaGrafo();

		TopologicalOrderIterator<String, DefaultEdge> toi = new TopologicalOrderIterator<>(grafo);

		List<String> asignaturasTopologico = new ArrayList<>();
		toi.forEachRemaining(v -> asignaturasTopologico.add(v));

		System.out.println(asignaturasTopologico);
	}

	public static void apartadoB() {
		// Vértices sin ninguna arista de entrada, solo de salida
		Graph<String, DefaultEdge> grafo = lecturaGrafo();
		Set<String> sinEntrada = new HashSet<>();
		for (String as : grafo.vertexSet()) {
			if (grafo.incomingEdgesOf(as).size() == 0) {
				sinEntrada.add(as);
			}
		}
		System.out.println(sinEntrada);

		Graphs2.<String, DefaultEdge>toDot(grafo, "ficheros/E3/E3B.gv", v -> String.format("%s", v),
				e -> String.format(""), v -> GraphColors.getColor(sinEntrada.contains(v) ? Color.blue : Color.black),
				e -> GraphColors.getColor(Color.black));
	}

	public static void apartadoC(Set<String> asignaturas) {
		Graph<String, DefaultEdge> grafo = lecturaGrafo();
		Graph<String, DefaultEdge> copia = lecturaGrafo();
		Set<String> res = new HashSet<>();

		copia.removeAllVertices(asignaturas);
		for (String as : copia.vertexSet()) {
			if (copia.incomingEdgesOf(as).size() == 0) {
				res.add(as);
			}
		}

		System.out.println(res);

		Graphs2.<String, DefaultEdge>toDot(grafo, "ficheros/E3/E3C.gv", v -> String.format("%s", v),
				e -> String.format(""),
				v -> GraphColors
						.getColor(asignaturas.contains(v) ? Color.blue : res.contains(v) ? Color.yellow : Color.black),
				e -> GraphColors.getColor(Color.black));

	}

	public static Graph<String, DefaultEdge> lecturaGrafo() {
		Graph<String, DefaultEdge> grafo = new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

		List<String> lineasFichero = Files2.linesFromFile("ficheros/PI5Ej3DatosEntrada.txt");

		for (int i = 0; i < lineasFichero.size(); i++) {
			String linea = lineasFichero.get(i);
			String[] trozos = linea.split(": ");
			if (trozos.length == 1) {
				String vertice = trozos[0].trim();
				grafo.addVertex(vertice);

			} else {
				String v1 = trozos[0].trim();
				grafo.addVertex(v1);
				String[] subj = trozos[1].split(",");
				for (int j = 0; j < subj.length; j++) {
					String v2 = subj[j].replace("{", "").replace("}", "");
					if (v2 != "") {
						grafo.addVertex(v2);
						grafo.addEdge(v2, v1);
					}

				}
			}

		}
		return grafo;
	}

	public static void main(String[] args) {
		Set<String> test1 = new HashSet<>();
		test1.add("Asignatura_01");
		test1.add("Asignatura_02");
		test1.add("Asignatura_03");
		test1.add("Asignatura_04");
		test1.add("Asignatura_05");

		apartadoA();
		// apartadoB();
		// apartadoC(test1);
	}

}
