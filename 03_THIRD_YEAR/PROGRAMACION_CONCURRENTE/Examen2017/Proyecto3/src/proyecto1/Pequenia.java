/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class Pequenia extends Thread {

    private Semaphore minerales;
    private Semaphore cargaPoco;
    private Semaphore cargaMucho;

    public Pequenia(Semaphore minerales, Semaphore cargaPoco, Semaphore cargaMucho) {
        this.minerales = minerales;
        this.cargaPoco = cargaPoco;
        this.cargaMucho = cargaMucho;
    }

    @Override
    public void run() {
        int id = (int) getId();
        System.out.println("Soy la Cargadora Pequenia " + id);
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {
            try {
                if (cargaMucho.availablePermits() == 0) {
                    cargaPoco.release();
                }
                cargaPoco.acquire();
                minerales.acquire();
                System.out.println("\t\t\t<---Cargadora " + id + " carga 1 Tm");
                
                sleep((r.nextInt(3) + 1) * 1000);

            } catch (InterruptedException ex) {
                Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("Soy la Cargadora Pequenia " + id + " y he terminado.");
    }

}
