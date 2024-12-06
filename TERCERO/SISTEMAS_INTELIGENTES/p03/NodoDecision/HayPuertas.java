package si2024.daniellinfonyealu.p03.NodoDecision;

import si2024.daniellinfonyealu.p03.Condiciones.CondicionHayPuertas;
import si2024.daniellinfonyealu.p03.IA.Decision;
import si2024.daniellinfonyealu.p03.NodoAccion.AlejarsePuerta;

public class HayPuertas extends Decision{
	
	public HayPuertas() {
		setAntecedentes(new CondicionHayPuertas());
		setNodoNo(new EstoyInfectado());
		setNodoSi(new AlejarsePuerta()); //Enfermera
		
	}
	
}
