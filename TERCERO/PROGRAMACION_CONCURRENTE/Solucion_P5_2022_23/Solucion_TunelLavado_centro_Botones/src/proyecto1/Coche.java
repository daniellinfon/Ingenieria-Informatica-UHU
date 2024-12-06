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
public class Coche extends Thread {

    Tunel t;
    CanvasTunel cv;

    public Coche(Tunel tl, CanvasTunel cv) {
        t = tl;
        this.cv = cv;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int id = (int) Thread.currentThread().getId();

        try {

            System.out.println("Soy el coche " + getName());
            cv.enconlacoches(id);
            char donde = t.entraCoche();
            cv.fincolacoches(id);
            if (donde == 'c') {
                cv.encentro(id, 'c');
            } else {
                cv.enlateral(id, 'c');
            }
            System.out.println("                 ----> coche " + getName() + " ATENDIDO en " + donde);
            Thread.sleep((rnd.nextInt(3) + 2) * 2000);
            System.out.println("                 <---- FIN coche " + getName());
            if (donde == 'c') {
                cv.fincentro();
            } else {
                cv.finlateral(id);
            }
            sleep(500);
            t.salecoche(donde);

        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
