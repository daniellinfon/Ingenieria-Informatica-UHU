/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import org.jcsp.lang.Any2OneChannel;

/**
 *
 * @author danie
 */
public class Cinta extends Thread {


    private  Any2OneChannel recarga;
    private int id;

    public Cinta(Any2OneChannel recarga, int id) {
        this.recarga = recarga;
        this.id = id;
    }


    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        try {
            while (!this.isInterrupted()) {
                recarga.out().write(id);
                sleep((r.nextInt(4) + 2)*1000);
                
            }
        } catch (InterruptedException ex) {
            System.out.println("Cinta interrumpido...");
        }

    }
}
