package si2024.daniellinfonyealu.p05;

public class ArcoConsistente {
	public Variable distinguida;
	public Variable noDistinguida;
	
	public ArcoConsistente(Variable v1, Variable v2) {
		this.distinguida = v1;
		this.noDistinguida = v2;
	}
	
	public boolean esConsistente() {
		if(noDistinguida.getDominio().size() == 1) {
			Integer valor = noDistinguida.getDominio().get(0);
			for(Integer v : distinguida.getDominio()) {
				if(valor == v) {
					distinguida.getDominio().remove((Object)v);
					return false;
				}
					
			}
			return true;
		} else
			return true;
	}

	public Variable getDistinguida() {
		return distinguida;
	}

	public Variable getNoDistinguida() {
		return noDistinguida;
	}
	
	
}
