package practica1;

import java.util.Random;
import practica2.Consumidor;
import practica2.Productor;

/**
 *
 * @author usuario
 */
public class UsaPila {

    public static void main(String[] args) throws Exception {
        //PilaLenta pila = new PilaLenta(20);
        
        //Practica 1
        /*
        Random r = new Random(System.currentTimeMillis());

        for (int i = 0; i < 15; i++) {
            int n = r.nextInt(100);
            try {
                if (n % 2 == 0)//par
                {
                    System.out.println("Apila " + n);
                    pila.Apila(n);
                } else {
                    System.out.println("Desapila " + pila.Desapila()); 
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Contenido de la pila:");
        for (int i = 0; i < pila.GetNum(); i++) {
            System.out.println(pila.Desapila());
        }
        
        Practica 2
        
        Productor p1 = new Productor(pila);
        Productor p2 = new Productor(pila);
        Consumidor r = new Consumidor(pila);
        Thread c1 = new Thread(r);
        Thread c2 = new Thread(r);
        
        p1.start();
        p2.start();
        //p1.join();
       //p2.join();
        c1.start();
        c2.start();
        //c1.join();
        //c2.join();
        */
    }
}
