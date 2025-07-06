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
public class Efectivo implements Callable<Integer> {

    private Centro c;
    private CanvasCentro cc;

    public Efectivo(Centro c, CanvasCentro cc) {
        this.c = c;
        this.cc = cc;
    }

    @Override
    public Integer call() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int espera = (rnd.nextInt(2) + 1) * 1000;
        try {
            int id = (int) Thread.currentThread().getId();
            System.out.println("Soy el cliente " + id + " y voy a pagar en EFECTIVO");
            cc.encolar(id,'E');
            c.entraEfectivo();
            cc.desencolar(id,'E');
            cc.enCaja(id, 'E');
            System.out.println(" \t\t----> cliente " + id + " pasa a caja");
            sleep(espera);
            c.saleEfectivo();
            cc.finCaja(id);
            System.out.println(" \t\t<---- cliente " + id + " SALE de CAJA");
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(Efectivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return espera;
    }
}
