package si2024.daniellinfonyealu.p03.Condiciones;

import si2024.daniellinfonyealu.p03.Mundo53;
import si2024.daniellinfonyealu.p03.IA.Condicion;
import si2024.daniellinfonyealu.p03.IA.Mundo;
import tools.Vector2d;

public class CondicionHayPuertas implements Condicion {

	@Override
	/*
	 * Devuelve true si lo mas cerca que tiene es una enfermera y false si es una
	 * infeccion
	 */
	public boolean seCumple(Mundo m) {
		Mundo53 m53 = (Mundo53) m;
		
		if(m53.hayPuertas())
			return true;
		else
			return false;
	}

}
