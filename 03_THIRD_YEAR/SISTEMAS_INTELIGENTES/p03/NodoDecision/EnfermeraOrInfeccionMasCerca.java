package si2024.daniellinfonyealu.p03.NodoDecision;

import si2024.daniellinfonyealu.p03.Condiciones.MasCercaEnfermeraOrInfeccion;
import si2024.daniellinfonyealu.p03.IA.Decision;
import si2024.daniellinfonyealu.p03.IA.Mundo;
import si2024.daniellinfonyealu.p03.IA.NodoArbol;
import si2024.daniellinfonyealu.p03.NodoAccion.AtacarEnfermera;
import si2024.daniellinfonyealu.p03.NodoAccion.Infectarse;

public class EnfermeraOrInfeccionMasCerca extends Decision{
	
	public EnfermeraOrInfeccionMasCerca() {
		setAntecedentes(new MasCercaEnfermeraOrInfeccion());
		setNodoNo(new Infectarse());
		setNodoSi(new AtacarEnfermera()); //Enfermera
	}
	
}
