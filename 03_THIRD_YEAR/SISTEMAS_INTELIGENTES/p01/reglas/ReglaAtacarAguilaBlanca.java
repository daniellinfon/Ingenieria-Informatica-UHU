package si2024.daniellinfonyealu.p01.reglas;

import java.util.ArrayList;
import java.util.List;

import si2024.daniellinfonyealu.p01.IA.Accion;
import si2024.daniellinfonyealu.p01.IA.Condicion;
import si2024.daniellinfonyealu.p01.IA.Mundo;
import si2024.daniellinfonyealu.p01.IA.Reglas;
import si2024.daniellinfonyealu.p01.acciones.AccionAtacarAguilaBlanco;
import si2024.daniellinfonyealu.p01.condiciones.HayAguilasBlancas;
import si2024.daniellinfonyealu.p01.condiciones.NoEstarEnPeligro;

public class ReglaAtacarAguilaBlanca implements Reglas{

	private List<Condicion> antecedentes;
	private Accion accion;
	
	public ReglaAtacarAguilaBlanca() {
		
		accion = new AccionAtacarAguilaBlanco();
		antecedentes = new ArrayList<Condicion>();
		
		HayAguilasBlancas hayAguilasBlancas = new HayAguilasBlancas();
		NoEstarEnPeligro noPeligro = new NoEstarEnPeligro();
		
		antecedentes.add(hayAguilasBlancas);
		//antecedentes.add(noPeligro);
	}

	@Override
	public boolean seCumple(Mundo m) {
		
		for (Condicion c : antecedentes) {
		    if (!c.seCumple(m))
		        return false;
		}
		return true;
	}

	@Override
	public Accion getAccion() {
		// TODO Auto-generated method stub
		return accion;
	}

}
