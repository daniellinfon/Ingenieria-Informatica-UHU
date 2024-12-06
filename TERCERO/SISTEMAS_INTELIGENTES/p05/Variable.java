package si2024.daniellinfonyealu.p05;

import java.util.ArrayList;

public class Variable {
    
    public int fila;
	public int columna;
	private ArrayList<Integer> dominio = new ArrayList<Integer>();
	
	public Variable(int fila, int columna) {
		this.columna = columna;
		this.fila = fila;
		for(int i=1; i<10; i++) {
			dominio.add(i);
		}
		
	}
	
	public ArrayList<Integer> getDominio() {
		return dominio;
	}
	
	
}
