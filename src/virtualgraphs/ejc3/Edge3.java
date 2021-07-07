package ejc3;

import us.lsi.graphs.virtual.ActionSimpleEdge;

public class Edge3 extends ActionSimpleEdge<Vertex3, Integer> {

	public Integer alternativa;

	protected Edge3(Vertex3 v1, Vertex3 v2, Integer action) {
		super(v1, v2, action);
		this.alternativa = action;
		this.weight = Lectura3.getPrecio(v1.index) * action * 1.0;
	}

	public static Edge3 of(Vertex3 v1, Vertex3 v2, Integer action) {
		return new Edge3(v1, v2, action);
	}

	public String toString() {
		return String.format("(%d,%d,%d,%.2f)", this.source.index, this.target.index, alternativa,
				this.getEdgeWeight());
	}
}
