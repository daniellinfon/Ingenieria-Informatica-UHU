/**
 * 
 */
package si2024.daniellinfonyealu.p00;

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
import si2024.daniellinfonyealu.p01.IA.Reglas;
import tools.ElapsedCpuTimer;

/**
 * 
 */
public class Practica_00_Agente extends AbstractPlayer {

	private List <ACTIONS> acciones;
	private ACTIONS accion;
	private Cerebro cerebro;
	
	public Practica_00_Agente(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
			
			
			
			cerebro = new Aleatorio();
		/*
			acciones = new LinkedList<ACTIONS>();
			acciones.add(ACTIONS.ACTION_NIL);
			acciones.add(ACTIONS.ACTION_LEFT);
			acciones.add(ACTIONS.ACTION_RIGHT);
			acciones.add(ACTIONS.ACTION_UP);
			acciones.add(ACTIONS.ACTION_DOWN);
		*/
	}

	@Override
	public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
		//PERCIBIR
		cerebro.analizarMundo(stateObs);
		
		//PENSAR
		return cerebro.piensa();
		
		
		/*Random r = new  Random();
		int accion = r.nextInt(5);
		
		return null;
		return acciones.get(accion);
		*/
		
						
	}

}
