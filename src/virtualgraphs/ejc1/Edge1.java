package ejc1;

import us.lsi.graphs.virtual.ActionSimpleEdge;

public class Edge1 extends ActionSimpleEdge<Vertex1, Integer> {

	public Integer alternativa;

	public static Edge1 of(Vertex1 v1, Vertex1 v2, Integer action) {
		return new Edge1(v1, v2, action);
	}

	public Edge1(Vertex1 v1, Vertex1 v2, Integer action) {
		super(v1, v2);
		this.alternativa = action;
		super.weight = Lectura1.getAfinidad(v1.indice, action).doubleValue();
	}

	public String toString() {
		return String.format("(%d,%d, %.2f)", this.source.indice, alternativa, this.getEdgeWeight());
	}
}
