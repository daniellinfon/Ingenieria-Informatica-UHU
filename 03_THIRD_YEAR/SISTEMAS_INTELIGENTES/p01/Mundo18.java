package si2024.daniellinfonyealu.p01;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import core.game.Observation;
import core.game.StateObservation;
import si2024.daniellinfonyealu.p01.IA.Mundo;
import tools.Vector2d;

public class Mundo18 implements Mundo {

	private ArrayList<Observation>[][] entorno;
	private int bloque;
	private int anchura;
	private int altura;
	private int numAguilasBlancas;
	private int numAguilasNegras;
	private StateObservation stateObs;
	private int[][] blocksArray;
	Dimension dimension;

	public Mundo18(StateObservation stateObs) {
		entorno = stateObs.getObservationGrid();
		bloque = stateObs.getBlockSize();
		dimension = stateObs.getWorldDimension();
		numAguilasBlancas = 0;
		numAguilasNegras = 0;
		anchura = dimension.width / bloque;
		altura = dimension.height / bloque;
		this.stateObs = stateObs;
		
		ArrayList<Observation>[] posMuros = stateObs.getImmovablePositions();
		ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();
		blocksArray = new int[posMuros[0].size()+posNPC[0].size()][2];
	}

	@Override
	public void analizarMundo(StateObservation stateObs) {
		entorno = stateObs.getObservationGrid();
		bloque = stateObs.getBlockSize();
		dimension = stateObs.getWorldDimension();
		anchura = dimension.width / bloque;
		altura = dimension.height / bloque;
		
		this.stateObs = stateObs;
		numAguilasBlancas = 0;
		numAguilasNegras = 0;

		for (int y = 0; y < altura; y++) {
			for (int x = 0; x < anchura; x++) {
				ArrayList<Observation> contenido = entorno[x][y];
				if (x == 0) {
					System.out.print(" ");
				}
				Observation propiedades;
				if (contenido.size() > 0) {
					propiedades = contenido.get(0);
					/*if (propiedades.itype == 0) {// Borde
						System.out.print("#");
					else if (propiedades.itype == 1) // Avatar
						System.out.print("X");*/
				    if (propiedades.itype == 6) // Aguila blanco
					{
						//System.out.print("B");
						numAguilasBlancas++;
					} else if (propiedades.itype == 5) // Aguila Negro
					{
						//System.out.print("N");
						numAguilasNegras++;
						Vector2d aguilaNegra = new Vector2d(x, y);
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
				
				int i = 0;
				int j=0;
				ArrayList<Observation>[] posMuros = stateObs.getImmovablePositions();
				for (Observation muro : posMuros[0]) {
					int posX = (int) muro.position.x/bloque;
					int posY = (int) muro.position.y/bloque;
					
					// Almacena las coordenadas en blocksArray
				    blocksArray[i][0] = posX;
				    blocksArray[i][1] = posY;
				    i++;   
				    j=i;
				}
				
				if(hayAguilasNegras()) {
					ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();
					for (Observation p : posNPC[0]) {
						int posX = (int) p.position.x/bloque;
						int posY = (int) p.position.y/bloque;
						
						blocksArray[j][0] = posX;
						blocksArray[j][1] = posY;
						j++;    
					}
				}
				
}}}

	public int getBloque() {
		return bloque;
	}

	public int getAnchura() {
		return anchura;
	}

	public int getAltura() {
		return altura;
	}

	public StateObservation getStateObs() {
		return stateObs;
	}

	public int getNumAguilasBlancas() {
		return numAguilasBlancas;
	}

	public int getNumAguilasNegras() {
		return numAguilasNegras;
	}

	public int[][] getBlocksArray() {
		return blocksArray;
	}

	public void setBlocksArray(int[][] blocksArray) {
		this.blocksArray = blocksArray;
	}

	public Vector2d AguilaBlancaMasCercana() {

		Vector2d posAvatar = stateObs.getAvatarPosition();
		ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();
		int x = 0;

		double distMinima = Double.MAX_VALUE;
		Vector2d posAguilaMasCerca = null;

		if (posNPC.length > 1)
			x = 1;
		else
			x = 0;

		for (Observation p : posNPC[x]) {
			if (p.itype == 6) { // Comprobar que sea águila blanca
				double distActual = p.position.dist(posAvatar);
				if (distActual < distMinima) {
					distMinima = distActual;
					posAguilaMasCerca = p.position;
				}
			}
		}

		return posAguilaMasCerca;
	}
	
	public Vector2d AguilaNegraMasCercana() {

		Vector2d posAvatar = stateObs.getAvatarPosition();
		ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();
		int x=0;

		double distMinima = Double.MAX_VALUE;
		Vector2d posAguilaMasCerca = null;

		for (Observation p : posNPC[x]) {
			if (p.itype == 5) { // Comprobar que sea águila negra
				double distActual = p.position.dist(posAvatar);
				if (distActual < distMinima) {
					distMinima = distActual;
					posAguilaMasCerca = p.position;
				}
			}
		}

		return posAguilaMasCerca;
	}

	public Vector2d getPosAvatar() {
		return stateObs.getAvatarPosition();
	}

	public ArrayList<Observation>[][] getEntorno() {
		return entorno;
	}

	public boolean hayAguilasNegras() {
		ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();
		if(posNPC.length > 1)
			return true;
		else
			return false;
	}
}
