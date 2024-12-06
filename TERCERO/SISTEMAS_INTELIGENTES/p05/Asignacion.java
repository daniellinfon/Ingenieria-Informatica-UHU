package si2024.daniellinfonyealu.p05;


public class Asignacion implements Comparable<Asignacion>{
	private Variable variable;
	private int valor;
	
	public Asignacion(Variable v) {
		this.variable = v;
		valor = -1;
	}
	
	public Asignacion(Asignacion i) {
		this.variable = i.variable;
		this.valor = i.valor;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	public Variable getVariable() {
		return variable;
	}

	@Override
	public int compareTo(Asignacion o) {
		return Integer.compare(this.variable.getDominio().size(), o.variable.getDominio().size());
	}
}
