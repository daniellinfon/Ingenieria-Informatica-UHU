/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto4;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author danie
 */
public class Controlador extends Thread {
    
    Any2OneChannel entraLlanta;
    Any2OneChannel saleLlanta;
    Any2OneChannel entraPC;
    Any2OneChannel salePC;
    One2OneChannel permiso[];

    public Controlador(Any2OneChannel entraLlanta, Any2OneChannel saleLlanta, Any2OneChannel entraPC, Any2OneChannel salePC, One2OneChannel[] permiso) {
        this.entraLlanta = entraLlanta;
        this.saleLlanta = saleLlanta;
        this.entraPC = entraPC;
        this.salePC = salePC;
        this.permiso = permiso;

    }

    @Override
    public void run() {

        int id;
        int numLlantas = 0, numPC = 0;
        final Guard[] guardas_or = new Guard[4];
        guardas_or[0] = entraLlanta.in();
        guardas_or[1] = saleLlanta.in();
        guardas_or[2] = entraPC.in();
        guardas_or[3] = salePC.in();

        final boolean[] preCondition = new boolean[guardas_or.length];

        final Alternative selector = new Alternative(guardas_or);
        
        while(true){
            
            preCondition[0] = !(numLlantas == 5 || numPC == 2 || (numPC == 1 && numLlantas == 3));
            preCondition[1] = true;
            preCondition[2] = !(numLlantas > 3 || numPC == 2 || (numPC == 1 && numLlantas == 3));
            preCondition[3] = true;
            
            int index = selector.select();
            switch(index){
                case 0:
                    id = (int) entraLlanta.in().read();
                    numLlantas++;
                    permiso[id].out().write(id);
                    break;
                case 1:
                    id = (int) saleLlanta.in().read();
                    numLlantas--;
                    break;
                case 2:
                    id = (int) entraPC.in().read();
                    numPC++;
                    permiso[id].out().write(id);
                    break;
                case 3:
                    id = (int) salePC.in().read();
                    numPC--;
                    break;
                default:
                    System.out.println("Error");
            }
            
        }
    }

    /*public void entrallanta() throws InterruptedException {
        mutex.lock();
        try {
            esperaLlanta++;
            while (numLlantas == 5 || numPC > 1 || (numPC == 1 && numLlantas == 3)) {
                colaLlantas.await();
            }
            esperaLlanta--;
            numLlantas++;
        } finally {
            mutex.unlock();
        }
    }

    public void salellanta() {
        mutex.lock();
        try {
            numLlantas--;
            if (esperaLlanta > 0) {
                colaLlantas.signal();
            } else {
                colaPC.signal();
            }

        } finally {
            mutex.unlock();
        }
    }

    public void entraPC() throws InterruptedException {
        mutex.lock();
        try {
            esperaPC++;
            while (numLlantas > 3 || numPC > 1 || (numPC == 1 && numLlantas > 0)) {
                colaPC.await();
            }
            esperaPC--;
            numPC++;
        } finally {
            mutex.unlock();
        }

    }

    public void salePC() {
        mutex.lock();
        try {
            numPC--;
            if (esperaPC > 0) {
                colaPC.signal();
            } else {
                colaLlantas.signal();
            }
        } finally {
            mutex.unlock();
        }
    }*/
}
