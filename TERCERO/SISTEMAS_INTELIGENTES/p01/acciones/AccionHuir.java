package si2024.daniellinfonyealu.p01.acciones;

import java.util.ArrayList;
import java.util.Random;
import core.game.Observation;
import ontology.Types.ACTIONS;
import si2024.daniellinfonyealu.p01.Mundo18;
import si2024.daniellinfonyealu.p01.IA.Accion;
import si2024.daniellinfonyealu.p01.IA.Mundo;
import tools.Vector2d;

public class AccionHuir implements Accion {

	@Override
	public ACTIONS doAction(Mundo m) {
		Mundo18 m18 = (Mundo18) m;
		Vector2d posAvatar = m18.getPosAvatar();

		ArrayList<ACTIONS> alejarse = new ArrayList<>();
		ArrayList<ACTIONS> acercarse = new ArrayList<>();
		/*
		 * acciones.add(ACTIONS.ACTION_LEFT); acciones.add(ACTIONS.ACTION_RIGHT);
		 * acciones.add(ACTIONS.ACTION_UP); acciones.add(ACTIONS.ACTION_DOWN);
		 */

		double posXAvatar = posAvatar.x / m18.getBloque();
		double posYAvatar = posAvatar.y / m18.getBloque();
		double posXAguila = m18.AguilaNegraMasCercana().x / m18.getBloque();
		double posYAguila = m18.AguilaNegraMasCercana().y / m18.getBloque();

		// DERECHA

		if (!hayObstaculo(m18, 'D')) {
			if (posXAvatar >= posXAguila) {
				alejarse.add(ACTIONS.ACTION_RIGHT);
			}else {
				acercarse.add(ACTIONS.ACTION_RIGHT);
			}
		}

		// IZQUIERDA
		if (!hayObstaculo(m18, 'I')) {
			if (posXAvatar <= posXAguila) {
				alejarse.add(ACTIONS.ACTION_LEFT);
			} else {
				acercarse.add(ACTIONS.ACTION_LEFT);
			}

		}

		// ABAJO
		if (!hayObstaculo(m18, 'A')) {
			if (posYAvatar >= posYAguila) {
				alejarse.add(ACTIONS.ACTION_DOWN);
			} else {
				acercarse.add(ACTIONS.ACTION_DOWN);
			}

		}

		// ARRIBA
		if (!hayObstaculo(m18, 'U')) {
			if (posYAvatar <= posYAguila) {
				alejarse.add(ACTIONS.ACTION_UP);
			} else {
				acercarse.add(ACTIONS.ACTION_UP);
			}
		}

		Random r = new Random();
        if (!alejarse.isEmpty()) {
            return alejarse.get(r.nextInt(alejarse.size()));
        }else if(!acercarse.isEmpty()){
            return acercarse.get(r.nextInt(acercarse.size()));
        }else
        	return null;
	}

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
		
		int posXInt = (int) posX;
	    int posYInt = (int) posY;
	    
	 // Contadores de obstáculos en las cuatro posiciones posibles
	    int obstaculosArriba = contarObstaculos(posXInt, posYInt - 1, m18);
	    int obstaculosAbajo = contarObstaculos(posXInt, posYInt + 1, m18);
	    int obstaculosIzquierda = contarObstaculos(posXInt - 1, posYInt, m18);
	    int obstaculosDerecha = contarObstaculos(posXInt + 1, posYInt, m18);
	    
	 // Verificar si hay una esquina
	    if ((obstaculosArriba + obstaculosAbajo + obstaculosIzquierda + obstaculosDerecha >= 2)) {
	        return true;
	    }
		
		// No se encontraron obstáculos en la posición adyacente
		return false;

	}
	
	private int contarObstaculos(int x, int y, Mundo18 m18) {
		if (x < 0 || y < 0 || x >= m18.getAnchura() || y >= m18.getAltura()) {

	        return 0;
	    }
	    ArrayList<Observation> contenido = m18.getEntorno()[x][y];
	    int contador = 0;

	    for (Observation observacion : contenido) {
	        if (observacion.itype == 0 || observacion.itype == 5) {
	            contador++;
	        }
	    }

	    return contador;
	}
}
