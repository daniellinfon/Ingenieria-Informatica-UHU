/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.util.ArrayList;

/**
 *
 * @author danie
 */
public class VistaMonitores {
    
     public void muestraMonitorResp(ArrayList<Object[]> mon) {
        System.out.println("Lista de Monitores");
        System.out.println("************************");
        System.out.println("Actividades\t\tCodigo\t\tNombre\t\tDNI\t\tTelefono\t\tCorreo\t\tFechaEntrada\t\tNick");
        for (Object[] m : mon) {
            System.out.println(m[7] + "\t\t" + m[0] + "\t\t" + m[1] + "\t\t" + m[2] + "\t\t" + m[3] + "\t\t" + m[4] + "\t\t" + m[5] + "\t\t" + m[6]);
        }
    }
}
