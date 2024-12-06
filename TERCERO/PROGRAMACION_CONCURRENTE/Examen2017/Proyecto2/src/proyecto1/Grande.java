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
public class Grande implements Runnable{

    private Monton m;

    public Grande(Monton m) {
        this.m = m;
    }
    
    @Override
    public void run() {
        int id = (int) Thread.currentThread().getId();
        System.out.println("Soy la Cargadora Grande "+id);
        for (int i = 0; i < 7; i++) {
            try {
                m.cargaMucho();
                System.out.println("\t\t\t<---Cargadora "+id+" carga 3 Tm");
            } catch (InterruptedException ex) {
                Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
