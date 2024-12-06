/**
 * 
 */
package si2024.daniellinfonyealu.p01;

import java.util.List;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2024.daniellinfonyealu.p01.IA.Reglas;
import tools.ElapsedCpuTimer;

/**
 * 
 */
public class Practica_01_Agente extends AbstractPlayer {

	private Mundo18 m;
	private Motor18 motor;
	
	public Practica_01_Agente(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {		
			m = new Mundo18(stateObs);
			motor = new Motor18(m);
	}

	@Override
	public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
		//PERCIBIR
		m.analizarMundo(stateObs);
		
		//PENSAR
		Reglas r = motor.piensa(m);
		
		//ACTUAR
		return motor.actuar(m,r);
						
	}

}
