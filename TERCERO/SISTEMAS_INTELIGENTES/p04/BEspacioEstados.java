package si2024.daniellinfonyealu.p04;

import java.util.ArrayList;
import java.util.List;

import core.game.Observation;
import ontology.Types.ACTIONS;
import si2024.daniellinfonyealu.p04.astar.Node;
import si2024.daniellinfonyealu.p04.Acciones.Abajo;
import si2024.daniellinfonyealu.p04.Acciones.Arriba;
import si2024.daniellinfonyealu.p04.Acciones.Derecha;
import si2024.daniellinfonyealu.p04.Acciones.Izquierda;
import si2024.daniellinfonyealu.p04.IA.Motor;
import si2024.daniellinfonyealu.p04.IA.Mundo;
import si2024.daniellinfonyealu.p04.astar.AStar;
import tools.Vector2d;

public class BEspacioEstados implements Motor {

	private AStar aStar;
	private Vector2d posAvatar;
	private Vector2d objetivo;
	private Vector2d orientacion;
	Node initialNode;
	Node finalNode;

	@Override
	public ACTIONS piensa(Mundo m) {
		Mundo45 m45 = (Mundo45) m;
		posAvatar = m45.getPosAvatar();
		/*
		 * if (m45.numManzanas() == m45.getNumManzanasInicial()-1) { objetivo =
		 * m45.objetivoMasCercano(); System.out.println("oki"); } else { objetivo =
		 * m45.objetivoMasCercanoX();
		 * 
		 * }
		 */
		objetivo = m45.comparaObjetivos();
		//objetivo = m45.objetivoMasCercanoX();
		orientacion = m45.getAvatarOrientation();

		double posXobj = objetivo.x / m45.getBloque();
		double posYobj = objetivo.y / m45.getBloque();
		double posXAvatar = posAvatar.x / m45.getBloque();
		double posYAvatar = posAvatar.y / m45.getBloque();

		initialNode = new Node((int) posXAvatar, (int) posYAvatar);
		finalNode = new Node((int) posXobj, (int) posYobj);

		aStar = new AStar(m45.getAnchura(), m45.getAltura(), initialNode, finalNode);
		aStar.setBlocks(m45.getBlocksArray());

		List<Node> path = aStar.findPath();

		// for (Node node : path) { System.out.println(node); }

		if (path.size() > 1) {
			int diffX = (int) (path.get(1).getRow() - posXAvatar);
			int diffY = (int) (path.get(1).getCol() - posYAvatar);
			// System.out.println(diffX + " - " + diffY);

			// Mover hacia la posición objetivo en la dirección con la menor distancia
			if (Math.abs(diffX) > Math.abs(diffY)) {
				if (diffX > 0) {

					Derecha d = new Derecha();
					return m45.aplicarOperador(d);

				} else {

					Izquierda i = new Izquierda();
					return m45.aplicarOperador(i);

				}
			} else {
				if (diffY > 0) {

					Abajo a = new Abajo();
					return m45.aplicarOperador(a);

				} else {

					Arriba up = new Arriba();
					return m45.aplicarOperador(up);

				}
			}

		}
		return null;
	}

}
