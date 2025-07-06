package ArbolSintaxisAbs;

import java.util.ArrayList;

public class OptionList extends Expression {
	
	private ArrayList<Expression> list;

	public OptionList() {
        list = new ArrayList<Expression>();
    }

    public OptionList(Expression exp) {
        list = new ArrayList<Expression>();
        list.add(exp);
    }

    public void addOption(Expression exp) {
        list.add(0, exp);
    }

    public ArrayList<Expression> getList() {
        return list;
    }
}
