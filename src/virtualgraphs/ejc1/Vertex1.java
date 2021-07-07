package ejc1;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class Vertex1 extends ActionVirtualVertex<Vertex1, Edge1, Integer> {

	public Integer indice;
	public ArrayList<Integer> afinidades;

	public static ArrayList<Integer> inicializarLista() {
		ArrayList<Integer> lis = new ArrayList<>();
		for (int i = 0; i < Lectura1.NUM_GR; i++) {
			lis.add(Lectura1.NUM_AL_POR_GR);
		}
		return lis;
	}

	public static ArrayList<Integer> finalizarLista() {
		ArrayList<Integer> lis = new ArrayList<>();
		for (int i = 0; i < Lectura1.NUM_GR; i++) {
			lis.add(0);
		}
		return lis;
	}

	public static Vertex1 verticeInicial() {
		return of(0, inicializarLista());
	}

	public static Vertex1 verticeFinal() {
		return new Vertex1(Lectura1.NUM_AL, finalizarLista());
	}

	public static Vertex1 of(Integer i, ArrayList<Integer> lis) {
		return new Vertex1(i, lis);
	}

	public Vertex1(Integer i, ArrayList<Integer> lis) {
		super();
		this.indice = i;
		this.afinidades = lis;
	}

	public static Boolean goal(Vertex1 v) {
		return v.indice == Lectura1.NUM_AL;
	}

	@Override
	public Boolean isValid() {
		return indice >= 0 && indice <= Lectura1.NUM_AL;
	}

	@Override
	public List<Integer> actions() {
		if (this.indice == Lectura1.NUM_AL) {
			return new ArrayList<>();
		}
		List<Integer> alternativas = new ArrayList<>();
		for (int i = 0; i < afinidades.size(); i++) {
			if (afinidades.get(i) > 0) {
				alternativas.add(i);// <
			}
		}
		return alternativas;
	}

	@Override
	public Vertex1 neighbor(Integer a) {
		Vertex1 r;
		ArrayList<Integer> lis = new ArrayList<>(afinidades); // creamos una lista que copia la lista de afinidades.
		lis.set(a, lis.get(a) - 1);
		r = Vertex1.of(indice + 1, lis);

		return r;
	}

	@Override
	public Edge1 edge(Integer a) {
		Vertex1 v = this.neighbor(a);
		return Edge1.of(this, v, a);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((afinidades == null) ? 0 : afinidades.hashCode());
		result = prime * result + ((indice == null) ? 0 : indice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex1 other = (Vertex1) obj;
		if (afinidades == null) {
			if (other.afinidades != null)
				return false;
		} else if (!afinidades.equals(other.afinidades))
			return false;
		if (indice == null) {
			if (other.indice != null)
				return false;
		} else if (!indice.equals(other.indice))
			return false;
		return true;
	}

	public Integer getIndice() {
		return indice;
	}

	public ArrayList<Integer> getAfinidades() {
		return afinidades;
	}

	public static Solucion1 getSolucion(GraphPath<Vertex1, Edge1> path) {
		return Solucion1.create(path);
	}
}
