package practica2;

import static java.lang.Thread.sleep;
import java.util.Random;
import practica1.Pila;

/**
 *
 * @author danie
 */
public class Consumidor implements Runnable {

    private volatile Pila lapila;
    private final int capacidad;

    public Consumidor(Pila pila, int capacidad) {
        lapila = pila;
        this.capacidad = capacidad;
    }

    @Override
    public void run() {
        consumir();
    }

    public void consumir() {
        for (int i = 0; i < capacidad; i++) {
            try {
                System.out.println("Hilo " + Thread.currentThread().getId() + " Desapilo: " + lapila.Desapila());
            } catch (Exception ex) {
                System.out.println("Hilo " + Thread.currentThread().getId() + " - Fin de los intentos: " + ex.getMessage());
            }
        }
        System.out.println("Hilo "+Thread.currentThread().getId()+ " Consumidor acaba");
    }
}
