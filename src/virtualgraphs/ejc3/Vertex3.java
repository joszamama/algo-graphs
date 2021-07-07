package ejc3;

import java.util.List;
import java.util.Set;
import us.lsi.common.Lists2;
import us.lsi.common.Sets2;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class Vertex3 extends ActionVirtualVertex<Vertex3, Edge3, Integer> {

	public Integer index;
	public Set<String> funcionalidadesFaltantes;

	// Este constructor nos servira para el vértice inicial
	public Vertex3() {
		this.index = 0;
		this.funcionalidadesFaltantes = Sets2.of(Lectura3.getFuncionalidades());
	}

	public Vertex3(Integer i, Set<String> funcionalidadesFaltantes) {
		this.index = i;
		this.funcionalidadesFaltantes = funcionalidadesFaltantes;
	}

	public static Vertex3 of(Integer i, Set<String> funcionalidadesFaltantes) {
		return new Vertex3(i, funcionalidadesFaltantes);
	}

	// Definimos el vértice inicial y el vértice final
	public static Vertex3 initialVertex() {
		return new Vertex3();
	}

	public static Boolean goal(Vertex3 v) {
		return v.index == Lectura3.NUM_PR;
	}

	public Boolean isValid() {
		return true;
	}

	public List<Integer> actions() {
		List<Integer> alternativas = Lists2.empty();

		if (this.index >= Lectura3.NUM_PR) {
			return alternativas;
		}

		if (this.index == Lectura3.NUM_PR - 1) {
			Set<String> propuesta = Sets2.of(this.funcionalidadesFaltantes);

			Set<String> items_a_quitar = Lectura3.getProducto(this.index).getV3();
			propuesta.removeAll(items_a_quitar);

			if (propuesta.isEmpty()) {
				alternativas.add(1);
			}
		} else {
			if (funcionalidadesFaltantes.isEmpty()) {
				alternativas.add(0);
			} else {
				alternativas.add(0);
				alternativas.add(1);
			}
		}

		return alternativas;

	}

	public Vertex3 neighbor(Integer a) {
		Set<String> elementos_faltantes = Sets2.copy(this.funcionalidadesFaltantes);

		if (a == 0 && elementos_faltantes.isEmpty()) {
			return Vertex3.of(Lectura3.NUM_PR, elementos_faltantes);
		}

		if (a == 1) {
			Set<String> items_a_quitar = Sets2.of(Lectura3.getProducto(this.index).getV3());
			elementos_faltantes.removeAll(items_a_quitar);
		}
		return Vertex3.of(this.index + 1, elementos_faltantes);
	}

	public Edge3 edge(Integer a) {
		return Edge3.of(this, this.neighbor(a), a);
	}

	@Override
	public String toString() {
		return "Vertex3 [index=" + index + ", funcionalidadesFaltantes=" + funcionalidadesFaltantes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((funcionalidadesFaltantes == null) ? 0 : funcionalidadesFaltantes.hashCode());
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
		Vertex3 other = (Vertex3) obj;
		if (funcionalidadesFaltantes == null) {
			if (other.funcionalidadesFaltantes != null)
				return false;
		} else if (!funcionalidadesFaltantes.equals(other.funcionalidadesFaltantes))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		return true;
	}

}
