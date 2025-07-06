/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class Parachoque extends Thread{
    private Semaphore PC;
    private Semaphore Llanta;

    public Parachoque(Semaphore PC, Semaphore Llanta) {
        this.PC = PC;
        this.Llanta = Llanta;
    }

    
    
    @Override
    public void run() {
        try {
            int id = (int) Thread.currentThread().getId();
            System.out.println("Soy el PARACHOQUE "+id);
            while((PC.availablePermits()==1 && Llanta.availablePermits()<5)||Llanta.availablePermits()<2){}
            PC.acquire();
            System.out.println("\t\t--->  PARACHOQUE "+id+" ENTRA");
            sleep(7000);
            System.out.println("\t\t\t\t<---  PARACHOQUE "+id+" SALE");
            PC.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Parachoque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
