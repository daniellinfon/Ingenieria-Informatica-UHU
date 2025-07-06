/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica6;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Cortado extends Thread {

    private final CanvasCongreso cc;
    Semaphore leche;
    Semaphore cafe;
    Semaphore papelera;
    Semaphore salaLeche;
    Semaphore salaCafe;

    public Cortado(CanvasCongreso cc, Semaphore leche, Semaphore cafe, Semaphore papelera, Semaphore salaLeche, Semaphore salaCafe) {
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
        int id = (int) getId();

        try {

            System.out.println("Soy el asistente " + id + " y quiero un CORTADO");
            
            //Primero va a la sala de la leche a por una dosis
            cc.encolaleche(id, 'C', 0, 0);
            salaLeche.acquire();
            cc.fincolaleche(id, 'C', 0, 0);
            cc.ensalaleche(id, 'C', 0, 0);
            System.out.println("                 ----> Asistente "+getId()+" entra a la Sala de leche");
            leche.acquire();
            System.out.println("                 ----> Asistente "+getId()+" coge una dosis de leche");
            
            //Luego va a la sala de cafe a por 2 dosis
            salaLeche.release();
            cc.finsalaleche(id, 'C', 0, 0);
            System.out.println("                 <---- Asistente "+getId()+" sale de la Sala de leche");
            cc.encolacafe(id, 'C', 1, 0);
            salaCafe.acquire();
            cc.fincolacafe(id, 'C', 1, 0);
            cc.ensalacafe(id, 'C', 1, 0);
            System.out.println("                 ----> Asistente "+getId()+" entra a la Sala de cafe");
            cafe.acquire();
            cc.finsalacafe(id, 'C', 1, 0);
            cc.ensalacafe(id, 'C', 1, 1);
            cafe.acquire();
            System.out.println("                 ----> Asistente "+getId()+" coge dos dosis de cafe");
            
            //Sale de la sala y se dirije a la sala de congreso durante 3segs
            salaCafe.release();
            cc.finsalacafe(id, 'C', 1, 1);
            cc.ensalon(id, 'C', 1, 2);
            System.out.println("                 <---- Asistente "+getId()+" sale de la Sala de cafe");
            sleep((rnd.nextInt(3)+1)*1000);
            cc.finsalon(id, 'C', 1, 2);
            
            //Tira el cafe a la papelera
            cc.encolapapelera(id, 'C', 1, 2);
            papelera.acquire();
            cc.fincolapapelera(id, 'C', 1, 2);
            cc.enpapelera(id, 'C', 1, 2);
            System.out.println("                 ----> Asistente "+getId()+" tira la taza");
            sleep(1000);
            System.out.println("                 <---- Asistente "+getId()+" termina");
            papelera.release();
            cc.finpapelera(id, 'C', 1, 2);
            

        } catch (InterruptedException ex) {
            Logger.getLogger(Cortado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
