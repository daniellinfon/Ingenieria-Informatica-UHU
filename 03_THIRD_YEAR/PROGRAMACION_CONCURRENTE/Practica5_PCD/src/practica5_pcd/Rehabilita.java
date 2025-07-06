package practica5_pcd;

import practica5_pcd.Centro;
import static java.lang.Thread.sleep;
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
public class Rehabilita implements Runnable {

    private Centro c;
    private CanvasCentro cc;

    public Rehabilita(Centro c, CanvasCentro cc) {
        this.c = c;
        this.cc = cc;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int id = (int) Thread.currentThread().getId();

        try {
            cc.encolar(id, 'r');
            System.out.println("Soy el cliente " + Thread.currentThread().getId() + " y quiero hacer una REHABILITACION");
            c.EntraRehabilitacion();
            System.out.println("                 ----> cliente " + Thread.currentThread().getId() + " ATENDIDO por FISIOTERAPEUTA");
            cc.desencolar(id, 'r');
            cc.enFisio(id, 'r');
            sleep((rnd.nextInt(3) + 2) * 1000);
            System.out.println("                 <---- cliente " + Thread.currentThread().getId() + " TERMINA la REHABILITACION");
            c.SaleRehabilitacion();
            cc.finRehab();
            System.out.println("                 ----> cliente " + Thread.currentThread().getId() + " ENTRA al VESTUARIO");
            cc.enVestuario(id, 'r');
            sleep(5000);
            System.out.println("                 <---- cliente " + Thread.currentThread().getId() + " SALE del VESTUARIO");
            c.Termina();
            cc.finVestuario();

        } catch (InterruptedException ex) {
            Logger.getLogger(Rehabilita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
