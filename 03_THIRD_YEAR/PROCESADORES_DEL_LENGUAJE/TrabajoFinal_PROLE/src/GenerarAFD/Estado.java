package GenerarAFD;

public class Estado {
	
	private int id;
	private boolean esFinal;
	
	public Estado(int id, boolean esFinal) {
		this.id = id;
		this.esFinal = esFinal;
	}
	public int getId() {
		return id;
	}
	public boolean isFinal() {
		return esFinal;
	}
}