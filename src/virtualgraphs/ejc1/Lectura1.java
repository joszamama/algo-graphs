package ejc1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.Lists2;
import us.lsi.common.Tuple;
import us.lsi.common.Tuple2;

public class Lectura1 {

	public static Integer NUM_AL, NUM_GR, NUM_AL_POR_GR;

	private static List<Tuple2<String, List<Integer>>> estudiantes;
	private static List<Integer> grupos;
	public static List<String> alumnos;

	public static void lectura(String file) {
		List<String> lineas = Files2.linesFromFile(file);
		Lectura1.estudiantes = Lists2.empty();
		Lectura1.grupos = Lists2.empty();
		for (String linea : lineas) {
			String[] args = linea.split(":");
			String estudiante = args[0];
			String[] valores = args[1].split(",");
			List<Integer> estudianteValueG = Lists2.empty();
			for (String valor : valores) {
				int avG = Integer.valueOf(valor.trim());
				estudianteValueG.add(avG);
			}
			Lectura1.estudiantes.add(Tuple.create(estudiante, estudianteValueG));
		}
		int numeros = getEstudiantes().get(0).v2.size();
		for (int i = 1; i <= numeros; i++) {
			getGrupos().add(i);
		}
		NUM_AL = estudiantes.size();
		NUM_GR = grupos.size();
		NUM_AL_POR_GR = NUM_AL / NUM_GR;
	}

	public static List<Tuple2<String, List<Integer>>> getEstudiantes() {
		return Lectura1.estudiantes;
	}

	public static List<Integer> getGrupos() {
		return Lectura1.grupos;
	}

	public static Integer getAfinidad(Integer i, Integer j) {
		return estudiantes.get(i).v2.get(j);
	}

	public static List<String> getNombreEstudiantes() {
		List<String> nombreEstudiantes = new ArrayList<String>();
		for (int i = 0; i < estudiantes.size(); i++) {
			nombreEstudiantes.add(estudiantes.get(i).v1);
		}
		return nombreEstudiantes;
	}

	public static void toConsole() {
		System.out.println("Alumnos = " + Lectura1.getNombreEstudiantes());
	}

	public static void main(String[] args) throws IOException {
		lectura("ficheros/PI7Ej1DatosEntrada1.txt");
		toConsole();
	}
}
