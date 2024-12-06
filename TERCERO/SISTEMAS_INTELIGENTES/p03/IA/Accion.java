package si2024.daniellinfonyealu.p03.IA;

import ontology.Types.ACTIONS;

public abstract class Accion implements NodoArbol{
	
	public abstract ACTIONS doAction(Mundo m);

	@Override
	public NodoArbol decide(Mundo m) {
		return this;
	}
}
