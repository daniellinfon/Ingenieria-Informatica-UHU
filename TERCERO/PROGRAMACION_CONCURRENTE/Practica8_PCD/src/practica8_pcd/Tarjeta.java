/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8_pcd;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author usuario
 */
public class Tarjeta implements Callable<Integer>{

    private Centro c;
    private CanvasCentro cc;

    public Tarjeta(Centro c, CanvasCentro cc) {
        this.c = c;
        this.cc = cc;
    }

    @Override
    public Integer call() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int espera = (rnd.nextInt(4) + 1) * 1000;
        try {
            int id = (int) Thread.currentThread().getId();
            System.out.println("Soy el cliente " + id + " y voy a pagar con TARJETA");
            cc.encolar(id,'T');
            c.entraTarjeta();
            cc.desencolar(id,'T');
            cc.enCaja(id, 'T');
            System.out.println(" \t\t----> cliente " + id + " pasa a caja");
            sleep(espera);
            c.saleTarjeta();
            cc.finCaja(id);
            System.out.println(" \t\t<---- cliente " + id + " SALE de CAJA");
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(Tarjeta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return espera;
    }
    
}
