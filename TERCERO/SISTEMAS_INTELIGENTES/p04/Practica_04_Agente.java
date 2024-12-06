/**
 * 
 */
package si2024.daniellinfonyealu.p04;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;

/**
 * 
 */
public class Practica_04_Agente extends AbstractPlayer {

	Mundo45 m;
	BEspacioEstados cerebro;

	public Practica_04_Agente(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {

		m = new Mundo45(stateObs);
		cerebro = new BEspacioEstados();
	}

	@Override
	public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
		// PERCIBIR
		m.analizarMundo(stateObs);
		// PIENSA
		ACTIONS a = cerebro.piensa(m);
		//System.out.println(a);
		return a;

	}

}
