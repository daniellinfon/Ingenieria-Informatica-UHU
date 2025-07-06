package si2024.daniellinfonyealu.p05.Restricciones;

import si2024.daniellinfonyealu.p05.Asignacion;

public class Debe_Ser implements Restriccion {
	
	Asignacion asig;
	Integer valor;
	
	public Debe_Ser(Asignacion i1, int valor) {
		this.asig = i1;
		this.valor = valor;
	}
	
	public Debe_Ser(Restriccion r) {
		Debe_Ser ds = (Debe_Ser) r;
		this.asig = ds.asig;
		this.valor = ds.valor;
	}
	
	public Asignacion getAsig() {
		return asig;
	}
	
	public void setAsig(Asignacion i) {
		asig = i;
	}
	
	public Integer getValor() {
		return valor;
	}

	@Override
	public boolean seCumple() {
		if(asig.getValor() == valor || asig.getValor() == -1)
			return true;
		else
			return false;
	}

	@Override
	public int getTipo() {
		// TODO Auto-generated method stub
		return 1;
	} 

}
