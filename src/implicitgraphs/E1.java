package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.graph.SimpleGraph;

import tools.Amistad;
import tools.Persona;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class E1 {
	public static void apartadoA() {
		// APARTADO A - Algoritmo para sacar los vértices sin aristas - Obtener el mayor
		// número de aristas - Sacar los vértices con ese número de aristas

		SimpleGraph<Persona, Amistad> grafo = GraphsReader.newGraph("ficheros/PI5Ej1DatosEntrada.txt",
				Persona::ofFormat, Amistad::ofFormat, Graphs2::simpleGraph);

		List<Persona> sinAristas = new ArrayList<>();
		for (Persona p : grafo.vertexSet()) {
			if (grafo.degreeOf(p) == 0) {
				sinAristas.add(p);
			}
		}

		List<Persona> maxAristas = new ArrayList<>();
		for (Persona p : grafo.vertexSet()) {
			if (grafo.degreeOf(p) == gradoMaximo(grafo)) {
				maxAristas.add(p);
			}
		}

		System.out.println("Vértices sin aristas: " + sinAristas);
		System.out.println("Vértices con el número máximo de aristas: " + maxAristas);

		Graphs2.<Persona, Amistad>toDot(grafo, "ficheros/E1/E1A.gv", v -> String.format("%s", v.getNombre()),
				e -> String.format(""),
				v -> GraphColors.getColor(
						sinAristas.contains(v) ? Color.gray : maxAristas.contains(v) ? Color.red : Color.black),
				e -> GraphColors.getColor(Color.black));

	}

	public static void apartadoB(Persona p1, Persona p2) {
		// APARTADO B - Con un nodo inicio y un nodo final, obtener el camino mínimo
		// entre ellos

		SimpleGraph<Persona, Amistad> grafo = GraphsReader.newGraph("ficheros/PI5Ej1DatosEntrada.txt",
				Persona::ofFormat, Amistad::ofFormat, Graphs2::simpleGraph);

		DijkstraShortestPath<Persona, Amistad> caminoCorto = new DijkstraShortestPath<Persona, Amistad>(grafo);
		GraphPath<Persona, Amistad> gpth = caminoCorto.getPath(p1, p2);
		List<Persona> personas = gpth.getVertexList();
		System.out.println(personas);

		Graphs2.<Persona, Amistad>toDot(grafo, "ficheros/E1/E1B.gv", v -> String.format("%s", v.getNombre()),
				e -> String.format(""), v -> GraphColors.getColor(personas.contains(v) ? Color.blue : Color.gray),
				e -> GraphColors
						.getColor(personas.contains(e.getSource()) && personas.contains(e.getTarget()) ? Color.blue
								: Color.gray));
	}

	public static void apartadoC() {
		// APARTADO C - Obtener las componentes conexas del grafo

		SimpleGraph<Persona, Amistad> grafo = GraphsReader.newGraph("ficheros/PI5Ej1DatosEntrada.txt",
				Persona::ofFormat, Amistad::ofFormat, Graphs2::simpleGraph);

		ConnectivityInspector<Persona, Amistad> cnect = new ConnectivityInspector<Persona, Amistad>(grafo);
		List<Set<Persona>> conexas = cnect.connectedSets();
		System.out.println(conexas);

		Graphs2.<Persona, Amistad>toDot(grafo, "ficheros/E1/E1C.gv", v -> String.format("%s", v.getNombre()),
				e -> String.format(""),
				v -> GraphColors.getColor(conexas.get(0).contains(v) ? Color.black
						: conexas.get(1).contains(v) ? Color.red
								: conexas.get(2).contains(v) ? Color.yellow
										: conexas.get(3).contains(v) ? Color.gray : Color.cyan),
				e -> GraphColors.getColor(
						conexas.get(0).contains(e.getSource()) && conexas.get(0).contains(e.getTarget()) ? Color.black
								: conexas.get(1).contains(e.getSource()) && conexas.get(1).contains(e.getTarget())
										? Color.red
										: conexas.get(2).contains(e.getSource())
												&& conexas.get(2).contains(e.getTarget())
														? Color.yellow
														: conexas.get(3).contains(e.getSource())
																&& conexas.get(3).contains(e.getTarget()) ? Color.gray
																		: Color.cyan));
	}

	public static void apartadoD() {
		// APARTADO D - Minimizar el número de vertices tal que esten cubiertas todas
		// las aristas.

		SimpleGraph<Persona, Amistad> grafo = GraphsReader.newGraph("ficheros/PI5Ej1DatosEntrada.txt",
				Persona::ofFormat, Amistad::ofFormat, Graphs2::simpleGraph);

		GreedyVCImpl<Persona, Amistad> vCover = new GreedyVCImpl<>(grafo);
		Set<Persona> enviarCuestionarios = vCover.getVertexCover();
		System.out.println(enviarCuestionarios);

		Graphs2.<Persona, Amistad>toDot(grafo, "ficheros/E1/E1D.gv", v -> String.format("%s", v.getNombre()),
				e -> String.format(""),
				v -> GraphColors.getColor(enviarCuestionarios.contains(v) ? Color.blue : Color.gray),
				e -> GraphColors.getColor(Color.gray));
	}

	public static Integer gradoMaximo(Graph<Persona, Amistad> grafo) {
		int i = 0;
		for (Persona v : grafo.vertexSet()) {
			if (grafo.degreeOf(v) > i) {
				i = grafo.degreeOf(v);
			}
		}
		return i;
	}

	public static void main(String[] args) {
		Persona p1 = Persona.ofName("Juan");
		Persona p2 = Persona.ofName("Ramiro");

		System.out.println("APARTADO A");
		apartadoA();
		System.out.println("\n\nAPARTADO B");
		apartadoB(p1, p2);
		System.out.println("\n\nAPARTADO C");
		apartadoC();
		System.out.println("\n\nAPARTADO D");
		apartadoD();
	}

}
