/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Tunel {

    private volatile int lateraleslibres = 2, furgolateral = 0;
    private volatile char central = 'n';

    public synchronized char entraCoche() throws InterruptedException {

        while (lateraleslibres == 0 && central != 'n') {
            wait();
        }
        if (central == 'n') {
            central = 'c';
            return 'c';
        } else {
            lateraleslibres--;
            return 'l';
        }
    }

    public synchronized void salecoche(char donde) {
        if (donde == 'l') {
            lateraleslibres++;
        } else {
            central = 'n';
        }
        notifyAll();
    }

    public synchronized char entraFurgo() throws InterruptedException {
        char donde;
        while ((lateraleslibres == 0 && central != 'n') || central == 'f' || (lateraleslibres==0 && furgolateral>0)) {
            wait();
        }
        if (lateraleslibres > 0) {
            lateraleslibres--;
            furgolateral++;
            donde = 'l';
        }else{
            central='f';
            donde='c';
        }
        return donde;
    }
    
    
    public synchronized void salefurgo(char donde) {
        if (donde == 'l') {
            lateraleslibres++;
            furgolateral--;
        } else {
            central = 'n';
        }
        notifyAll();
    }
}
