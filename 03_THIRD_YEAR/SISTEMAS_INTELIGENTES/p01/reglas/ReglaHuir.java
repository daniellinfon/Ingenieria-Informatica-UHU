package si2024.daniellinfonyealu.p01.reglas;

import java.util.ArrayList;
import java.util.List;

import si2024.daniellinfonyealu.p01.IA.Accion;
import si2024.daniellinfonyealu.p01.IA.Condicion;
import si2024.daniellinfonyealu.p01.IA.Mundo;
import si2024.daniellinfonyealu.p01.IA.Reglas;
import si2024.daniellinfonyealu.p01.acciones.AccionHuir;
import si2024.daniellinfonyealu.p01.condiciones.HayAguilasNegras;

public class ReglaHuir implements Reglas{

	private List<Condicion> antecedentes;
	private Accion accion;
	
	public ReglaHuir() {
		
		accion = new AccionHuir();
		antecedentes = new ArrayList<Condicion>();
		
		HayAguilasNegras c1 = new HayAguilasNegras();
		
		antecedentes.add(c1);
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
