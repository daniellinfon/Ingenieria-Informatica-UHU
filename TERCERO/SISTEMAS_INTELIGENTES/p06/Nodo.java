package si2024.daniellinfonyealu.p06;

import java.util.List;
import java.util.Stack;

import si2024.daniellinfonyealu.p06.Acciones.Accion;

public class Nodo {

	 Stack<Object> pila;
	 List<Accion> plan;
	 Estado estado;
	
	public Nodo(List<Accion> plan, Stack<Object> pila, Estado estado) {
		this.pila = pila;
		this.plan = plan;
		this.estado = estado;
	}
	
	public boolean esBucle() {
        Stack<Object> pilaCopia = new Stack<Object>();
        pilaCopia.addAll(pila);
        String meta1 = (String) pilaCopia.pop();
        
        for(int i=0; i<pilaCopia.size(); i++) {
        	Object elem = pilaCopia.pop();
            if(elem instanceof String) {
                String meta2 = (String) elem;
                if(meta1.equals(meta2))
                    return true;
            }
        }
        return false;
    }
}
