package si2024.daniellinfonyealu.p01.IA;

import ontology.Types.ACTIONS;

public interface Motor {
	public Reglas piensa(Mundo m);
	
	public ACTIONS actuar(Mundo m, Reglas r);
}
