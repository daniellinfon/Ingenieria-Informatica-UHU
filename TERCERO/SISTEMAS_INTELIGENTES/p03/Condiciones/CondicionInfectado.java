package si2024.daniellinfonyealu.p03.Condiciones;

import si2024.daniellinfonyealu.p03.Mundo53;
import si2024.daniellinfonyealu.p03.IA.Condicion;
import si2024.daniellinfonyealu.p03.IA.Mundo;

public class CondicionInfectado implements Condicion{

	@Override
	public boolean seCumple(Mundo m) {
		// TODO Auto-generated method stub
		Mundo53 m53 = (Mundo53) m;
		if(m53.infectado())
			return true;
		else
			return false;
		
	}

}
