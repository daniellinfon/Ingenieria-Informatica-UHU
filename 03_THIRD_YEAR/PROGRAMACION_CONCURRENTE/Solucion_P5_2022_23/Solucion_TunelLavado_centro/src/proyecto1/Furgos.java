/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Furgos implements Runnable {

    Tunel t;
    CanvasTunel cv;

    public Furgos(Tunel tl, CanvasTunel cv) {
        t = tl;
        this.cv = cv;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int id = (int) Thread.currentThread().getId();
        try {

            System.out.println("Soy furgo " + Thread.currentThread().getName());
            cv.enconlafurgos(id);
            char donde = t.entraFurgo();
            cv.fincolafurgos(id);
            System.out.println("                                    ----> furgo " + Thread.currentThread().getName() + " ATENDIDO POR " + donde);
            if (donde == 'c') {
                cv.encentro(id, 'f');
            } else {
                cv.enlateral(id, 'f');
            }

            Thread.sleep((rnd.nextInt(3) + 1) * 2000);
            System.out.println("                                       <---- FIN furgo " + Thread.currentThread().getName());
              if (donde == 'c') {
                cv.fincentro();
            } else {
                cv.finlateral(id);
            }
            t.salefurgo(donde);

        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
