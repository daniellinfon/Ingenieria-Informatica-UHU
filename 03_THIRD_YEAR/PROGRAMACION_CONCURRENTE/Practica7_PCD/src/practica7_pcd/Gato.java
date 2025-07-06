/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7_pcd;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Gato implements Runnable{

    private Comedero c;
    private CanvasComedero cc;

    public Gato(Comedero c, CanvasComedero cc) {
        this.c = c;
        this.cc = cc;
    }
    
    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int id = (int) Thread.currentThread().getId();
        
        try {
            System.out.println("Soy el Gato "+id);
            cc.encolar(id,'G');
            c.entraGato();
            cc.desencolar(id,'G');
            cc.enComedero(id, 'G');
            System.out.println(" \t\t----> Gato "+id+" ENTRA al COMEDERO");
            sleep((rnd.nextInt(3)+1)*1000);
            c.saleGato();
            cc.finComedero(id);
            System.out.println(" \t\t<---- Gato "+id+" SALE del COMEDERO");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
