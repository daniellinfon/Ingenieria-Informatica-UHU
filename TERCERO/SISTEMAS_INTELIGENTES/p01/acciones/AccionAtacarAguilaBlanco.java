package si2024.daniellinfonyealu.p01.acciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.game.Observation;
import ontology.Types.ACTIONS;
import si2024.daniellinfonyealu.p01.Mundo18;
import si2024.daniellinfonyealu.p01.IA.Accion;
import si2024.daniellinfonyealu.p01.IA.Mundo;
import si2024.daniellinfonyealu.p01.astar.AStar;
import si2024.daniellinfonyealu.p01.astar.Node;
import tools.Vector2d;

public class AccionAtacarAguilaBlanco implements Accion {

	@Override
	public ACTIONS doAction(Mundo m) {
		// TODO Auto-generated method stub
		Mundo18 m18 = (Mundo18) m;
		Vector2d posAvatar = m18.getPosAvatar();
		Vector2d posAguilaMasCercana = m18.AguilaBlancaMasCercana();
		
		double posXAguila = posAguilaMasCercana.x / m18.getBloque();
		double posYAguila = posAguilaMasCercana.y / m18.getBloque();
		double posXAvatar = posAvatar.x / m18.getBloque();
		double posYAvatar = posAvatar.y / m18.getBloque();
		/*
		ArrayList<ACTIONS> acercar = new ArrayList<>();
		ArrayList<ACTIONS> alejar = new ArrayList<>();

		

		if ((posAvatar.dist(posAguilaMasCercana) / m18.getBloque()) <= 1)
			moverHaciaPosicion(posAvatar, posAguilaMasCercana);

		// DERECHA

		if (!hayObstaculo(m18, 'D')) {
			if (posXAvatar < posXAguila) {
				acercar.add(ACTIONS.ACTION_RIGHT);
			} else {
				alejar.add(ACTIONS.ACTION_RIGHT);
			}
		}

		// IZQUIERDA
		if (!hayObstaculo(m18, 'I')) {
			if (posXAvatar > posXAguila) {
				acercar.add(ACTIONS.ACTION_LEFT);
			} else {
				alejar.add(ACTIONS.ACTION_LEFT);
			}

		}

		// ABAJO
		if (!hayObstaculo(m18, 'A')) {
			if (posYAvatar < posYAguila) {
				acercar.add(ACTIONS.ACTION_DOWN);
			} else{
				alejar.add(ACTIONS.ACTION_DOWN);
			}

		}

		// ARRIBA
		if (!hayObstaculo(m18, 'U')) {
			if (posYAvatar > posYAguila) {
				acercar.add(ACTIONS.ACTION_UP);
			} else {
				alejar.add(ACTIONS.ACTION_UP);
			}
		}

		Random r = new Random();

		if (acercar.isEmpty() && alejar.isEmpty()) {
			return null; // No hay acciones posibles
		} else if (alejar.isEmpty()) {
			return acercar.get(r.nextInt(acercar.size()));
		} else if (acercar.isEmpty()) {
			return alejar.get(r.nextInt(alejar.size()));
		} else {
			int probabilidadAcercarse = 70; // Probabilidad de acercarse
			if (r.nextInt(100) < probabilidadAcercarse) {
				return acercar.get(r.nextInt(acercar.size()));
			} else {
				return alejar.get(r.nextInt(alejar.size()));
			}
		}
		*/
		
		//A*
		
		Node initialNode = new Node((int) posXAvatar, (int)posYAvatar);
        Node finalNode = new Node((int)posXAguila, (int)posYAguila);
        
		AStar aStar = new AStar(m18.getAnchura(),m18.getAltura(), initialNode, finalNode);
		aStar.setBlocks(m18.getBlocksArray());
		List<Node> path = aStar.findPath();
		/*
		for (Node node : path) {
            System.out.println(node);
        }*/
		
		if(path.size()>1)
			return atacar(path.get(1), posXAvatar, posYAvatar);
		else
			return null;
		
		
        
	}
/*
	protected boolean hayObstaculo(Mundo18 m18, char donde) {
		Vector2d posAvatar = m18.getPosAvatar();
		double posX = posAvatar.x / m18.getBloque();
		double posY = posAvatar.y / m18.getBloque();

		switch (donde) {
		case 'D': // Derecha
			posX++;
			break;
		case 'U': // Arriba
			posY--;
			break;
		case 'I': // Izquierda
			posX--;
			break;
		case 'A': // Abajo
			posY++;
			break;
		}

		ArrayList<Observation> contenido = m18.getEntorno()[(int) posX][(int) posY];

		// Verificar si hay un obstáculo en el contenido
		for (Observation observacion : contenido) {
			if (observacion.itype == 0 || observacion.itype == 5) {
				return true;
			}
		}

		// No se encontraron obstáculos en la posición adyacente
		return false;

	}

	protected ACTIONS moverHaciaPosicion(Vector2d posAvatar, Vector2d posicionObjetivo) {
		double diffX = posicionObjetivo.x - posAvatar.x;
		double diffY = posicionObjetivo.y - posAvatar.y;

		// Mover hacia la posición objetivo en la dirección con la menor distancia
		if (Math.abs(diffX) > Math.abs(diffY)) {
			if (diffX > 0)
				return ACTIONS.ACTION_RIGHT;
			else
				return ACTIONS.ACTION_LEFT;
		} else {
			if (diffY > 0)
				return ACTIONS.ACTION_DOWN;
			else
				return ACTIONS.ACTION_UP;
		}

	}*/
	
	private ACTIONS atacar(Node path, double posXAvatar, double posYAvatar){
		
		int diffX = (int) (path.getRow() - posXAvatar);
		int diffY = (int) (path.getCol() - posYAvatar);
		//System.out.println(diffX+" - "+diffY);

		// Mover hacia la posición objetivo en la dirección con la menor distancia
		if (Math.abs(diffX) > Math.abs(diffY)) {
			if (diffX > 0)
				return ACTIONS.ACTION_RIGHT;
			else
				return ACTIONS.ACTION_LEFT;
		} else {
			if (diffY > 0)
				return ACTIONS.ACTION_DOWN;
			else
				return ACTIONS.ACTION_UP;
		}
		
	}

}
