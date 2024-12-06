/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica10_pcd;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author usuario
 */
public class Controlador extends Thread {

    
    Any2OneChannel entraPerro;
    Any2OneChannel salePerro;
    Any2OneChannel entraGato;
    Any2OneChannel saleGato;
    One2OneChannel[] permiso;
    private final int N = 4;

    public Controlador(Any2OneChannel entraPerro, Any2OneChannel salePerro, Any2OneChannel entraGato, Any2OneChannel saleGato, One2OneChannel[] permiso) {
        this.entraPerro = entraPerro;
        this.salePerro = salePerro;
        this.entraGato = entraGato;
        this.saleGato = saleGato;
        this.permiso = permiso;
    }

    @Override
    public void run() {
        
        int numPerros = 0, numGatos = 0;
        int id;
        final Guard[] guardas_or = new Guard[4];
        guardas_or[0] = entraPerro.in();
        guardas_or[1] = salePerro.in();
        guardas_or[2] = entraGato.in();
        guardas_or[3] = saleGato.in();

        final boolean[] preCondition = new boolean[guardas_or.length];

        final Alternative selector = new Alternative(guardas_or);

        while (true) {

            preCondition[0] = !(numPerros + numGatos == N || numGatos == 3 || (numGatos == 1 && numPerros == 2));
            preCondition[1] = true;
            preCondition[2] = !(numPerros + numGatos == N || numPerros == 3 || (numPerros == 1 && numGatos == 2));
            preCondition[3] = true;
                    
            int index = selector.select(preCondition);
            System.out.println(index);
            switch (index) {
                case 0:
                    //System.out.println("Entra Perro");
                    id = (int) entraPerro.in().read();
                    numPerros++;
                    permiso[id].out().write(id);
                    break;
                case 1:
                    //System.out.println("Sale Perro");
                    id = (int) salePerro.in().read();
                    numPerros--;
                    break;
                case 2:
                    //System.out.println("Entra Gato");
                    id = (int) entraGato.in().read();
                    numGatos++;
                    permiso[id].out().write(id);
                    break;
                case 3:
                    //System.out.println("Sale Gato");
                    id = (int) saleGato.in().read();
                    numGatos--;
                    break;
                default:
                    System.out.println("Error");

            }

        }

    }

   /* public void entraPerro() throws InterruptedException {
        esperaPerro++;
        if (numPerros + numGatos == N || numGatos == 3 || (numGatos == 1 && numPerros == 2)) {
            colaPerros.await();
        }
        esperaPerro--;
        numPerros++;
    }

    public void salePerro() throws InterruptedException {

        numPerros--;
        if (esperaPerro > 0) {
            colaPerros.signal();

        } else {
            if (numPerros < 3 && !(numGatos == 2 && numPerros == 1)) {
                colaGatos.signal();
            }

        }

    }

    public void entraGato() throws InterruptedException {

        esperaGato++;
        if (numPerros + numGatos == N || numPerros == 3 || (numPerros == 1 && numGatos == 2)) {
            colaGatos.await();
        }
        esperaGato--;
        numGatos++;

    }

    public void saleGato() throws InterruptedException {

        numGatos--;
        if (esperaGato > 0) {
            colaGatos.signal();

        } else {
            if (numGatos < 3 && !(numPerros == 2 && numGatos == 1)) {
                colaPerros.signal();
            }
        }

    }*/
}
