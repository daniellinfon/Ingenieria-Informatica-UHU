package ArbolSintaxisAbs;

public class Operation extends Expression {
	public static final int STAR = 1;
	public static final int PLUS = 2;
	public static final int HOOK = 3;
	private int operator;
	private Expression operand;

	public Operation(int op, Expression exp) {
		this.operator = op;
		this.operand = exp;
	}

	public int getOperator() {
		return operator;
	}

	public Expression getOperand() {
		return operand;
	}

	public static String operator(int i) {
		switch (i) {
		case 1: return "STAR";
		case 2: return "PLUS";
		case 3: return "HOOK";
		default:
			return "";
		}
	}
	
}
