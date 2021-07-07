package ejc1;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;

public class Solucion1 {
	public static Solucion1 create(GraphPath<Vertex1, Edge1> path) {
		return new Solucion1(path);
	}

	public Solucion1(GraphPath<Vertex1, Edge1> path) {

		List<List<String>> alumnos = new ArrayList<>();
		Double afinidad = 0.;

		for (int i = 0; i < Lectura1.NUM_GR; i++) {
			alumnos.add(new ArrayList<>());
		}

		for (int j = 0; j < path.getEdgeList().size(); j++) {
			String[] trozos = path.getEdgeList().get(j).toString().replace(")", "").replace("(", "").split(",");
			Integer alumno = Integer.valueOf(trozos[0].trim());
			Integer grupo = Integer.valueOf(trozos[1].trim());
			afinidad += Double.valueOf(trozos[2].trim());
			alumnos.get(grupo).add(Lectura1.getNombreEstudiantes().get(alumno));
		}
		System.out.println("Reparto obtenido: ");
		for (int k = 0; k < alumnos.size(); k++) {
			System.out.println("Grupo " + (k + 1) + ": " + alumnos.get(k));
		}
		System.out.println("Afinidad media: " + afinidad / Lectura1.NUM_AL);

	}

}