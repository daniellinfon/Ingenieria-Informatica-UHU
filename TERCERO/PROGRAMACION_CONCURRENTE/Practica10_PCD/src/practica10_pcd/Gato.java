/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica10_pcd;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author usuario
 */
public class Gato implements Runnable {

    private Controlador c;
    private CanvasComedero cc;
    private final int id;
    private final Any2OneChannel entraGato;
    private final Any2OneChannel saleGato;
    private final One2OneChannel permiso;

    public Gato(Controlador c, CanvasComedero cc, Any2OneChannel entraGato, Any2OneChannel saleGato, One2OneChannel permiso, int id) {
        this.c = c;
        this.cc = cc;
        this.id = id;
        this.entraGato = entraGato;
        this.saleGato = saleGato;
        this.permiso = permiso;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        try {
            System.out.println("Soy el Gato " + id);
            cc.encolar(id, 'G');
            entraGato.out().write(id);
            int lee = (int) permiso.in().read();
            cc.desencolar(id, 'G');
            cc.enComedero(id, 'G');
            System.out.println(" \t\t----> Gato " + id + " ENTRA al COMEDERO");
            sleep((rnd.nextInt(5) + 1) * 1000);
            saleGato.out().write(id);
            cc.finComedero(id);
            System.out.println(" \t\t<---- Gato " + id + " SALE del COMEDERO");

        } catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
