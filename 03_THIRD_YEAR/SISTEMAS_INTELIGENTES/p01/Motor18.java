package si2024.daniellinfonyealu.p01;

import java.util.ArrayList;
import java.util.List;

import ontology.Types.ACTIONS;
import si2024.daniellinfonyealu.p01.IA.Motor;
import si2024.daniellinfonyealu.p01.IA.Mundo;
import si2024.daniellinfonyealu.p01.IA.Reglas;
import si2024.daniellinfonyealu.p01.reglas.ReglaAtacarAguilaBlanca;
import si2024.daniellinfonyealu.p01.reglas.ReglaHuir;

public class Motor18 implements Motor{
	private List<Reglas> reglas;

	public Motor18(Mundo18 m) {
		
		ReglaAtacarAguilaBlanca AtacarAguilasBlancas = new ReglaAtacarAguilaBlanca();
		ReglaHuir Huir = new ReglaHuir();
		
		reglas = new ArrayList<Reglas>();
		
		reglas.add(AtacarAguilasBlancas);
		//reglas.add(Huir);
	}

	@Override
	public Reglas piensa(Mundo m) {
		for (Reglas r : reglas) {
		    if (r.seCumple(m))
		        return r;
		}
		return null;
	}

	@Override
	public ACTIONS actuar(Mundo m, Reglas r) {
		// TODO Auto-generated method stub
		return r.getAccion().doAction(m);
	}


}
