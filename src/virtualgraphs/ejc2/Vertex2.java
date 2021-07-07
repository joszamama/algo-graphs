package ejc2;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import us.lsi.common.Lists2;
import us.lsi.graphs.virtual.ActionSimpleEdge;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class Vertex2 extends ActionVirtualVertex<Vertex2, ActionSimpleEdge<Vertex2, Integer>, Integer> {

	public Integer index;

	public List<Integer> ca;

	private Double cMax;
	private Integer aMax, aMin;

	public Vertex2() { // Este constructor nos servira para el vértice inicial

		this.index = 0;
		this.ca = Lists2.copy(0, Lectura2.NUM_AB);

		this.aMax = 0;
		this.aMin = 0;
		this.cMax = 0.;

	}

	public Vertex2(Integer index, List<Integer> ca) {
		super();
		this.index = index;
		this.ca = ca;

		this.aMax = IntStream.range(0, Lectura2.NUM_AB).boxed().max(Comparator.comparing(i -> ca.get(i))).get();

		this.aMin = IntStream.range(0, Lectura2.NUM_AB).boxed().min(Comparator.comparing(i -> ca.get(i))).get();

		this.cMax = (double) this.ca.get(aMax);

	}

	public static Vertex2 of(Integer index, List<Integer> ca) {
		return new Vertex2(index, ca);
	}

	// Definimos el vértice inicial y el vértice final
	public static Vertex2 initialVertex() {
		return new Vertex2();
	}

	public Integer getIndex() {
		return index;
	}

	public List<Integer> getCa() {
		return ca;
	}

	public Double getcMax() {
		return cMax;
	}

	public Integer getaMax() {
		return aMax;
	}

	public Integer getaMin() {
		return aMin;
	}

	public static Boolean goal(Vertex2 v) {
		return v.index == Lectura2.NUM_CA;
	}

	public static Vertex2 copy(Vertex2 c) {
		return new Vertex2(c.index, c.ca);
	}

	@Override
	public String toString() {
		return "AbogadoVertex [index=" + index + ", ca=" + ca + ", cMax=" + cMax + ", aMax=" + aMax + ", aMin=" + aMin
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ca == null) ? 0 : ca.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
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
		Vertex2 other = (Vertex2) obj;
		if (ca == null) {
			if (other.ca != null)
				return false;
		} else if (!ca.equals(other.ca))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		return true;
	}

	@Override
	public Boolean isValid() {
		Boolean cond = this.index >= 0 && this.index <= Lectura2.NUM_CA;
		return cond;
	}

	@Override
	public List<Integer> actions() {
		List<Integer> alternativas = Lists2.empty();

		if (this.index >= Lectura2.NUM_CA) {
			return alternativas;
		}
		if (this.index == Lectura2.NUM_CA - 1) {
			List<Integer> ls = Lists2.copy(this.ca);
			for (int i = 0; i < ls.size(); i++) {

				Integer v = ls.get(i);
				v += Lectura2.getAbogado(i).getV2().get(this.index);
				ls.set(i, v);

			}

			aMin = IntStream.range(0, Lectura2.NUM_AB).boxed().min(Comparator.comparing(i -> ls.get(i))).get();

			alternativas.add(aMin);

		} else {
			for (int i = 0; i < Lectura2.NUM_AB; i++) {
				alternativas.add(i);
			}
		}
		return alternativas;
	}

	@Override
	public Vertex2 neighbor(Integer a) {

		List<Integer> ls = Lists2.copy(this.ca);

		Integer v = ls.get(a);
		v += Lectura2.getAbogado(a).v2.get(this.index);

		ls.set(a, v);

		return Vertex2.of(this.index + 1, ls);

	}

	public Integer greadyAction() {
		return this.aMin;
	}

	public ActionSimpleEdge<Vertex2, Integer> greadyEdge() {
		return this.edge(this.greadyAction());
	}

	@Override
	public ActionSimpleEdge<Vertex2, Integer> edge(Integer a) {
		return ActionSimpleEdge.of(this, this.neighbor(a), a);
	}

}
