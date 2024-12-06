/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto3;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class Parachoque extends Thread{
    private Tanque t;

    public Parachoque(Tanque t) {
        this.t = t;
    }
    
    @Override
    public void run() {
        try {
            int id = (int) Thread.currentThread().getId();
            System.out.println("Soy el PARACHOQUE "+id);
            t.entraPC();
            System.out.println("\t\t--->  PARACHOQUE "+id+" ENTRA");
            sleep(7000);
            System.out.println("\t\t\t\t<---  PARACHOQUE "+id+" SALE");
            t.salePC();
        } catch (InterruptedException ex) {
            Logger.getLogger(Parachoque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
