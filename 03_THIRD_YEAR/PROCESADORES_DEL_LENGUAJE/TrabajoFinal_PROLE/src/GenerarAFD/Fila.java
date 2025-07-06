package GenerarAFD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Fila {
	
	private Estado estado;
	private ArrayList<Integer> posPuntos;
	private ArrayList<Transicion> transiciones;
	
	public Fila(Estado estado, ArrayList<Integer> posPuntos, ArrayList<Transicion> transiciones) {
		this.estado = estado;
		this.posPuntos = posPuntos;
		this.transiciones = transiciones;
	}
	
	public void anadirTransicion(Transicion T) {
		transiciones.add(T);
	}

	public Estado getEstado() {
		return estado;
	}

	public ArrayList<Integer> getPosPuntos() {
		return posPuntos;
	}

	public ArrayList<Transicion> getTransiciones() {
		return transiciones;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fila other = (Fila) obj;
		Collections.sort(posPuntos);
		Collections.sort(other.posPuntos);
		return Objects.equals(posPuntos, other.posPuntos);
	}
	
	
	
}
