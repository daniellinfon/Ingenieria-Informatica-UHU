package practica5_pcd;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author usuario
 */
public class Centro {

    private volatile boolean Mlibre = true, Flibre = true, Vlibre = true;
    private volatile int esperaRehabilitacion = 0, esperaSalirMas=0, esperaSalirRehab=0;

    public synchronized String EntraMasaje() throws InterruptedException {

        String donde = null;
        while (!Mlibre && (!Flibre || esperaRehabilitacion > 0)) {
            wait();
        }

        //Entra
        if (Mlibre) {
            Mlibre = false;
            donde = "MASAJISTA";
        } else if (Flibre) {
            Flibre = false;
            donde = "FISIOTERAPEUTA";
        }
        return donde;
    }

    public synchronized void SaleMasaje() throws InterruptedException {
        while (!Vlibre || esperaSalirRehab > esperaSalirMas) {
            esperaSalirMas++;
            wait();
        }
        esperaSalirMas=0;
        if (Vlibre) {
            Mlibre = true;
            Vlibre = false;
            notifyAll();
        }
    }

    public synchronized void EntraRehabilitacion() throws InterruptedException {
        esperaRehabilitacion++;
        while (!Flibre) {
            wait();
        }
        esperaRehabilitacion--;

        if (Flibre) {
            Flibre = false;
        }
    }

    public synchronized void SaleRehabilitacion() throws InterruptedException {
        while (!Vlibre || esperaSalirMas > esperaSalirRehab) {
            esperaSalirRehab++;
            wait();
        }
        esperaSalirRehab=0;

        if (Vlibre) {
            Flibre = true;
            Vlibre = false;
            notifyAll();
        }

    }

    public synchronized void Termina() {
        Vlibre = true;
        notifyAll();
    }

}
