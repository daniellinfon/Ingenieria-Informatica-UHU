package si2024.daniellinfonyealu.p06.Acciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import si2024.daniellinfonyealu.p06.Estado;

public class Accion {

	private String nombre;
	private List<String> precond = new ArrayList<String>();
	private List<String> adicion = new ArrayList<String>();
	private List<String> supresion = new ArrayList<String>();

	public Accion(String nombre, String precond, String adicion, String supresion) {
		this.nombre = nombre.trim();

		if (!precond.trim().replaceAll(" ", "").equals("")) {
			String[] strs = precond.trim().replaceAll(" ", "").split(",");
			for (String str : strs)
				this.precond.add(str);
		}

		if (!adicion.trim().replaceAll(" ", "").equals("")) {
			String[] strs = adicion.trim().replaceAll(" ", "").split(",");
			for (String str : strs)
				this.adicion.add(str);
		}

		if (!supresion.trim().replaceAll(" ", "").equals("")) {
			String[] strs = supresion.trim().replaceAll(" ", "").split(",");
			for (String str : strs)
				this.supresion.add(str);
		}
	}

	@Override
	public String toString() {
		return "Accion [nombre: '" + nombre + "', precond: {" + precond + "}, adicion: {" + adicion + "}, supresion: {"
				+ supresion + "}]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getPrecond() {
		return precond;
	}

	public void setPrecond(List<String> precond) {
		this.precond = precond;
	}

	public List<String> getAdicion() {
		return adicion;
	}

	public void setAdicion(List<String> adicion) {
		this.adicion = adicion;
	}

	public List<String> getSupresion() {
		return supresion;
	}

	public void setSupresion(List<String> supresion) {
		this.supresion = supresion;
	}
}
