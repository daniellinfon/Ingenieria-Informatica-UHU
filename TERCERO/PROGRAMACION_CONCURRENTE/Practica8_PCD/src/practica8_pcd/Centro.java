/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8_pcd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author usuario
 */
public class Centro {

    private int esperaEfectivo = 0, numEfectivo = 0, numTarjeta = 0;
    private final int cajas = 4;
    ReentrantLock mutex = new ReentrantLock();
    Condition colaEfectivo = mutex.newCondition();
    Condition colaTarjeta = mutex.newCondition();

    public void entraEfectivo() throws InterruptedException {
        mutex.lock();
        try {
            esperaEfectivo++;
            if (numEfectivo + numTarjeta == cajas || numEfectivo == 1) {
                colaEfectivo.await();
            }
            esperaEfectivo--;
            numEfectivo++;
        } catch (InterruptedException ex) {
        } finally {
            mutex.unlock();
        }
    }

    public void saleEfectivo() throws InterruptedException {
        mutex.lock();
        try {
            numEfectivo--;
            if (esperaEfectivo > 0 && numEfectivo==0) {
                colaEfectivo.signal();
            } else {
                colaTarjeta.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void entraTarjeta() throws InterruptedException {
        mutex.lock();
        try {
            if (numEfectivo + numTarjeta == cajas || (esperaEfectivo > 0 && numEfectivo == 0)) {
                colaTarjeta.await();
            }
            numTarjeta++;
        } catch (InterruptedException ex) {
        } finally {
            mutex.unlock();
        }
    }

    public void saleTarjeta() throws InterruptedException {
        mutex.lock();
        try {
            numTarjeta--;
            if (esperaEfectivo > 0 && numEfectivo==0) {
                colaEfectivo.signal();
            } else {
                colaTarjeta.signal();
            }
        } finally {
            mutex.unlock();
        }

    }
}
