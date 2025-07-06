package si2024.daniellinfonyealu.p04;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import core.game.Observation;
import core.game.StateObservation;
import ontology.Types.ACTIONS;
import si2024.daniellinfonyealu.p04.IA.Mundo;
import si2024.daniellinfonyealu.p04.IA.Operador;
import si2024.daniellinfonyealu.p04.astar.AStar;
import si2024.daniellinfonyealu.p04.astar.Node;
import tools.Vector2d;

public class Mundo45 implements Mundo {

	private ArrayList<Observation>[][] entorno;
	private int bloque;
	private int anchura;
	private int altura;
	private int[][] blocksArray;
	private StateObservation stateObs;
	private ArrayList<Observation>[] posMuros;
	ArrayList<Vector2d> rutaMasCorta;
	private int numManzanasInicial;
	Dimension dimension;

	public Mundo45(StateObservation stateObs) {
		entorno = stateObs.getObservationGrid();
		bloque = stateObs.getBlockSize();
		dimension = stateObs.getWorldDimension();
		anchura = dimension.width / bloque;
		altura = dimension.height / bloque;
		this.stateObs = stateObs;
		posMuros = stateObs.getImmovablePositions();
		blocksArray = new int[posMuros[0].size()][2];
		//rutaMasCorta = rutaMasCorta();
		ArrayList<Observation>[] posComida = stateObs.getMovablePositions();
		 numManzanasInicial = posComida[0].size();
	}

	@Override
	public void analizarMundo(StateObservation stateObs) {
		entorno = stateObs.getObservationGrid();
		this.stateObs = stateObs;
		posMuros = stateObs.getImmovablePositions();

		if (posMuros.length > 1)
			blocksArray = new int[posMuros[0].size() + posMuros[1].size()][2];

		int i = 0;

		for (int j = 0; j < 2; j++) {
			for (Observation muro : posMuros[j]) {
				int posX = (int) muro.position.x / bloque;
				int posY = (int) muro.position.y / bloque;

				// Almacena las coordenadas en blocksArray
				blocksArray[i][0] = posX;
				blocksArray[i][1] = posY;
				i++;
			}
			if (posMuros.length == 1)
				break;
		}

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
					else if (propiedades.itype == 1) // Avatar
						System.out.print("X");
					else if (propiedades.itype == 3) // cola
						System.out.print("#");
					else if (propiedades.itype == 4) // Comida
						System.out.print("P");
					else
						System.out.print(propiedades.itype);
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}

		System.out.println("");

		// ArrayList<Observation>[] posComida = stateObs.getMovablePositions();
		// System.out.println(posComida[0]);

