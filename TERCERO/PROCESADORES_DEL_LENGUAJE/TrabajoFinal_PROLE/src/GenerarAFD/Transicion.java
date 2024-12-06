package GenerarAFD;

public class Transicion {

	private char simbolo;
	private int destino;
	
	public Transicion(char simbolo, int destino) {
		this.simbolo = simbolo;
		this.destino = destino;
	}
	public char getSimbolo() {
		return simbolo;
	}
	public int getDestino() {
		return destino;
	}
	
	
}
