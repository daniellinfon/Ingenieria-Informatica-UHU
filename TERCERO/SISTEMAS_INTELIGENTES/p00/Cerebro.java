package si2024.daniellinfonyealu.p00;

import core.game.StateObservation;
import ontology.Types.ACTIONS;

public interface Cerebro {
	public ACTIONS piensa();
		
	public void analizarMundo(StateObservation stateObs);
	
}
