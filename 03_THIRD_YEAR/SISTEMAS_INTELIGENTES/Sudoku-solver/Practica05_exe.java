package si2024.daniellinfonyealu.p05;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Practica05_exe {
	public static void main(String[] args) throws FileNotFoundException {
		LectorFichero lf = new LectorFichero("2024_tableros.txt");
		GeneradorFichero gf = new GeneradorFichero("sudokus.txt");
		
		Long timeT = (long) 0.00;
		int i=1;
		while(lf.haySigLinea()) {
			lf.leerLinea();
			Long timeI = System.currentTimeMillis();
			AC3 ace3 = new AC3(lf.restricciones, lf.tablero);
			BusquedaEspEst bee = new BusquedaEspEst();
		
			ace3.resolverAC3();
			
			EstadoSudoku inicial = new EstadoSudoku(lf.asignaciones, lf.restricciones, lf.tablero);
			ArrayList<Asignacion> asignacionesFinales = bee.Algoritmo(inicial, lf.restricciones);
			System.out.println(i);
			i++;
			gf.escribeFich(asignacionesFinales);
			Long timeF = System.currentTimeMillis();
			timeT = (long) (timeT + ((timeF-timeI)/1000.00));
			//System.out.println((timeF-timeI)/1000.00);
		}
		System.out.println(timeT);
		
	}
}
