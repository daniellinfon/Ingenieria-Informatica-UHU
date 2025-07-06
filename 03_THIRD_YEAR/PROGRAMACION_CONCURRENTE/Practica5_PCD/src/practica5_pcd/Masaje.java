package practica5_pcd;


import practica5_pcd.Centro;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class Masaje extends Thread{
    
    private Centro c;
    private CanvasCentro cc;

    public Masaje(Centro c, CanvasCentro cc) {
        this.c = c;
        this.cc = cc;
    }
    
    
    
    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int id = (int) getId();
        try {
            System.out.println("Soy el cliente "+getId() + " y quiero un MASAJE");
            cc.encolar(id,'m');
            String donde= c.EntraMasaje();
            cc.desencolar(id,'m');
            if (donde.equals("MASAJISTA")) {
                cc.enMasaje(id);
            } else {
                cc.enFisio(id, 'm');
            }
            System.out.println("                 ----> cliente "+getId()+" ATENDIDO por "+donde);
            
            sleep((rnd.nextInt(3)+2)*1000);
            System.out.println("                 <---- cliente "+getId()+ " TERMINA el MASAJE");
            if (donde.equals("MASAJISTA")) {
                c.SaleMasaje();
                cc.finMasaje();
            } else {
                c.SaleRehabilitacion();
                cc.finRehab();
            }
            System.out.println("                 ----> cliente "+getId()+" ENTRA al VESTUARIO");
            cc.enVestuario(id, 'm');
            sleep(5000);
            System.out.println("                 ----> cliente "+getId()+" SALE del VESTUARIO");
            c.Termina();
            cc.finVestuario();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Masaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
