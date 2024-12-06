package si2024.daniellinfonyealu.p01.condiciones;

import si2024.daniellinfonyealu.p01.Mundo18;
import si2024.daniellinfonyealu.p01.IA.Condicion;
import si2024.daniellinfonyealu.p01.IA.Mundo;
import tools.Vector2d;

public class NoEstarEnPeligro implements Condicion {

	@Override
	public boolean seCumple(Mundo m) {
		// TODO Auto-generated method stub
		Mundo18 m18 = (Mundo18) m;

		if (m18.getNumAguilasNegras() == 0)
			return true;

		Vector2d posAvatar = m18.getPosAvatar();
		Vector2d posAguilasNegra = m18.AguilaNegraMasCercana();
		Vector2d posAguilasBlanca = m18.AguilaBlancaMasCercana();
		int bloque = m18.getBloque();

		// Estoy en peligro cuando hay un aguila negra en un rango de 3 bloques
		double distAvatarBlanca = posAvatar.dist(posAguilasBlanca) / bloque;
		double distAvatarNegra = posAvatar.dist(posAguilasNegra) / bloque;

		if (distAvatarNegra <= 1)
			if(distAvatarBlanca <= 1)
				return true;
			else
				return false;
		else
			return true;

	}
}
