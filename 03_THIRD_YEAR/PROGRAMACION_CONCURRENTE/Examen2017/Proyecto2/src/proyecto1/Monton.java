/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author danie
 */
public class Monton {

    private int minerales = 4;
    private boolean grandeLibre = true;

    private ReentrantLock mutex = new ReentrantLock();
    private Condition colaPequenia = mutex.newCondition();
    private Condition colaGrande = mutex.newCondition();

    public void cargaPoco() throws InterruptedException {
        mutex.lock();
        try {
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            if ((minerales > 2 && grandeLibre) || minerales == 0) {
                colaPequenia.await();
            }
            minerales--;

        } finally {
            mutex.unlock();
        }
    }

    public void cargaMucho() throws InterruptedException {
        mutex.lock();
        try {
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            if (minerales < 3) {
                colaGrande.await();
            }
            minerales = minerales - 3;
            grandeLibre = false;
            

            sleep((r.nextInt(3) + 2) * 1000);
            grandeLibre = true;

        } finally {
            mutex.unlock();
        }

    }

    public int rellena() {
        mutex.lock();
        try {
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());
            int recarga = r.nextInt(4) + 2;
            minerales = minerales + recarga;
            
            if (minerales > 2 && grandeLibre) {
                colaGrande.signal();
            }
            
            if (minerales > 1) {
                colaPequenia.signal();
                colaPequenia.signal();
            } else if (minerales > 0) {
                colaPequenia.signal();
            }
            
            return recarga;
        } finally {
            mutex.unlock();
        }
    }

    public synchronized int getMinerales() {
        return minerales;
    }

}
