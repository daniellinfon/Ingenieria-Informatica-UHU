package si2024.daniellinfonyealu.p06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import si2024.daniellinfonyealu.p06.Acciones.Accion;

public class Estado implements Cloneable {

	private String nombre;
	private List<String> proposiciones;

	public Estado(String nombre) {
		this.nombre = nombre;
		proposiciones = new ArrayList<String>();
	}

	public Estado(List<String> proposiciones) {
		this.proposiciones = proposiciones;
	}

	public void agregarProposicion(String prop) {
		proposiciones.add(prop);
	}

	public void aplicarAccion(Accion ac) {
		for (String sup : ac.getSupresion()) {
			this.proposiciones.remove(sup);
		}
		for (String ad : ac.getAdicion()) {
			this.proposiciones.add(ad);
		}
	}

	public boolean contiene(String prop) {
		return proposiciones.contains(prop);
	}

	public boolean contieneTodas(List<String> list) {
		return this.proposiciones.containsAll(list);
	}

	@Override
	public String toString() {
		return "Estado " + nombre + ": {" + proposiciones + "}";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getProposiciones() {
		return proposiciones;
	}

	public void setDesetProposicionesf(ArrayList<String> props) {
		this.proposiciones = props;
	}

}
