/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cliente;

import IRemoto.IRemoto;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class Tarjeta {

    public static void main(String[] args) {
        try {

            Random r1 = new Random(System.currentTimeMillis());
            int id = r1.nextInt(50);
            
            Registry Registro = LocateRegistry.getRegistry("localhost", 2023);
            String[] oferta = Registro.list();

            IRemoto objrem = (IRemoto) Naming.lookup("rmi://localhost:2023/ejemremoto");

            sleep(5000);
            
            System.out.println("Soy el Cliente " + id + " y quiero pagar con tarjeta");
            objrem.entraTarjeta(id);
            System.out.println("Soy el Cliente " + id + " y entro al cajero");
            sleep(r1.nextInt(4000) + 1000);
            System.out.println("Soy el Cliente " + id + " y salgo del cajero");
            objrem.saleTarjeta(id);
            
        } catch (NotBoundException | MalformedURLException | RemoteException | InterruptedException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
