package si2024.daniellinfonyealu.p03.IA;

import ontology.Types.ACTIONS;
import si2024.daniellinfonyealu.p03.NodoDecision.EstoyInfectado;
import si2024.daniellinfonyealu.p03.NodoDecision.HayEnfermera;

public class ArbolDecision implements Motor{
	private NodoArbol raiz;
	
	@Override
	public ACTIONS piensa(Mundo m) {
			this.raiz = new HayEnfermera();
			Accion a =  (Accion) raiz.decide(m);
			return a.doAction(m);
	}


	
}
