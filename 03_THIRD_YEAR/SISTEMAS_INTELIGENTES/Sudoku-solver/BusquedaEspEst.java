package si2024.daniellinfonyealu.p05;

import java.util.ArrayList;

import si2024.daniellinfonyealu.p05.Restricciones.*;

public class BusquedaEspEst {
	private ArrayList<EstadoSudoku> Abierta = new ArrayList<EstadoSudoku>();
	private ArrayList<Restriccion> restricciones;
	private int indAnterior = -1;
	
	public ArrayList<Asignacion> Algoritmo(EstadoSudoku inicial, ArrayList<Restriccion> resDS) {
		this.restricciones = new ArrayList<Restriccion>();
		
		for(Restriccion r : resDS) {
			this.restricciones.add(new Debe_Ser(r));
		}
		
		Abierta.add(inicial);
		EstadoSudoku actual = inicial;
		
		while(!Abierta.isEmpty()) {
			
			actual = Abierta.remove(Abierta.size()-1);
			
			if(actual.valorSucesor != 0) {
				if(indAnterior >= actual.indice) { // Backtracking
					int max = actual.indice + (indAnterior - actual.indice);
					actual.forwardCheckingReverse();
					for(int i = actual.indice; i<max; i++) {
						actual.getAsignaciones().get(i).setValor(-1);
						actual.forwardCheckingReverse();
					}
				}
				indAnterior = actual.indice;
				actual.getAsignaciones().get(actual.indice-1).setValor(actual.valorSucesor);
				actual.forwardChecking(actual.getAsignaciones().get(actual.indice-1));
				actual.ordenarAsignaciones(actual.indice);
				
			}
			if(esMeta(actual))
				return actual.getAsignaciones();
			else {
				ArrayList<EstadoSudoku> sucesores = getSucesores(actual);
			    Abierta.addAll(sucesores);
				
			}
		}
		return null;
	}
	
	
	private boolean esMeta(EstadoSudoku act) {
		if(act.numAsignaciones == 81)
			return true;
		else
			return false;
	}
	
	private ArrayList<EstadoSudoku> getSucesores(EstadoSudoku actual) {
		ArrayList<EstadoSudoku> sucesores = new ArrayList<EstadoSudoku>();
		int ind = actual.indice;
		Asignacion asig = actual.getAsignaciones().get(ind);
		for(int i : asig.getVariable().getDominio()) {
			
				actual.getAsignaciones().get(ind).setValor(i);
				if(seCumple(actual)) {
					EstadoSudoku est = new EstadoSudoku(actual, restricciones);
					est.valorSucesor = i;
					est.indice = ind + 1;
					sucesores.add(est);
				} 
					
		}
		actual.getAsignaciones().get(ind).setValor(-1);
		return sucesores;
	}
	
	private boolean seCumple(EstadoSudoku e) {
		for(Restriccion r : e.restricciones) {
			if(!r.seCumple())
				return false;
		}
		return true;
	}
}
