package si2024.daniellinfonyealu.p06;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import si2024.daniellinfonyealu.p06.Acciones.Accion;


public class Practica06_exe {
	public static void main(String[] args) throws FileNotFoundException {
		LectorFichero lf = new LectorFichero("plan_1.txt");
		GeneradorFichero gf = new GeneradorFichero("solucion_plan1.txt");
		
		System.out.println(lf.getEstadoInicial());
		System.out.println(lf.getEstadoFinal());
		
		for(Accion accion : lf.getAcciones()) {
			System.out.println(accion);
		}
		
		Problema problem = new Problema(lf.getEstadoInicial(), lf.getEstadoFinal(), lf.getAcciones());
		AlgoritmoStrips strips =  new AlgoritmoStrips(problem);
		
		List<Accion> plan = strips.resolver();

        if (plan != null) {
            System.out.println("\nPlan encontrado:");
            for (Accion accion : plan) {
                System.out.println(accion.getNombre());
            }
            gf.escribeFich(plan);
        } else {
            System.out.println("No se pudo encontrar un plan.");
        }
	}
}
