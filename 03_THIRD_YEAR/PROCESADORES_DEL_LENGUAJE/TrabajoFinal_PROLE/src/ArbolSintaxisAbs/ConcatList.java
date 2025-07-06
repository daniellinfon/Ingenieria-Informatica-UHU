package ArbolSintaxisAbs;

import java.util.ArrayList;

public class ConcatList extends Expression {
	
	private ArrayList<Expression> list;
	
	public ConcatList(Expression exp) {
        list = new ArrayList<Expression>();
        list.add(exp);
    }

    public ConcatList() {
        list = new ArrayList<Expression>();
    }

    public void concat(Expression exp) {
        list.add(0, exp);
    }

    public ArrayList<Expression> getList() {
        return list;
    }

}
