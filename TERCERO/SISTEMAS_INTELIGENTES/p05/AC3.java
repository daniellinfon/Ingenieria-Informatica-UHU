package si2024.daniellinfonyealu.p05;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import si2024.daniellinfonyealu.p05.Restricciones.Debe_Ser;
import si2024.daniellinfonyealu.p05.Restricciones.Restriccion;

public class AC3 {

	private ArrayList<Restriccion> restricciones;
	private Variable[][] tablero;
	private Set<ArcoConsistente> arcos;
	private Set<ArcoConsistente> arcosPosibles;

	public AC3(ArrayList<Restriccion> restricciones, Variable[][] tablero) {
		this.restricciones = restricciones;
		this.tablero = tablero;

		setArcos();
	}

	public void resolverAC3() {

		aplicarRestriccionesIniciales();
		
		Iterator<ArcoConsistente> it = arcos.iterator();
		while (it.hasNext()) {
			ArcoConsistente a = it.next();
			arcos.remove(a);
			if (!a.esConsistente()) {
				anadirArcosNoDistinguidos(a.distinguida);

			}
			it = arcos.iterator();

		}
	}
	
	private void aplicarRestriccionesIniciales() {
        for (Restriccion r : restricciones) {
            if (r instanceof Debe_Ser) {
                Debe_Ser ds = (Debe_Ser) r;
                Variable var = ds.getAsig().getVariable();
                Integer valor = ds.getValor();
                var.getDominio().removeIf(d -> !d.equals(valor));
            }
        }
    }

	public void setArcos() {
		arcos = new HashSet<ArcoConsistente>();
		for (int f = 0; f < 9; f++) {
			for (int c = 0; c < 9; c++) {
				arcosFila(tablero[f][c]);
				arcosColumna(tablero[f][c]);
				arcos3x3(tablero[f][c]);
			}
		}
		arcosPosibles = new HashSet<ArcoConsistente>(arcos);
	}

	public void arcos3x3(Variable v) {
		int fila = (v.fila / 3) * 3;
		int columna = (v.columna / 3) * 3;

		for (int i = fila; i < fila + 3; i++) {
			for (int j = columna; j < columna + 3; j++) {
				if (i != v.fila || j != v.columna) {
					arcos.add(new ArcoConsistente(v, tablero[i][j]));
				}
			}
		}
	}

	public void arcosFila(Variable v) {
		for (int c = 0; c < 9; c++) {
			if (c != v.columna) {
				arcos.add(new ArcoConsistente(v, tablero[v.fila][c]));
			}
		}
	}

	public void arcosColumna(Variable v) {
		for (int f = 0; f < 9; f++) {
			if (f != v.fila) {
				arcos.add(new ArcoConsistente(v, tablero[f][v.columna]));
			}
		}
	}
	
	private void anadirArcosNoDistinguidos(Variable vD) {
		for (ArcoConsistente arc : arcosPosibles) {
			if (arc.noDistinguida.equals(vD)) {
				arcos.add(arc);
			}
		}
	}
}
