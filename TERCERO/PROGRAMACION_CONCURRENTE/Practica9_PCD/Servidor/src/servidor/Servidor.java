package servidor;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Servidor extends java.awt.Frame {

    /**
     * Creates new form Generador
     */
    public Servidor() {
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException, ExecutionException, RemoteException, IOException {

        Servidor g = new Servidor();
        g.setSize(850, 601);
        g.setLocation(550, 280);
        CanvasCajero cv = new CanvasCajero();
        g.add(cv);
        g.setVisible(true);

        Registry registro = LocateRegistry.createRegistry(2023);
        Cajero c = new Cajero(cv);
        registro.rebind("ejemremoto", c);

        Random aleatorio = new Random();
        aleatorio.setSeed(System.currentTimeMillis());
        
        System.out.println("Servidor Activo");
        System.out.println("Pulsa cualquier tecla para cerrar el servidor:");

        System.in.read();

        System.out.println("Servidor Cerrado");

        sleep(2000);
        System.exit(0);
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
