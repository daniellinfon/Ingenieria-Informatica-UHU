package ArbolSintaxisAbs;

public class Fichero {
	
	private String id;
	private Expression exp;
	
	public Fichero(String id, Expression exp) {
		this.id = id;
		this.exp = exp;
	}

	public String getId() {
		return id;
	}

	public Expression getExp() {
		return exp;
	}
			
}
