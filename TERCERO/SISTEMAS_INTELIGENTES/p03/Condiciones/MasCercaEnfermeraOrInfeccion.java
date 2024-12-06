package si2024.daniellinfonyealu.p03.Condiciones;

import si2024.daniellinfonyealu.p03.Mundo53;
import si2024.daniellinfonyealu.p03.IA.Condicion;
import si2024.daniellinfonyealu.p03.IA.Mundo;
import tools.Vector2d;

public class MasCercaEnfermeraOrInfeccion implements Condicion {

	@Override
	/*
	 * Devuelve true si lo mas cerca que tiene es una enfermera y false si es una
	 * infeccion
	 */
	public boolean seCumple(Mundo m) {
		Mundo53 m53 = (Mundo53) m;
		Vector2d enfermera = m53.enfermeraMasCercana();
		Vector2d infeccion = m53.infectadoMasCercano().get(0);
		Vector2d avatar = m53.getPosAvatar();
		
		if(!m53.hayEnfermeras())
			return false;
		
		if (avatar.dist(enfermera) <= avatar.dist(infeccion))
			return true;
		else
			return false;
	}

}
