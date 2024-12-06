package si2024.daniellinfonyealu.p03;

import java.awt.Dimension;
import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import si2024.daniellinfonyealu.p03.IA.Mundo;
import tools.Vector2d;

public class Mundo53 implements Mundo {

	private ArrayList<Observation>[][] entorno;
	private int bloque;
	private int anchura;
	private int altura;
	private int[][] blocksArray;
	private int[][] blocksArrayInf;
	private StateObservation stateObs;
	Dimension dimension;

	public Mundo53(StateObservation stateObs) {
		entorno = stateObs.getObservationGrid();
		bloque = stateObs.getBlockSize();
		dimension = stateObs.getWorldDimension();
		anchura = dimension.width / bloque;
		altura = dimension.height / bloque;
		this.stateObs = stateObs;
		ArrayList<Observation>[] posMuros = stateObs.getImmovablePositions();
		blocksArray = new int[posMuros[0].size()][2];
		blocksArrayInf = new int[posMuros[0].size() + posMuros[1].size()][2];
	}

	@Override
	public void analizarMundo(StateObservation stateObs) {
		entorno = stateObs.getObservationGrid();
		bloque = stateObs.getBlockSize();
		dimension = stateObs.getWorldDimension();
		anchura = dimension.width / bloque;
		altura = dimension.height / bloque;
		this.stateObs = stateObs;

		int i = 0;
		ArrayList<Observation>[] posMuros = stateObs.getImmovablePositions();
		ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();
		
		if (posNPC.length > 1) {
			if (posNPC[1].get(0).itype == 11) {
				blocksArrayInf = new int[posMuros[0].size() + posMuros[1].size() + posNPC[1].size()][2];
				for (Observation infectados : posNPC[1]) {
					int posX = (int) infectados.position.x / bloque;
					int posY = (int) infectados.position.y / bloque;

					// Almacena las coordenadas en blocksArray
					blocksArrayInf[i][0] = posX;
					blocksArrayInf[i][1] = posY;
					i++;
				}
			}
		}

		int j = 0;
		for (Observation muro : posMuros[0]) {
			int posX = (int) muro.position.x / bloque;
			int posY = (int) muro.position.y / bloque;

			// Almacena las coordenadas en blocksArray
			blocksArray[j][0] = posX;
			blocksArray[j][1] = posY;
			blocksArrayInf[i][0] = posX;
			blocksArrayInf[i][1] = posY;
			i++;
			j++;
		}

		for (Observation muro : posMuros[1]) {
			int posX = (int) muro.position.x / bloque;
			int posY = (int) muro.position.y / bloque;

			// Almacena las coordenadas en blocksArray
			blocksArrayInf[i][0] = posX;
			blocksArrayInf[i][1] = posY;
			i++;
		}
		
		// ArrayList<Observation>[] pos = stateObs.getPortalsPositions();
		// System.out.println(pos[0]);
		// pintar();
	}

