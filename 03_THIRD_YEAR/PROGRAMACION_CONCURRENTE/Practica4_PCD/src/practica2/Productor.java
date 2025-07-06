package practica2;

import java.util.Random;
import practica1.Pila;

/**
 *
 * @author danie
 */
public class Productor extends Thread {

    private volatile Pila lapila;
    private final int capacidad;

    public Productor(Pila pila, int capacidad) {
        lapila = pila;
        this.capacidad = capacidad;
    }

    @Override
    public void run() {
        producir();
    }

    public void producir() {
        try {
            for (int i = 0; i < capacidad; i++) {
                Random r = new Random(System.currentTimeMillis());
                int n = r.nextInt(100);
                lapila.Apila(n);
                System.out.println("Hilo " + getId() + " Apilo: " + n);
            }
        } catch (Exception ex) {
            System.out.println("Hilo " + getId() + " - Fin de los intentos: " + ex.getMessage());
        }
        System.out.println("Hilo "+getId()+ " Productor acaba");
    }
}
