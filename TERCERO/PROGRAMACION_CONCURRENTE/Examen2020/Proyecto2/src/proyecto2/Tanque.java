/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author danie
 */
public class Tanque {

    private int numLlantas = 0, numPC = 0, esperaPC = 0, esperaLlanta = 0;
    ReentrantLock mutex = new ReentrantLock();
    Condition colaLlantas = mutex.newCondition();
    Condition colaPC = mutex.newCondition();

    public void entrallanta() throws InterruptedException {
        mutex.lock();
        try {
            esperaLlanta++;
            while (numLlantas == 5 || numPC > 1 || (numPC == 1 && numLlantas == 3)) {
                colaLlantas.await();
            }
            esperaLlanta--;
            numLlantas++;
        } finally {
            mutex.unlock();
        }
    }

    public void salellanta() {
        mutex.lock();
        try {
            numLlantas--;
            if (esperaLlanta > 0) {
                colaLlantas.signal();
            } else {
                colaPC.signal();
            }

        } finally {
            mutex.unlock();
        }
    }

    public void entraPC() throws InterruptedException {
        mutex.lock();
        try {
            esperaPC++;
            while (numLlantas > 3 || numPC > 1 || (numPC == 1 && numLlantas > 0)) {
                colaPC.await();
            }
            esperaPC--;
            numPC++;
        } finally {
            mutex.unlock();
        }

    }

    public void salePC() {
        mutex.lock();
        try {
            numPC--;
            if (esperaPC > 0) {
                colaPC.signal();
            } else {
                colaLlantas.signal();
            }
        } finally {
            mutex.unlock();
        }
    }
}
