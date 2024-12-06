/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class Pequenia extends Thread{
    
    private Monton m;

    public Pequenia(Monton m) {
        this.m = m;
    }
    
    
    @Override
    public void run() {
        int id=(int) getId();
        System.out.println("Soy la Cargadora Pequenia "+id);
        
        for (int i = 0; i < 10; i++) {
            try {
                m.cargaPoco();
                System.out.println("\t\t\t<---Cargadora "+id+" carga 1 Tm");
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        System.out.println("Soy la Cargadora Pequenia "+id+" y he terminado.");
    }
    
}
