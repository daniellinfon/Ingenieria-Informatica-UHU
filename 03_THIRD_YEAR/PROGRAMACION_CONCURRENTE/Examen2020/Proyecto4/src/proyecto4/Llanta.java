/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto4;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author danie
 */
public class Llanta implements Runnable{

     private Any2OneChannel entraLlanta;
     private Any2OneChannel saleLlanta;
     private One2OneChannel permiso;
     private int id;

    public Llanta(Any2OneChannel entraLlanta, Any2OneChannel saleLlanta, One2OneChannel permiso, int id) {
        this.entraLlanta = entraLlanta;
        this.saleLlanta = saleLlanta;
        this.permiso = permiso;
        this.id = id;
    }
    
    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {
            System.out.println("Soy la LLANTA "+id);
            entraLlanta.out().write(id);
            permiso.in().read();
            System.out.println("\t\t---> LLANTA "+id+" ENTRA");
            sleep((rnd.nextInt(2)+2)*3000);
            System.out.println("\t\t\t\t<--- LLANTA "+id+" SALE");
           saleLlanta.out().write(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Llanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
