/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2AnyChannel;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author danie
 */
public class Grande implements Runnable {

    private Any2OneChannel cargaMucho;
    private final One2OneChannel permiso;
    private int id;

    public Grande(Any2OneChannel cargaMucho, One2OneChannel permiso, int id) {
        this.cargaMucho = cargaMucho;
        this.permiso = permiso;
        this.id = id;
    }


    @Override
    public void run() {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        System.out.println("Soy la Cargadora Grande " + id);
        for (int i = 0; i < 7; i++) {
            try {
                cargaMucho.out().write(id);
                System.out.println(id);
                permiso.in().read();
                System.out.println("\t\t\t<---Cargadora " + id + " carga 3 Tm");
                sleep((r.nextInt(3) + 2) * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
