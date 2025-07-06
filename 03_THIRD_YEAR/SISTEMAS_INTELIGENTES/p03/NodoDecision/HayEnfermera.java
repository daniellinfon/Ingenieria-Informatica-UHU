package si2024.daniellinfonyealu.p03.NodoDecision;

import si2024.daniellinfonyealu.p03.Condiciones.CondicionHayEnfermera;
import si2024.daniellinfonyealu.p03.Condiciones.MasCercaEnfermeraOrNoInfectado;
import si2024.daniellinfonyealu.p03.IA.Decision;
import si2024.daniellinfonyealu.p03.IA.Mundo;
import si2024.daniellinfonyealu.p03.IA.NodoArbol;
import si2024.daniellinfonyealu.p03.NodoAccion.AtacarEnfermera;
import si2024.daniellinfonyealu.p03.NodoAccion.AtacarNoInfectado;

public class HayEnfermera extends Decision{
	
	public HayEnfermera() {
		setAntecedentes(new CondicionHayEnfermera());
		setNodoNo(new HayPuertas());
		setNodoSi(new AtacarEnfermera()); //Enfermera
		
	}
	
}
