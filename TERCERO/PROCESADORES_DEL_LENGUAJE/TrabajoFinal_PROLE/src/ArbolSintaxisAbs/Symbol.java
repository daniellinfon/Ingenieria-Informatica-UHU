package ArbolSintaxisAbs;

public class Symbol extends Expression{
	private char symbol;
	 
	public Symbol(char s) { this.symbol = s; }

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
