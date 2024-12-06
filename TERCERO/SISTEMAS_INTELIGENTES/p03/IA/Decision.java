package si2024.daniellinfonyealu.p03.IA;

import java.util.List;

import ontology.Types.ACTIONS;

public abstract class Decision implements NodoArbol{
	
	private Condicion antecedentes;
	private NodoArbol nodoSi;
	private NodoArbol nodoNo;
	
	public NodoArbol ObtenerRama(Mundo m){
		if(antecedentes.seCumple(m))
			return nodoSi.decide(m);
		else
			return nodoNo.decide(m);
	}
	
	@Override
	public NodoArbol decide(Mundo m) {
		// TODO Auto-generated method stub
		return ObtenerRama(m);
	}

	public Condicion getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(Condicion antecedentes) {
		this.antecedentes = antecedentes;
	}

	public NodoArbol getNodoNo() {
		return nodoNo;
	}

	public void setNodoNo(NodoArbol nodoNo) {
		this.nodoNo = nodoNo;
	}

	public NodoArbol getNodoSi() {
		return nodoSi;
	}

	public void setNodoSi(NodoArbol nodoSi) {
		this.nodoSi = nodoSi;
	}

}
