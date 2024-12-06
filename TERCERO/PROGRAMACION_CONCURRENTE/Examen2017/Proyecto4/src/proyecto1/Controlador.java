/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import org.jcsp.lang.Alternative;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author danie
 */
public class Controlador extends Thread {

    Any2OneChannel cargaPoco;
    Any2OneChannel cargaMucho;
    Any2OneChannel recarga;
    One2OneChannel[] permiso;

    public Controlador(Any2OneChannel cargaPoco, Any2OneChannel cargaMucho, Any2OneChannel recarga, One2OneChannel[] permiso) {
        this.cargaPoco = cargaPoco;
        this.cargaMucho = cargaMucho;
        this.recarga = recarga;
        this.permiso = permiso;
    }

    @Override
    public void run() {

        int minerales = 4, id;
        boolean grandeLibre = true;

        Guard[] guards = new Guard[3];
        guards[0] = cargaPoco.in();
        guards[1] = cargaMucho.in();
        guards[2] = recarga.in();

        final boolean[] preCondition = new boolean[guards.length];

        final Alternative selector = new Alternative(guards);

        while (true) {
            
            preCondition[0] = (minerales > 2 && grandeLibre);
            preCondition[1] = minerales > 0;
            preCondition[2] = true;

            int index = selector.select(preCondition);
            switch (index) {
                case 0:
                    id = (int) cargaMucho.in().read();
                    minerales = minerales - 3;
                    grandeLibre=false;
                    permiso[id].out().write(id);
                    break;
                case 1:
                    id = (int) cargaPoco.in().read();
                    minerales--;
                    permiso[id].out().write(id);
                    break;
                case 2:
                    id = (int) recarga.in().read();
                    Random r = new Random();
                    r.setSeed(System.currentTimeMillis());
                    int recarga = r.nextInt(4) + 2;
                    minerales = minerales + recarga;
                    System.out.println("Cinta recarga: "+recarga);
                    System.out.println("Cantidad de Minerales: " + minerales + " Tm");

            }
        }

    }
}
