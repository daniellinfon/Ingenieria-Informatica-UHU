/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class Grande implements Runnable {

    private Semaphore minerales;
    private Semaphore cargaPoco;
    private Semaphore cargaMucho;

    public Grande(Semaphore minerales, Semaphore cargaPoco, Semaphore cargaMucho) {
        this.minerales = minerales;
        this.cargaPoco = cargaPoco;
        this.cargaMucho = cargaMucho;
    }

    @Override
    public void run() {
        int id = (int) Thread.currentThread().getId();
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        System.out.println("Soy la Cargadora Grande " + id);
        for (int i = 0; i < 7; i++) {
            try {
                cargaMucho.acquire();
                minerales.acquire(3);
                System.out.println("\t\t\t<---Cargadora " + id + " carga 3 Tm");
                sleep((r.nextInt(3) + 2) * 1000);

            } catch (InterruptedException ex) {
                Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Grande termina");
    }

}
