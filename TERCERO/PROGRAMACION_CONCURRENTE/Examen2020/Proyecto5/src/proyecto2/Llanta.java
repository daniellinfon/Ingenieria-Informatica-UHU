/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class Llanta implements Runnable{

    private Semaphore PC;
    private Semaphore Llanta;

    public Llanta(Semaphore PC, Semaphore Llanta) {
        this.PC = PC;
        this.Llanta = Llanta;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {
            int id = (int) Thread.currentThread().getId();
            System.out.println("Soy la LLANTA "+id);
            while(PC.availablePermits()==0 ||(PC.availablePermits()==1 && Llanta.availablePermits()<3)){}
            Llanta.acquire();
            System.out.println("\t\t---> LLANTA "+id+" ENTRA");
            sleep((rnd.nextInt(2)+2)*3000);
            System.out.println("\t\t\t\t<--- LLANTA "+id+" SALE");
            Llanta.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Llanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
