package ejc3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.Tuple;
import us.lsi.common.Tuple3;

public class Lectura3 {

	public static Integer NUM_PR;

	private static List<Tuple3<String, Double, Set<String>>> productos;
	private static Set<String> funcionalidades;

	public static void lectura(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		funcionalidades = new HashSet<>();
		productos = new ArrayList<>();
		String v1 = lineas.get(0);
		String[] args = v1.split(":");
		String[] funcionalidadesSplit = args[1].trim().split(",");
		for (String f : funcionalidadesSplit) {
			funcionalidades.add(f);
		}
		lineas.remove(0);
		for (String linea : lineas) {
			String[] sep1 = linea.split(":");
			String[] sep2 = sep1[0].split(" ");
			String[] sep3 = sep1[1].trim().split(",");

			String objeto = sep2[0];
			Double precio = Double.valueOf(sep2[1].replace("(", "").trim());
			Set<String> funcionalidades = new HashSet<>();
			for (String f : sep3) {
				funcionalidades.add(f);
			}
			productos.add(Tuple.create(objeto, precio, funcionalidades));

		}
		NUM_PR = productos.size();
	}

	public static List<Tuple3<String, Double, Set<String>>> getProductos() {
		return productos;
	}

	public static Tuple3<String, Double, Set<String>> getProducto(int index) {
		return productos.get(index);
	}

	public static Double getPrecio(Integer index) {
		return productos.get(index).getV2();
	}

	public static Set<String> getFuncionalidades() {
		return funcionalidades;
	}

	public static void toConsole() {
		System.out.println("Productos = " + Lectura3.getProductos());
		System.out.println("Funcionalidades = " + Lectura3.getFuncionalidades());
	}

	public static void main(String[] args) {
		lectura("ficheros/PI7Ej3DatosEntrada1.txt");
		toConsole();
	}

}
