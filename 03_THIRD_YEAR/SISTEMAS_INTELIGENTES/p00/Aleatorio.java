package si2024.daniellinfonyealu.p00;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import core.game.Observation;
import core.game.StateObservation;
import ontology.Types.ACTIONS;
import si2024.daniellinfonyealu.p01.IA.Mundo;
import tools.Vector2d;

public class Aleatorio implements Cerebro {

	private ArrayList<Observation>[][] entorno;
	private int bloque;
	private int anchura;
	private int altura;
	private StateObservation stateObs;
	Dimension dimension;

	@Override
	public void analizarMundo(StateObservation stateObs) {
		entorno = stateObs.getObservationGrid();
		bloque = stateObs.getBlockSize();
		dimension = stateObs.getWorldDimension();
		anchura = dimension.width / bloque;
		altura = dimension.height / bloque;
		this.stateObs = stateObs;

		for (int y = 0; y < altura; y++) {
			for (int x = 0; x < anchura; x++) {
				ArrayList<Observation> contenido = entorno[x][y];
				if (x == 0) {
					System.out.print(" ");
				}
				Observation propiedades;
				if (contenido.size() > 0) {
					propiedades = contenido.get(0);
					/*if (propiedades.itype == 0) // Borde
						System.out.print("#");
					else if (propiedades.itype == 1) // Avatar
						System.out.print("X");*/
				    if (propiedades.itype == 6) // Aguila blanco
					{
						//System.out.print("B");
					} else if (propiedades.itype == 5) // Aguila Negro
					{
						//System.out.print("N");
					}}/*else if (propiedades.itype == 3) // Gusano
						System.out.print("G");
					else
						System.out.print(propiedades.category);
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
		System.out.println("");*/
}}}

	@Override
	public ACTIONS piensa() {
		// TODO Auto-generated method stub
		
		LinkedList<ACTIONS> acciones = new LinkedList<ACTIONS>();
		acciones.add(ACTIONS.ACTION_NIL);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_DOWN);
		
		Random r = new  Random();
		int accion = r.nextInt(5);
		
		return acciones.get(accion);
	}

}
