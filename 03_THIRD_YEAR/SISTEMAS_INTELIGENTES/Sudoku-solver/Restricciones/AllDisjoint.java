package si2024.daniellinfonyealu.p05.Restricciones;

import java.util.ArrayList;

import si2024.daniellinfonyealu.p05.Asignacion;

public class AllDisjoint implements Restriccion {
	

	private ArrayList<Asignacion> asignaciones;
	
	public AllDisjoint(ArrayList<Asignacion> asig) {
		this.asignaciones = asig;
	}
	
	@Override
	public boolean seCumple() {
		for(Asignacion ins1 : asignaciones) {
			if(ins1.getValor() != -1) {
				for(Asignacion ins2 : asignaciones) {
					if(ins1.getValor() == ins2.getValor() && !ins1.equals(ins2))
						return false;
				}
			}
			
		}
		return true;
	}

	@Override
	public int getTipo() {
		// TODO Auto-generated method stub
		return 2;
	}

}