	private void pintar() {
		for (int y = 0; y < altura; y++) {
			for (int x = 0; x < anchura; x++) {
				ArrayList<Observation> contenido = entorno[x][y];
				if (x == 0) {
					System.out.print(" ");
				}
				Observation propiedades;
				if (contenido.size() > 0) {
					propiedades = contenido.get(0);
					if (propiedades.itype == 0) // Borde
						System.out.print("#");
					else if (propiedades.itype == 7) // Avatar
						System.out.print("X");
					else if (propiedades.itype == 12) // Enfermera
					{
						System.out.print("E");
					} else if (propiedades.itype == 5) // Mocos
					{
						System.out.print("M");
					} else if (propiedades.itype == 4) // Puerta
						System.out.print("P");
					else if (propiedades.itype == 10) // No Infectado
					{
						System.out.print("N");
					} else if (propiedades.itype == 11) // Infectado
					{
						System.out.print("I");
					} else
						System.out.print(propiedades.itype);
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}

		System.out.println("");

		ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();
		System.out.println("Tipos de NPC: " + posNPC.length);

		for (int i = 0; i < posNPC.length; i++) {
			System.out.println(posNPC[i]);
		}

		// Puertas enfermeras

		ArrayList<Observation>[] pos = stateObs.getPortalsPositions();

		for (int i = 0; i < pos.length; i++) {
			System.out.println(pos[i]);
		}

		pos = stateObs.getImmovablePositions();

		System.out.println(pos[1]);

	}

	public Vector2d getAvatarOrientation() {
		return stateObs.getAvatarOrientation();
	}

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

	public Vector2d getPosAvatar() {
		return stateObs.getAvatarPosition();
	}

	public ArrayList<Observation>[][] getEntorno() {
		return entorno;
	}

	public boolean infectado() {
		// Infectado = 8
		if (stateObs.getAvatarType() == 7)
			return false;
		else
			return true;
	}

	public ArrayList<Vector2d> infectadoMasCercano() {
		Vector2d posAvatar = stateObs.getAvatarPosition();
		ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();
		ArrayList<Observation>[] posMocos = stateObs.getImmovablePositions();

		/*
		 * double distMinima = Double.MAX_VALUE; Vector2d infeccionMasCercana = null;
		 * 
		 * 
		 * for (Observation p : posMocos[1]) { if (p.itype == 5) { // Comprobar que sea
		 * moco; double distActual = p.position.dist(posAvatar); if (distActual <
		 * distMinima) { distMinima = distActual; infeccionMasCercana = p.position; } }
		 * }
		 */

		ArrayList<Vector2d> arrayObjetivos = new ArrayList<>();
		ArrayList<Double> objetivos = new ArrayList<>();
		for (Observation p : posMocos[1]) {
			objetivos.add(p.position.dist(posAvatar));
			arrayObjetivos.add(p.position);
		}

		if (posNPC.length == 3) {
			// return infeccionMasCercana; //No hay infectados
			for (Observation p : posNPC[posNPC.length - 2]) {
				objetivos.add(p.position.dist(posAvatar));
				arrayObjetivos.add(p.position);
			}
		}else if(posNPC.length == 2) {
			for (Observation p : posNPC[posNPC.length - 1]) {
				if(p.itype != 11)
					break;
				
				objetivos.add(p.position.dist(posAvatar));
				arrayObjetivos.add(p.position);
			}
		}


		QuickSort.quicksort(objetivos, arrayObjetivos, 0, objetivos.size() - 1);
		return arrayObjetivos;
		// return infeccionMasCercana;
	}

	public Vector2d noInfectadoMasCercano() {
		Vector2d posAvatar = stateObs.getAvatarPosition();
		ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();

		double distMinima = Double.MAX_VALUE;
		Vector2d noInfectadoMasCercano = null;

		for (Observation p : posNPC[0]) {
			if (p.itype == 10) { // Comprobar que sea no infectado;
				double distActual = p.position.dist(posAvatar);
				if (distActual < distMinima) {
					distMinima = distActual;
					noInfectadoMasCercano = p.position;
				}
			}
		}

		return noInfectadoMasCercano;
	}

	public Vector2d enfermeraMasCercana() {
		Vector2d posAvatar = stateObs.getAvatarPosition();
		ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();

		double distMinima = Double.MAX_VALUE;
		Vector2d enfermeraMasCercana = null;

		for (Observation p : posNPC[posNPC.length - 1]) {
			if (p.itype == 12) { // Comprobar que sea enfermera;
				double distActual = p.position.dist(posAvatar);
				if (distActual < distMinima) {
					distMinima = distActual;
					enfermeraMasCercana = p.position;
				}
			}
		}

		return enfermeraMasCercana;
	}

	public boolean hayEnfermeras() {
		ArrayList<Observation>[] posNPC = stateObs.getNPCPositions();
		for (Observation p : posNPC[posNPC.length - 1]) {
			if (p.itype == 12)
				return true;
		}
		return false;
	}

	public boolean hayPuertas() {
		ArrayList<Observation>[] pos = stateObs.getPortalsPositions();
		if (pos != null) {
			for (Observation p : pos[0]) {
				if (p.itype == 4)
					return true;
			}
		}
		return false;
	}

	public Vector2d puertaMasCerca() {
		Vector2d posAvatar = stateObs.getAvatarPosition();
		ArrayList<Observation>[] posPuertas = stateObs.getPortalsPositions();

		double distMinima = Double.MAX_VALUE;
		Vector2d puerta = null;

		for (Observation p : posPuertas[0]) {
			double distActual = p.position.dist(posAvatar);
			if (distActual < distMinima) {
				distMinima = distActual;
				puerta = p.position;
			}
		}

		return puerta;
	}

	public int[][] getBlocksArray() {
		return blocksArray;
	}

	public int[][] getBlocksArrayInf() {
		return blocksArrayInf;
	}

	public void setBlocksArrayInf(int[][] blocksArrayInf) {
		this.blocksArrayInf = blocksArrayInf;
	}

}
