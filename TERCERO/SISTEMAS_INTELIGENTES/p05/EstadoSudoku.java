package si2024.daniellinfonyealu.p05;

import java.util.ArrayList;
import java.util.Collections;

import si2024.daniellinfonyealu.p05.Restricciones.AllDisjoint;
import si2024.daniellinfonyealu.p05.Restricciones.Debe_Ser;
import si2024.daniellinfonyealu.p05.Restricciones.Restriccion;

public class EstadoSudoku {
	
	public class EstadosFwCh {
		public ArrayList<Variable> vAfectadas = new ArrayList<Variable>();
		public int valor;
		
		public EstadosFwCh(int valor, ArrayList<Variable> vAfectadas) {
			this.valor = valor;
			this.vAfectadas = vAfectadas;
		}
		
	}
	
	private ArrayList<Asignacion> asignaciones = new ArrayList<Asignacion>();
	public ArrayList<Restriccion> restricciones;
	public int indice;
	public int numAsignaciones;
	public int valorSucesor;
	public static ArrayList<EstadosFwCh> listaEstadosFC = new ArrayList<EstadosFwCh>();	
	public static Variable[][] tablero;
	
	// PARA EL ESTADO INICIAL
	public EstadoSudoku(ArrayList<Asignacion> asignaciones, ArrayList<Restriccion> restricciones, Variable[][] tablero) {
		this.asignaciones = asignaciones;
		EstadoSudoku.tablero = tablero;
		this.restricciones = new ArrayList<Restriccion>(restricciones);
		this.numAsignaciones = 0;
		this.indice = 0;
		this.valorSucesor = 0;
		
	    for(int i=0; i<9; i++) {
	    	AllDisjointFilas(i);
	    	AllDisjointColumnas(i);
	    	AllDisjoint3x3(i);
	    }
	    Collections.sort(asignaciones);
	}

	// PARA EL RESTO DE ESTADOS
	public EstadoSudoku(EstadoSudoku e, ArrayList<Restriccion> restricciones) {
		this.indice = e.indice;
		this.valorSucesor = e.valorSucesor;
		this.numAsignaciones = e.numAsignaciones + 1;
		this.asignaciones = e.asignaciones;
		
		this.restricciones = e.restricciones;
		for(Restriccion res : restricciones) {
			Debe_Ser debeSer = (Debe_Ser) res;
			Variable v = debeSer.getAsig().getVariable();
			for(Asignacion i : asignaciones) {
				if(i.getVariable().equals(v)) {
					debeSer.setAsig(i);
				}
			}
		}
		
	}
	
	public void forwardChecking(Asignacion asig) {
		int f = asig.getVariable().fila;
		int c = asig.getVariable().columna;
		int val = asig.getValor();
		
		ArrayList<Variable> vAfectadas = new ArrayList<Variable>();
		
		for(int i = 0; i<9; i++) { // filas y columnas
			if(EstadoSudoku.tablero[f][i].getDominio().remove((Object)val))    
				vAfectadas.add(tablero[f][i]);
			if(EstadoSudoku.tablero[i][c].getDominio().remove((Object)val))
				vAfectadas.add(tablero[i][c]);
		}
		
		int iF = (f / 3) * 3;
	    int iC = (c / 3) * 3;

	    for (int fi = iF; fi < iF + 3; fi++) {
	        for (int co = iC; co < iC + 3; co++) {
	            if (tablero[fi][co].getDominio().remove((Object) val)) {
	                vAfectadas.add(tablero[fi][co]);
	            }
	        }
	    }
		listaEstadosFC.add(new EstadosFwCh(val, vAfectadas));
	}
	
	public void forwardCheckingReverse() {
		EstadosFwCh fc = listaEstadosFC.remove(listaEstadosFC.size()-1);
		for (Variable v : fc.vAfectadas) {
			v.getDominio().add(fc.valor);
		}
	}
	
	public void ordenarAsignaciones(int indice) {
		ArrayList<Asignacion> subLista = new ArrayList<Asignacion>(asignaciones.subList(indice, asignaciones.size()));
		Collections.sort(subLista);
		for(int i=0; i<subLista.size(); i++) {
			asignaciones.set(indice + i, subLista.get(i));
		}
	}
	
	private void AllDisjointFilas(int fila) {
		ArrayList<Asignacion> ad = new ArrayList<Asignacion>();
		for(int i=fila*9; i<81; i++) {
			if(fila == asignaciones.get(i).getVariable().fila) {
				ad.add(asignaciones.get(i));
			}
			if(asignaciones.get(i).getVariable().fila == fila+1)
				break;
		}
		AllDisjoint alldj = new AllDisjoint(ad);
		restricciones.add(alldj);
	}
	
	private void AllDisjointColumnas(int columna) {
		ArrayList<Asignacion> adc = new ArrayList<Asignacion>();
		
		for(int i=columna; i<81; i = i+9) {
			if(columna == asignaciones.get(i).getVariable().columna) {
				adc.add(asignaciones.get(i));
			}
		}
		AllDisjoint alldj = new AllDisjoint(adc);
		restricciones.add(alldj);
	}
	
	private void AllDisjoint3x3(int cuadro) {
	    int inicioFila = (cuadro / 3) * 3; 
	    int inicioColumna = (cuadro % 3) * 3; 
	    
	    ArrayList<Asignacion> ad = new ArrayList<>();
	    
	    for (int i = inicioFila; i < inicioFila + 3; i++) {
	        for (int j = inicioColumna; j < inicioColumna + 3; j++) {
	            ad.add(asignaciones.get(i * 9 + j)); 
	        }
	    }
	    
	    AllDisjoint alldj = new AllDisjoint(ad);
	    restricciones.add(alldj);
	}
	
	public ArrayList<Asignacion> getAsignaciones() {
		return asignaciones;
	}
	
}
