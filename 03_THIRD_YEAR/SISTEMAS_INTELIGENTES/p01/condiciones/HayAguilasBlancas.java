package si2024.daniellinfonyealu.p01.condiciones;

import si2024.daniellinfonyealu.p01.Mundo18;
import si2024.daniellinfonyealu.p01.IA.Condicion;
import si2024.daniellinfonyealu.p01.IA.Mundo;

public class HayAguilasBlancas implements Condicion{

	@Override
	public boolean seCumple(Mundo m) {
		// TODO Auto-generated method stub
		Mundo18 m18 = (Mundo18) m;
		int hay = m18.getNumAguilasBlancas();
		return hay > 0;
	}
}
