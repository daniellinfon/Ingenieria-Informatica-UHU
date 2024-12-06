package practica1;

import static java.lang.Thread.sleep;
import java.util.Random;
import practica3.CanvasPila;

public class Pila implements IPila {

    private int cima;
    private final int capacidad;
    private int numelementos;
    private final Object[] datos;
    private final CanvasPila cp;

    public Pila(int capacidad, CanvasPila elcanvas) {
        this.capacidad = capacidad;
        cima = 0;
        numelementos = 0;
        datos = new Object[capacidad];
        cp = elcanvas;
    }

    @Override
    public int GetNum() {
        return numelementos;
    }

    /**
     *
     * @param elemento
     * @throws Exception
     * @throws java.lang.InterruptedException
     */
    @Override
    public synchronized void Apila(Object elemento) throws Exception {
        Random r = new Random(System.currentTimeMillis());
        int contador = 3;
        while (contador != 0) {
            if (!pilallena()) {
                datos[cima] = elemento;
                numelementos++;
                cima++;
                sleep(r.nextInt(2000) + 1000);
                cp.representa(datos, cima, numelementos);
                contador = 0;
                notifyAll();
            } else {
                contador--;
                cp.avisa("PILA LLENA");
                if (contador == 0) {
                    throw new Exception("Pila llena");
                }
                wait();
            }
        }
    }

    @Override
    public synchronized Object Desapila() throws Exception {
        Random r = new Random(System.currentTimeMillis());
        int contador = 3;
        while (contador != 0) {
            if (!pilavacia()) {
                numelementos--;
                cima--;
                sleep(r.nextInt(2000) + 1000);
                cp.representa(datos, cima, numelementos);
                contador = 0;
                notifyAll();
                return datos[cima];
                
            } else {
                contador--;
                cp.avisa("PILA VACIA");
                if (contador == 0) {
                    throw new Exception("Pila vacia");
                }
                wait();
            }
        }
        return null;
    }

    @Override
    public Object Primero() throws Exception {
        if (!pilavacia()) {
            return datos[0];
        } else {
            throw new Exception("Pila vacia");
        }

    }

    private boolean pilavacia() {
        return numelementos == 0;
    }

    private boolean pilallena() {
        return numelementos == capacidad;
    }

}
