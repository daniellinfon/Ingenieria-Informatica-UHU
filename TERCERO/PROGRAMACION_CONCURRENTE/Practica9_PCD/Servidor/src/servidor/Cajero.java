/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import IRemoto.IRemoto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cajero extends UnicastRemoteObject implements IRemoto {

    private final int n = 4;
    private int NTarjeta = 0;
    private int NEfectivo = 0;
    private final Lock mutex = new ReentrantLock();
    private final Condition cTarjeta = mutex.newCondition();
    private final Condition cEfectivo = mutex.newCondition();
    private int Efectivoesperando = 0;
    private CanvasCajero cv;

    public Cajero(CanvasCajero cv) throws RemoteException{
        this.cv = cv;
    }

    @Override
    public void entraTarjeta(int id) throws InterruptedException {
        mutex.lock();
        try {
            cv.encolatarjeta(id);
            if (NEfectivo + NTarjeta == n || (Efectivoesperando > 0 && NEfectivo == 0)) {
                cTarjeta.await();

            }

            cv.fincolatarjeta(id);
            NTarjeta++;
            cv.encajero(id, 'T');
            
        } finally {
            mutex.unlock();
        }

    }

    @Override
    public void saleTarjeta(int id) {
        mutex.lock();
        try {
            NTarjeta--;
            cv.fincajero(id, 'T');
            if (Efectivoesperando > 0 && NEfectivo == 0) {
                cEfectivo.signal();

            } else {
                cTarjeta.signal();

            }
        } finally {
            mutex.unlock();
        }

    }

    @Override
    public void entraEfectivo(int id) throws InterruptedException {
        mutex.lock();
        try {
            Efectivoesperando++;
            cv.encolaefectivo(id);
            if (NEfectivo + NTarjeta == n || NEfectivo == 1) {
                cEfectivo.await();
                
            }
            
            cv.fincolaefectivo(id);
            Efectivoesperando--;
            NEfectivo++;
            cv.encajero(id, 'E');
            
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public void saleEfectivo(int id) {
        mutex.lock();
        try {
            NEfectivo--;
            cv.fincajero(id, 'E');
            if (Efectivoesperando > 0 && NEfectivo == 0) {
                cEfectivo.signal();

            } else {
                cTarjeta.signal();
            }
        } finally {
            mutex.unlock();
        }

    }
}
