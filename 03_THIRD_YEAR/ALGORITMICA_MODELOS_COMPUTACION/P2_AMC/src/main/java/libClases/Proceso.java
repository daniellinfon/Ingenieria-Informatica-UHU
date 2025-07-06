
package libClases;


public interface Proceso {

    public abstract boolean esFinal(String estado); //true si estado es un estado final

    public abstract boolean reconocer(String cadena); //true si la cadena es reconocida

    public abstract String toString(); //muestra las transiciones y estados finales
}
