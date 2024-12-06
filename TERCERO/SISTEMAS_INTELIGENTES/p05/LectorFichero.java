package si2024.daniellinfonyealu.p05;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import si2024.daniellinfonyealu.p05.Restricciones.Debe_Ser;
import si2024.daniellinfonyealu.p05.Restricciones.Restriccion;

public class LectorFichero {
	private File f;
	Scanner sc;
	public Variable[][] tablero = new Variable[9][9];
	public ArrayList<Asignacion> asignaciones = new ArrayList<Asignacion>();
	public ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();

	public LectorFichero(String archivo) throws FileNotFoundException {
	     f = new File(archivo);
	     sc = new Scanner(f);
	    
	 }
	
	public boolean haySigLinea() {
		return sc.hasNextLine();
	}
	
	public void leerLinea() {
		asignaciones.clear();
		restricciones.clear();
		tablero = new Variable[9][9];
	            String linea = sc.nextLine();
	            for (int i = 0; i < 81; i++) {
	                 int fila = i / 9;
	                 int columna = i % 9;
	                 Character v = linea.charAt(i);
	                 tablero[fila][columna] = new Variable(fila, columna);
	                 asignaciones.add(new Asignacion(tablero[fila][columna]));
	                 
	                 if (v != '.') {
	                	 	int valor = Character.getNumericValue(v);
	                        restricciones.add(new Debe_Ser(asignaciones.get(i), valor));
	                 }
	             }
	}

}
