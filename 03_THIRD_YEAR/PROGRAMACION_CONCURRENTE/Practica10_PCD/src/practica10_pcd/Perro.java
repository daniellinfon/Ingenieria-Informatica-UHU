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
public class Perro extends Thread{
    private Controlador c;
    private CanvasComedero cc;
    private final int id;
    private final Any2OneChannel entraPerro;
    private final Any2OneChannel salePerro;
    private final One2OneChannel permiso;

    public Perro(Controlador c, CanvasComedero cc, Any2OneChannel entraPerro, Any2OneChannel salePerro, One2OneChannel permiso, int id) {
        this.c = c;
        this.cc = cc;
        this.id = id;
        this.entraPerro = entraPerro;
        this.salePerro = salePerro;
        this.permiso = permiso;
    }

    
    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {
            System.out.println("Soy el Perro "+id);
            cc.encolar(id,'P');
            entraPerro.out().write(id);
            int lee = (int) permiso.in().read();
            cc.desencolar(id,'P');
            cc.enComedero(id, 'P');
            System.out.println(" \t\t----> Perro "+id+" ENTRA al COMEDERO");
            sleep((rnd.nextInt(5)+1)*1000);
            salePerro.out().write(id);
            cc.finComedero(id);
            System.out.println(" \t\t<---- Perro "+id+" SALE del COMEDERO");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Perro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
