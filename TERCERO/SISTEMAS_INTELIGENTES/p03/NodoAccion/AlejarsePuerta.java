package si2024.daniellinfonyealu.p03.NodoAccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.game.Observation;
import ontology.Types.ACTIONS;
import si2024.daniellinfonyealu.p01.astar.AStar;
import si2024.daniellinfonyealu.p01.astar.Node;
import si2024.daniellinfonyealu.p03.Mundo53;
import si2024.daniellinfonyealu.p03.IA.Accion;
import si2024.daniellinfonyealu.p03.IA.Mundo;
import si2024.daniellinfonyealu.p03.IA.NodoArbol;
import si2024.daniellinfonyealu.p03b.Mundo68;
import tools.Vector2d;

public class AlejarsePuerta extends Accion {

	@Override
	public ACTIONS doAction(Mundo m) {
		Mundo53 m53 = (Mundo53) m;
		Vector2d posAvatar = m53.getPosAvatar();
		Vector2d puerta = m53.puertaMasCerca();
		Vector2d inf = m53.infectadoMasCercano().get(0);
		
		if(puerta.dist(posAvatar) > inf.dist(posAvatar)) {
			puerta = inf;
		}
		
		if(posAvatar.dist(puerta)/m53.getBloque() > 2) {
			return null;
		}

		double posXobj = puerta.x / m53.getBloque();
		double posYobj = puerta.y / m53.getBloque();
		double posXAvatar = posAvatar.x / m53.getBloque();
		double posYAvatar = posAvatar.y / m53.getBloque();

		ArrayList<ACTIONS> alejarse = new ArrayList<>();
		ArrayList<ACTIONS> acercarse = new ArrayList<>();
		// DERECHA

		if (!hayObstaculo(m53, 'D')) {
			if (posXAvatar >= posXobj) {
				alejarse.add(ACTIONS.ACTION_RIGHT);
			} else {
				acercarse.add(ACTIONS.ACTION_RIGHT);
			}
		}

		// IZQUIERDA
		if (!hayObstaculo(m53, 'I')) {
			if (posXAvatar <= posXobj) {
				alejarse.add(ACTIONS.ACTION_LEFT);
			} else {
				acercarse.add(ACTIONS.ACTION_LEFT);
			}

		}

		// ABAJO
		if (!hayObstaculo(m53, 'A')) {
			if (posYAvatar >= posYobj) {
				alejarse.add(ACTIONS.ACTION_DOWN);
			} else {
				acercarse.add(ACTIONS.ACTION_DOWN);
			}

		}

		// ARRIBA
		if (!hayObstaculo(m53, 'U')) {
			if (posYAvatar <= posYobj) {
				alejarse.add(ACTIONS.ACTION_UP);
			} else {
				acercarse.add(ACTIONS.ACTION_UP);
			}
		}

		Random r = new Random();
		if (!alejarse.isEmpty()) {
			return alejarse.get(r.nextInt(alejarse.size()));
		} else if (!acercarse.isEmpty()) {
			return acercarse.get(r.nextInt(acercarse.size()));
		} else
			return null;

	}

	protected boolean hayObstaculo(Mundo53 m53, char donde) {
		Vector2d posAvatar = m53.getPosAvatar();
		double posX = posAvatar.x / m53.getBloque();
		double posY = posAvatar.y / m53.getBloque();

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

		ArrayList<Observation> contenido = m53.getEntorno()[(int) posX][(int) posY];

		// Verificar si hay un obstáculo en el contenido
		for (Observation p : contenido) {
			if (p.itype == 0 || p.itype == 4) {
				return true;
			}
		}

		return false;
	}

	private boolean hayEnfermera(Mundo53 m53, char donde) {
		Vector2d posAvatar = m53.getPosAvatar();
		double posX = posAvatar.x / m53.getBloque();
		double posY = posAvatar.y / m53.getBloque();

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


		ArrayList<Observation> contenido = m53.getEntorno()[(int) posX][(int) posY];

		// Verificar si hay una enfermera
		for (Observation observacion : contenido) {
			if (observacion.itype == 12) {
				return true;
			}
		}

		return false;
	}

}
