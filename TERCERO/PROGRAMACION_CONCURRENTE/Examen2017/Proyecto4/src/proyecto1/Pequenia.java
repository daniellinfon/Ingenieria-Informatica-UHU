/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author danie
 */
public class Pequenia extends Thread{
    
    
    private Any2OneChannel cargaPoco;
    private final One2OneChannel permiso;
    private int id;

    public Pequenia(Any2OneChannel cargaPoco, One2OneChannel permiso, int id) {
        this.cargaPoco = cargaPoco;
        this.permiso = permiso;
        this.id = id;
    }
    
    @Override
    public void run() {
        System.out.println("Soy la Cargadora Pequenia "+id);
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        
        for (int i = 0; i < 10; i++) {
            try {
                cargaPoco.out().write(id);
                permiso.in().read();
                System.out.println("\t\t\t<---Cargadora "+id+" carga 1 Tm");
                sleep((r.nextInt(3) + 1) * 1000);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        System.out.println("Soy la Cargadora Pequenia "+id+" y he terminado.");
    }
    
}
