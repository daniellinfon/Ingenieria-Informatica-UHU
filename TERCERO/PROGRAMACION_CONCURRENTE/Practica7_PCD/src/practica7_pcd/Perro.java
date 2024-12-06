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
public class Perro extends Thread{
    private Comedero c;
    private CanvasComedero cc;

    public Perro(Comedero c, CanvasComedero cc) {
        this.c = c;
        this.cc = cc;
    }
    
    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int id = (int) getId();
        try {
            System.out.println("Soy el Perro "+getId());
            cc.encolar(id,'P');
            c.entraPerro();
            cc.desencolar(id,'P');
            cc.enComedero(id, 'P');
            System.out.println(" \t\t----> Perro "+getId()+" ENTRA al COMEDERO");
            sleep((rnd.nextInt(3)+1)*1000);
            c.salePerro();
            cc.finComedero(id);
            System.out.println(" \t\t<---- Perro "+getId()+" SALE del COMEDERO");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Perro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
