/**
 * 
 */
package si2024.daniellinfonyealu.p03;

import java.security.KeyStore.ProtectionParameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import si2024.daniellinfonyealu.p03.IA.ArbolDecision;
import tools.ElapsedCpuTimer;

/**
 * 
 */
public class Practica_03_Agente extends AbstractPlayer {

	private Mundo53 m;
	private ArbolDecision cerebro;
	
	public Practica_03_Agente(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
				
			m = new Mundo53(stateObs);
			cerebro = new ArbolDecision();
	}

	@Override
	public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
		//PERCIBIR
		m.analizarMundo(stateObs);
		//PIENSA
		return cerebro.piensa(m);
		
	}

}
