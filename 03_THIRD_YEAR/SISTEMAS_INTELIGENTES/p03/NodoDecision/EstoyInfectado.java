package si2024.daniellinfonyealu.p03.NodoDecision;

import si2024.daniellinfonyealu.p03.Condiciones.CondicionInfectado;
import si2024.daniellinfonyealu.p03.IA.Decision;
import si2024.daniellinfonyealu.p03.IA.Mundo;
import si2024.daniellinfonyealu.p03.IA.NodoArbol;
import si2024.daniellinfonyealu.p03.NodoAccion.AtacarNoInfectado;
import si2024.daniellinfonyealu.p03.NodoAccion.Infectarse;

public class EstoyInfectado extends Decision{

	
	private Mundo m53;
	
	public EstoyInfectado() {
		setAntecedentes(new CondicionInfectado());
		setNodoNo(new Infectarse());
		setNodoSi(new AtacarNoInfectado());
	}
	
}
