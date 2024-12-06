/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica6;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Manchado implements Runnable {

    private final CanvasCongreso cc;
    Semaphore leche;
    Semaphore cafe;
    Semaphore papelera;
    Semaphore salaLeche;
    Semaphore salaCafe;

    public Manchado(CanvasCongreso cc, Semaphore leche, Semaphore cafe, Semaphore papelera, Semaphore salaLeche, Semaphore salaCafe) {
        this.cc = cc;
        this.leche = leche;
        this.cafe = cafe;
        this.papelera = papelera;
        this.salaLeche = salaLeche;
        this.salaCafe = salaCafe;
    }


    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int id = (int) Thread.currentThread().getId();

        try {

            System.out.println("Soy el asistente " + id + " y quiero un MANCHADO");
            
            //Primero va a la sala de cafe a por una dosis
            cc.encolacafe(id, 'M', 0, 0);
            salaCafe.acquire();
            cc.fincolacafe(id, 'M', 0,0);
            cc.ensalacafe(id, 'M', 0,0);
            System.out.println("                 ----> Asistente "+id+" entra a la Sala de cafe");
            cafe.acquire();
            System.out.println("                 ----> Asistente "+id+" coge una dosis de cafe");
            
            //Luego va a la sala de leche a por 2 dosis
            salaCafe.release();
            cc.finsalacafe(id, 'M', 0,0);
            System.out.println("                 <---- Asistente "+id+" sale de la Sala de cafe");
            cc.encolaleche(id, 'M', 0,1);
            salaLeche.acquire();
            cc.fincolaleche(id, 'M', 0,1);
            cc.ensalaleche(id, 'M', 0,1);
            System.out.println("                 ----> Asistente "+id+" entra a la Sala de leche");
            leche.acquire();
            cc.finsalaleche(id, 'M', 0,1);
            cc.ensalaleche(id, 'M', 1,1);
            leche.acquire();
            System.out.println("                 ----> Asistente "+id+" coge dos dosis de leche");
            
            //Sale de la sala y se dirije a la sala de congreso durante 3segs
            salaLeche.release();
            cc.finsalaleche(id, 'M', 1,1);
            cc.ensalon(id, 'M', 2,1);
            System.out.println("                 <---- Asistente "+id+" sale de la Sala de leche");
            sleep((rnd.nextInt(3)+1)*1000);
            cc.finsalon(id, 'M', 2,1);
            
            //Tira el cafe a la papelera
            cc.encolapapelera(id, 'M', 2,1);
            papelera.acquire();
            cc.fincolapapelera(id, 'M', 2,1);
            cc.enpapelera(id, 'M', 2,1);
            System.out.println("                 ----> Asistente "+id+" tira la taza");
            sleep(1000);
            System.out.println("                 <---- Asistente "+id+" termina");
            papelera.release();
            cc.finpapelera(id, 'M', 2,1);

        } catch (InterruptedException ex) {
            Logger.getLogger(Manchado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
