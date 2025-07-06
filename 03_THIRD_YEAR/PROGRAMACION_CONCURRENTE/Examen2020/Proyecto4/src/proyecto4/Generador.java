/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto4;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannel;
import org.jcsp.util.Buffer;

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
        
        Any2OneChannel entraLlanta = Channel.any2one(new Buffer(numcromas));
        Any2OneChannel saleLlanta = Channel.any2one(new Buffer(numcromas));
        Any2OneChannel entraPC = Channel.any2one(new Buffer(numcromas));
        Any2OneChannel salePC = Channel.any2one(new Buffer(numcromas));
        One2OneChannel permiso[] = new One2OneChannel[numcromas];
        
        for (int i = 0; i < numcromas; i++) {
            permiso[i]= Channel.one2one(new Buffer(1));
        }
        
        Thread cromas[] = new Thread[numcromas];
        Thread t = new Controlador(entraLlanta, saleLlanta, entraPC, salePC, permiso);
        t.start();
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {
            for (int i = 0; i < numcromas; i++) {
                int num = rnd.nextInt(100);
                if (num < 30) {
                    cromas[i] = new Parachoque(entraPC, salePC, permiso[i], i);
                    cromas[i].start();
                } else {
                    Llanta l = new Llanta(entraLlanta, saleLlanta, permiso[i], i);
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
