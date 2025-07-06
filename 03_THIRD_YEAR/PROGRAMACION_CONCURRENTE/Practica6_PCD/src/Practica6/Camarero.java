package Practica6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class Camarero extends Thread {

    private CanvasCongreso cc;
    Semaphore leche;
    Semaphore cafe;

    public Camarero(CanvasCongreso cc, Semaphore leche, Semaphore cafe) {
        this.cc = cc;
        this.leche = leche;
        this.cafe = cafe;
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {
                rellenar();
                System.out.println("Camarero recarga leche y cafe"
                        + "\n\t- Dosis de leche disponibles: " + leche.availablePermits()
                        + "\n\t- Dosis de cafe disponibles: " + cafe.availablePermits());
            }
        } catch (InterruptedException ex) {
            System.out.println("Camarero interrumpido...");
        }
    }

    public synchronized void rellenar() throws InterruptedException {
        //El camarero recarga cafe y leche a la vez cada 6 segs como indica el enunciado pero para el Canvas
        // se mostrara 2segs recargando cafe, 2 segs recargando leche y otros 2segs de espera para asi llegar a 6
        leche.release(5);
        cafe.release(5);
        cc.camarero('L');
        sleep(2000);
        cc.fincamarero();
        cc.camarero('C');
        sleep(2000);
        cc.fincamarero();
        sleep(2000);
    }
}
