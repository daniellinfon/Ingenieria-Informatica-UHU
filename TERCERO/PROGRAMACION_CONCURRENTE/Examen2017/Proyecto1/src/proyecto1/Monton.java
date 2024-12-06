/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author danie
 */
public class Monton {

    private volatile int minerales = 4, pequeniosLibre = 2;
    private volatile boolean grandeLibre = true;

    public synchronized void cargaPoco() throws InterruptedException {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        while ((minerales > 2 && grandeLibre) || minerales == 0) {
            wait();
        }
        minerales--;
        notifyAll();
        sleep((r.nextInt(3) + 1) * 1000);
    }

    public synchronized void cargaMucho() throws InterruptedException {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        while (minerales < 3) {
            wait();
        }
        grandeLibre = false;
        minerales = minerales - 3;
        notifyAll();
        sleep((r.nextInt(3) + 2) * 1000);
        grandeLibre = true;
    }

    public synchronized void terminaCargaMucho() {
        grandeLibre = false;
    }

    public synchronized int rellena() {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        int recarga = r.nextInt(4) + 2;
        minerales = minerales + recarga;
        notifyAll();
        return recarga;
    }

    public synchronized int getMinerales() {
        return minerales;
    }

}
