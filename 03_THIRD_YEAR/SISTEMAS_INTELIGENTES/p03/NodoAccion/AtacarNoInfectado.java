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
import tools.Vector2d;

public class AtacarNoInfectado extends Accion {

	@Override
	public ACTIONS doAction(Mundo m) {
		Mundo53 m53 = (Mundo53) m;
		Vector2d posAvatar = m53.getPosAvatar();
		Vector2d noInfectado = m53.noInfectadoMasCercano();
		Vector2d orientacionAvatar = m53.getAvatarOrientation();

		double posXinf = noInfectado.x / m53.getBloque();
		double posYinf = noInfectado.y / m53.getBloque();
		double posXAvatar = posAvatar.x / m53.getBloque();
		double posYAvatar = posAvatar.y / m53.getBloque();

		Node initialNode = new Node((int) posXAvatar, (int) posYAvatar);
		Node finalNode = new Node((int) posXinf, (int) posYinf);

		AStar aStar = new AStar(m53.getAnchura(), m53.getAltura(), initialNode, finalNode);
		aStar.setBlocks(m53.getBlocksArray());
		List<Node> path = aStar.findPath();
		//System.out.println("dd");

		if (path.size() > 1) {
			int diffX = (int) (path.get(1).getRow() - posXAvatar);
			int diffY = (int) (path.get(1).getCol() - posYAvatar);
			// System.out.println(diffX+" - "+diffY);

			// Mover hacia la posición objetivo en la dirección con la menor distancia
						if (Math.abs(diffX) > Math.abs(diffY)) {
							if (diffX > 0) {
								if (hayEnfermera(m53, 'D') && orientacionAvatar.x == 1.0)
									return ACTIONS.ACTION_USE;
								else
									return ACTIONS.ACTION_RIGHT;
							} else {
								if (hayEnfermera(m53, 'I') && orientacionAvatar.x == -1.0)
									return ACTIONS.ACTION_USE;
								else
									return ACTIONS.ACTION_LEFT;
							}
						} else {
							if (diffY > 0) {
								if (hayEnfermera(m53, 'A') && orientacionAvatar.y == 1.0)
									return ACTIONS.ACTION_USE;
								else
									return ACTIONS.ACTION_DOWN;
							} else {
								if (hayEnfermera(m53, 'U') && orientacionAvatar.y == 1.0)
									return ACTIONS.ACTION_USE;
								else
									return ACTIONS.ACTION_UP;
							}
						}
		}

		return null;
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

		// Comprobar si hay una enfermera en un rango
		/*
		 * ArrayList<Observation> contenido; Observation propiedades; if (donde == 'D'
		 * || donde == 'I') { for (int y = (int) (posY - 0.2); y <= posY + 0.2; y++) {
		 * contenido = m53.getEntorno()[(int) posX][y]; if (contenido.size() > 0) {
		 * propiedades = contenido.get(0); if (propiedades.itype == 12) // Enfermera {
		 * return true; } } } } else { for (int x = (int) (posX - 0.2); x <= posX + 0.2;
		 * x++) { contenido = m53.getEntorno()[x][(int) posY]; if (contenido.size() > 0)
		 * { propiedades = contenido.get(0); if (propiedades.itype == 12) // Enfermera {
		 * return true; } } } }
		 */

		ArrayList<Observation> contenido = m53.getEntorno()[(int) posX][(int) posY];

		// Verificar si hay una enfermera
		for (Observation observacion : contenido) {
			if (observacion.itype == 12) {
				return true;
			}
		}

		return false;
	}

	/*
	 * ArrayList<ACTIONS> acercar = new ArrayList<>(); ArrayList<ACTIONS> alejar =
	 * new ArrayList<>();
	 * 
	 * if ((posAvatar.dist(noInfectado) / m53.getBloque()) < 2)
	 * moverHaciaPosicion(posAvatar, noInfectado);
	 * 
	 * // DERECHA
	 * 
	 * if (!hayObstaculo(m53, 'D')) { if (posXAvatar < posXinf) {
	 * acercar.add(ACTIONS.ACTION_RIGHT); } else { alejar.add(ACTIONS.ACTION_RIGHT);
	 * } }
	 * 
	 * // IZQUIERDA if (!hayObstaculo(m53, 'I')) { if (posXAvatar > posXinf) {
	 * 
	 * acercar.add(ACTIONS.ACTION_LEFT); } else { alejar.add(ACTIONS.ACTION_LEFT); }
	 * 
	 * }
	 * 
	 * // ABAJO if (!hayObstaculo(m53, 'A')) { if (posYAvatar < posYinf) {
	 * 
	 * acercar.add(ACTIONS.ACTION_DOWN); } else { alejar.add(ACTIONS.ACTION_DOWN); }
	 * 
	 * }
	 * 
	 * // ARRIBA if (!hayObstaculo(m53, 'U')) { if (posYAvatar > posYinf) {
	 * 
	 * acercar.add(ACTIONS.ACTION_UP); } else { alejar.add(ACTIONS.ACTION_UP); } }
	 * 
	 * Random r = new Random();
	 * 
	 * if (acercar.isEmpty() && alejar.isEmpty()) { return null; // No hay acciones
	 * posibles } else if (alejar.isEmpty()) { return
	 * acercar.get(r.nextInt(acercar.size())); } else if (acercar.isEmpty()) {
	 * return alejar.get(r.nextInt(alejar.size())); } else { int
	 * probabilidadAcercarse = 70; // Probabilidad de acercarse if (r.nextInt(100) <
	 * probabilidadAcercarse) { return acercar.get(r.nextInt(acercar.size())); }
	 * else { return alejar.get(r.nextInt(alejar.size())); } }
	 */
	

	/*
	 * protected boolean hayObstaculo(Mundo53 m53, char donde) { Vector2d posAvatar
	 * = m53.getPosAvatar(); double posX = posAvatar.x / m53.getBloque(); double
	 * posY = posAvatar.y / m53.getBloque();
	 * 
	 * switch (donde) { case 'D': // Derecha posX++; break; case 'U': // Arriba
	 * posY--; break; case 'I': // Izquierda posX--; break; case 'A': // Abajo
	 * posY++; break; }
	 * 
	 * ArrayList<Observation> contenido = m53.getEntorno()[(int) posX][(int) posY];
	 * 
	 * // Verificar si hay un obstáculo en el contenido for (Observation observacion
	 * : contenido) { if (observacion.itype == 0) { return true; } }
	 * 
	 * // No se encontraron obstáculos en la posición adyacente return false;
	 * 
	 * }
	 * 
	 * protected ACTIONS moverHaciaPosicion(Vector2d posAvatar, Vector2d
	 * posicionObjetivo) { double diffX = posicionObjetivo.x - posAvatar.x; double
	 * diffY = posicionObjetivo.y - posAvatar.y;
	 * 
	 * // Mover hacia la posición objetivo en la dirección con la menor distancia if
	 * (Math.abs(diffX) > Math.abs(diffY)) { if (diffX > 0) return
	 * ACTIONS.ACTION_RIGHT; else return ACTIONS.ACTION_LEFT; } else { if (diffY >
	 * 0) return ACTIONS.ACTION_DOWN; else return ACTIONS.ACTION_UP; }
	 * 
	 * }
	 */

}
