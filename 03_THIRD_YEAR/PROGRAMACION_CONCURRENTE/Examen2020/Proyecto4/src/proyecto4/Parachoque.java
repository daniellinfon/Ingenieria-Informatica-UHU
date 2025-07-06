/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto4;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author danie
 */
public class Parachoque extends Thread {

    private Any2OneChannel entraPC;
    private Any2OneChannel salePC;
    private One2OneChannel permiso;
    private int id;

    public Parachoque(Any2OneChannel entraPC, Any2OneChannel salePC, One2OneChannel permiso, int id) {
        this.entraPC = entraPC;
        this.salePC = salePC;
        this.permiso = permiso;
        this.id = id;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Soy el PARACHOQUE " + id);
            entraPC.out().write(id);
            permiso.in().read();
            System.out.println("\t\t--->  PARACHOQUE " + id + " ENTRA");
            sleep(7000);
            System.out.println("\t\t\t\t<---  PARACHOQUE " + id + " SALE");
            salePC.out().write(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Parachoque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
