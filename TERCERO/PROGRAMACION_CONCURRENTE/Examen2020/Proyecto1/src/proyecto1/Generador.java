/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int numcromas = 20;
        Thread cromas[] = new Thread[numcromas];
        Tanque t = new Tanque();
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {
            for (int i = 0; i < numcromas; i++) {
                int num = rnd.nextInt(100);
                if (num < 30) {
                    cromas[i] = new Parachoque(t);
                    cromas[i].start();
                } else {
                    Llanta l = new Llanta(t);
                    cromas[i] = new Thread(l);
                    cromas[i].start();
                }
                sleep((rnd.nextInt(2)+1)*1000);
            }

            for (int i = 0; i < numcromas; i++) {
                cromas[i].join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

}
