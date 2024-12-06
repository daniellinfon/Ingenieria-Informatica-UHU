package GenerarAFD;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GenerarFichero {

	private String id;

	public GenerarFichero(String id) {
		this.id = id;
	}

	public void generar(ArrayList<Fila> conjuntoFilas) {
		String contenido = "public class " + id + "{\n\n" + "\tpublic int transition(int state, char symbol) {\n"
				+ " \t\tswitch(state) {\n";

		for (Fila f : conjuntoFilas) {
			contenido += "\t\t\tcase " + f.getEstado().getId() + ": \n";
			for (Transicion trans : f.getTransiciones()) {
				contenido += "\t\t\t\tif(symbol == '" + trans.getSimbolo() + "') return " + trans.getDestino() + ";\n";
			}
			contenido += "\t\t\t\treturn -1; \n";
		}

		contenido += "\t\t\tdefault:\n" + "\t\t\t\treturn -1;\n" + "\t\t}\n" + "\t}\n\n"
				+ "\tpublic boolean isFinal(int state) {\n" + "\t\tswitch(state) {\n";
		for (Fila f : conjuntoFilas) {
			contenido += "\t\t\tcase " + f.getEstado().getId() + ": return " + f.getEstado().isFinal() + ";\n";
		}
		contenido += "\t\t\tdefault: return false;\n" + "\t\t}\n" + "\t}\n" + "}";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(id+".java"))) {
			writer.write(contenido);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
