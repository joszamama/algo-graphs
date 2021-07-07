package ejc3;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.common.Lists2;
import us.lsi.common.Tuple3;

public class Solucion3 {

	private List<Tuple3<String, Double, Set<String>>> seleccion;

	public static Solucion3 create(List<Integer> salida) {
		return new Solucion3(salida);
	}

	private Solucion3(List<Integer> salida) {
		this.seleccion = Lists2.empty();
		for (int i = 0; i < salida.size(); i++) {
			if (salida.get(i) == 1) {
				Tuple3<String, Double, Set<String>> objeto = Lectura3.getProductos().get(i);
				seleccion.add(objeto);
			}
		}
	}

	public String toString() {
		String toString = "Composicion del lote seleccionado:\n";
		for (Tuple3<String, Double, Set<String>> objeto : seleccion) {
			toString += "   " + objeto.v1 + " ( " + objeto.v2 + " euros)" + " => " + objeto.v3 + "\n";
		}
		toString += "Funcionalidades de la seleccion: " + getFuncionalidades() + "\n";
		toString += "Precio total del lote seleccionado: " + getPrecioTotal() + "\n\n";
		return toString;
	}

	private Double getPrecioTotal() {
		return seleccion.stream().map(x -> x.v2).reduce(0., (accum, x) -> {
			return accum + x;
		});
	}

	private Set<String> getFuncionalidades() {
		return seleccion.stream().map(x -> x.v3).flatMap(string -> string.stream()).collect(Collectors.toSet());
	}
}
