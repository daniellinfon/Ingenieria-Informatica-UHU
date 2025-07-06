/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author danie
 */
public class Cinta extends Thread {

    private Semaphore minerales;
    private Semaphore cargaPoco;
    private Semaphore cargaMucho;

    public Cinta(Semaphore minerales, Semaphore cargaPoco, Semaphore cargaMucho) {
        this.minerales = minerales;
        this.cargaPoco = cargaPoco;
        this.cargaMucho = cargaMucho;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        try {
            while (!this.isInterrupted()) {
                System.out.println("Cantidad de Minerales: " + minerales.availablePermits() + " Tm");
                sleep((r.nextInt(4) + 2) * 1000);
                int recarga = r.nextInt(4) + 2;
                minerales.release(recarga);
                System.out.println("Cinta recarga " + recarga);
                
                if (minerales.availablePermits() > 2 && cargaMucho.availablePermits()==0) {
                    cargaMucho.release();
                }

                if (minerales.availablePermits() > 1 && cargaMucho.availablePermits()==1) {
                    cargaPoco.release(2);
                } else if (minerales.availablePermits() ==  1 && cargaMucho.availablePermits()==1) {
                    cargaPoco.release();
                }

            }
        } catch (InterruptedException ex) {
            System.out.println("Cinta interrumpido...");
        }

    }
}
