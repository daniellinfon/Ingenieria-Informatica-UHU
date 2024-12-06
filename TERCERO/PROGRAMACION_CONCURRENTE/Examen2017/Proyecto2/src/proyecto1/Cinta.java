/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;

/**
 *
 * @author danie
 */
public class Cinta extends Thread {

    private Monton m;

    public Cinta(Monton m) {
        this.m = m;
    }

    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        try {
            while (!this.isInterrupted()) {
                System.out.println("Cantidad de Minerales: " + m.getMinerales() + " Tm");
                sleep((r.nextInt(4) + 2)*1000);
                System.out.println("Cinta recarga " + m.rellena());
                

            }
        } catch (InterruptedException ex) {
            System.out.println("Cinta interrumpido...");
        }

    }
}
