/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7_pcd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author usuario
 */
public class Comedero {

    private int esperaPerro = 0, esperaGato = 0, numPerros = 0, numGatos = 0;
    private final int N = 4;
    ReentrantLock mutex = new ReentrantLock();
    Condition colaPerros = mutex.newCondition();
    Condition colaGatos = mutex.newCondition();

    public void entraPerro() throws InterruptedException {
        mutex.lock();
        try {
            esperaPerro++;
            if (numPerros + numGatos == N || numGatos == 3 || (numGatos == 1 && numPerros == 2)) {
                colaPerros.await();
            }
            esperaPerro--;
            numPerros++;
        } catch (InterruptedException ex) {
        } finally {
            mutex.unlock();
        }
    }

    public void salePerro() throws InterruptedException {
        mutex.lock();
        try {
            numPerros--;
            if (esperaPerro > 0) {
                colaPerros.signal();
                
            } else {
                if (numPerros < 3 && !(numGatos == 2 && numPerros == 1)) {
                    colaGatos.signal();
                }
                
            }
        } finally {
            mutex.unlock();
        }
    }

    public void entraGato() throws InterruptedException {
        mutex.lock();
        try {
            esperaGato++;
            if (numPerros + numGatos == N || numPerros == 3 || (numPerros == 1 && numGatos == 2)) {
                colaGatos.await();
            }
            esperaGato--;
            numGatos++;
        } catch (InterruptedException ex) {
        } finally {
            mutex.unlock();
        }
    }

    public void saleGato() throws InterruptedException {
        mutex.lock();
        try {
            numGatos--;
            if (esperaGato > 0) {
                colaGatos.signal();
                
            } else {
                if (numGatos < 3 && !(numPerros == 2 && numGatos == 1)) {
                    colaPerros.signal();
                }
            }
        } finally {
            mutex.unlock();
        }

    }
}
