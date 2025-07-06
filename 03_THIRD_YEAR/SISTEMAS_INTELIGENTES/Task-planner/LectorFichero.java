package si2024.daniellinfonyealu.p06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import si2024.daniellinfonyealu.p06.Acciones.Accion;

public class LectorFichero {
	private File f;
	private Scanner sc;
	private Estado estadoInicial;
	private Estado estadoFinal;
	private List<Accion> acciones;

	public LectorFichero(String archivo) throws FileNotFoundException {
		f = new File(archivo);
		sc = new Scanner(f);
		acciones = new ArrayList<>();
		estadoInicial = new Estado("Ei");
		estadoFinal = new Estado("Ef");
		leerFichero();
	}

	private void leerFichero() {
		while (sc.hasNextLine()) {
			String linea = sc.nextLine().trim();
			if (linea.startsWith("#")) {
				continue; // comentario
			}
			if (linea.startsWith("Ei:")) {
				String[] proposiciones = linea.substring(3).trim().split(",");
				for (String proposicion : proposiciones) {
					estadoInicial.agregarProposicion(proposicion.trim());
				}
			} else if (linea.startsWith("Ef:")) {
				String[] proposiciones = linea.substring(3).trim().split(",");
				for (String proposicion : proposiciones) {
					estadoFinal.agregarProposicion(proposicion.trim());
				}
			} else if (linea.startsWith("Accion:")) {
				String[] partes = linea.substring(7).split(";");
				if (partes.length != 0) {
					String nombre = (partes.length > 0) ? partes[0].trim() : "";
                    String precond = (partes.length > 1) ? partes[1].trim() : "";
                    String adicion = (partes.length > 2) ? partes[2].trim() : "";
                    String supresion = (partes.length > 3) ? partes[3].trim() : "";
					Accion accion = new Accion(nombre, precond, adicion, supresion);
					acciones.add(accion);
				}
			}
		}
	}

	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public Estado getEstadoFinal() {
		return estadoFinal;
	}

	public void setEstadoFinal(Estado estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public List<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}
	
	
}