		// if(posMuros.length > 1)
		// System.out.println(posMuros[1]);

	}

	public ACTIONS aplicarOperador(Operador o) {
		return o.getAccion();
	}

	public Vector2d objetivoMasCercano() {
		Vector2d posAvatar = stateObs.getAvatarPosition();
		ArrayList<Observation>[] posComida = stateObs.getMovablePositions();

		// Manejo de casos de posComida vacío
		if (posComida.length == 0 || posComida[0].isEmpty()) {
			return null; // No hay comida, devolver null o un valor predeterminado
		}

		double distMinima = Double.MAX_VALUE;
		Vector2d objetivo = null;

		for (Observation p : posComida[0]) {
			double posXobj = p.position.x / bloque;
			double posYobj = p.position.y / bloque;
			double posXAvatar = posAvatar.x / bloque;
			double posYAvatar = posAvatar.y / bloque;

			// Calcular distancia entre el avatar y la comida
			double distActual = Math.abs(posXAvatar - posXobj);

			// Actualizar objetivo si la distancia es menor
			if (distActual < distMinima) {
				distMinima = distActual;
				objetivo = p.position;
			}
		}

		return objetivo;
	}

	
	public Vector2d objetivoMasCercanoX() {
		Vector2d posAvatar = stateObs.getAvatarPosition();
		ArrayList<Observation>[] posComida = stateObs.getMovablePositions();
		Node initialNode;
		Node finalNode;
		AStar aStar;
		List<Node> path;

		double distMinima = Double.MAX_VALUE;
		Vector2d objetivo = null;

		for (Observation p : posComida[0]) {
			double posXobj = p.position.x / bloque;
			double posYobj = p.position.y / bloque;
			double posXAvatar = posAvatar.x / bloque;
			double posYAvatar = posAvatar.y / bloque;

			initialNode = new Node((int) posXAvatar, (int) posYAvatar);
			finalNode = new Node((int) posXobj, (int) posYobj);

			aStar = new AStar(anchura, altura, initialNode, finalNode);
			aStar.setBlocks(blocksArray);
			path = aStar.findPath();

			double distActual = path.size();

			if (distActual < distMinima) {
				distMinima = distActual;
				objetivo = p.position;
			}
			// System.out.println(objetivo);
		}

		return objetivo;
	}
	
	public Vector2d comparaObjetivos() {
		Vector2d objetivoX = objetivoMasCercano();
		Vector2d objetivo = objetivoMasCercanoX();
		Vector2d posAvatar = stateObs.getAvatarPosition();
		
		double posXobjX = objetivoX.x / bloque;
		double posYobjX = objetivoX.y / bloque;
		double posXobj = objetivo.x / bloque;
		double posYobj = objetivo.y / bloque;
		double posXAvatar = posAvatar.x / bloque;
		double posYAvatar = posAvatar.y / bloque;
		
		if(numManzanasInicial - numManzanas() >= 3)
			return objetivo;
		
		if(Math.abs(posYAvatar - posYobjX) < 20 && Math.abs(posXAvatar - posXobj) > 12) {
			//System.out.println(Math.abs(posXAvatar - posXobj));
			return objetivoX;
		}
			
		else
			return objetivo;
	}
	

	public ArrayList<Vector2d> rutaMasCorta() {
		Vector2d posAvatar = getPosAvatar();
		ArrayList<Observation>[] posComida = stateObs.getMovablePositions();

		ArrayList<ArrayList<Integer>> todasRutas = new ArrayList<>();
		ArrayList<ArrayList<Vector2d>> todasRutas2d = new ArrayList<>();

		for (Observation comida : posComida[0]) {
			if (comida.position.equals(posAvatar))
				posComida[0].remove(comida);
		}

		calcularRutas(posAvatar, posComida[0], new ArrayList<Integer>(), new ArrayList<Vector2d>(), todasRutas,	todasRutas2d);

		// Seleccionar la ruta más corta en términos de la cantidad de nodos recorridos
		ArrayList<Vector2d> rutaMasCorta2d = null;
		int nodosMinimos = Integer.MAX_VALUE;
		int nodosTotal = 0;
		int i = 0;

		for (ArrayList<Integer> ruta : todasRutas) {
			for (int nodos : ruta) {
				nodosTotal += nodos;
			}

			if (nodosTotal < nodosMinimos) {
				nodosMinimos = nodosTotal;
				rutaMasCorta2d = todasRutas2d.get(i);
			}
			i++;
		}

		// System.out.println(nodosMinimos);
		return rutaMasCorta2d;
	}

	// Método recursivo para calcular todas las rutas posibles
	private void calcularRutas(Vector2d posActual, ArrayList<Observation> posComida, ArrayList<Integer> rutaActual,
			ArrayList<Vector2d> rutaActual2d, ArrayList<ArrayList<Integer>> todasRutas,
			ArrayList<ArrayList<Vector2d>> todasRutas2d) {
// Realiza una copia de la lista de posiciones de comida
		ArrayList<Observation> posComidaCopia = new ArrayList<>(posComida);

		// Si no hay más comidas por recoger, agrega la ruta actual a la lista de todas las rutas
		if (posComidaCopia.isEmpty()) {
			todasRutas.add(new ArrayList<>(rutaActual));
			todasRutas2d.add(new ArrayList<>(rutaActual2d));
		}

		// Itera sobre todas las comidas disponibles
		for (Observation comida : posComidaCopia) {
			ArrayList<Observation> posComidaNueva = new ArrayList<>(posComidaCopia);
			posComidaNueva.remove(comida);

			// Calcula la ruta desde la posición actual hasta la comida actual
			double posXobj = comida.position.x / bloque;
			double posYobj = comida.position.y / bloque;
			double posXAct = posActual.x / bloque;
			double posYAct = posActual.y / bloque;

			Node initialNode = new Node((int) posXAct, (int) posYAct);
			Node finalNode = new Node((int) posXobj, (int) posYobj);

			AStar aStar = new AStar(anchura, altura, initialNode, finalNode);
			aStar.setBlocks(blocksArray);
			List<Node> path = aStar.findPath();

			int distActual = path.size();
			ArrayList<Integer> nuevaRuta = new ArrayList<>(rutaActual);
			ArrayList<Vector2d> nuevaRuta2d = new ArrayList<>(rutaActual2d);
			nuevaRuta.add(distActual);
			nuevaRuta2d.add(comida.position);

// Llama recursivamente al método con la nueva posición actual y la lista de
// posiciones de comida actualizada
			calcularRutas(comida.position, posComidaNueva, nuevaRuta, nuevaRuta2d, todasRutas, todasRutas2d);
		}
	}

	// Método para verificar si no quedan más comidas por recoger
	private boolean posComidaVacio(ArrayList<Observation>[] posComida) {
		if (posComida[0].size() > 0) {
			return false;
		}
		return true;
	}

	public int numManzanas() {
		ArrayList<Observation>[] posComida = stateObs.getMovablePositions();
		return posComida[0].size();
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

	public int[][] getBlocksArray() {
		return blocksArray;
	}

	public int getNumManzanasInicial() {
		return numManzanasInicial;
	}

	public void setNumManzanasInicial(int numManzanasInicial) {
		this.numManzanasInicial = numManzanasInicial;
	}

	public ArrayList<Vector2d> getRutaMasCorta() {
		return rutaMasCorta;
	}

	public void setRutaMasCorta(ArrayList<Vector2d> rutaMasCorta) {
		this.rutaMasCorta = rutaMasCorta;
	}

}
