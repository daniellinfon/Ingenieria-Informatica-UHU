/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Actividad;
import Modelo.Socio;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author danie
 */
public class VistaSocios {
    
    /**
     *
     * @param soc
     */
    public void muestraSocios(ArrayList<Socio> soc) {
        System.out.println("Lista de Socios");
        System.out.println("************************");
        System.out.println("Numero\t\tNombre\t\tDNI\t\tFecha de Nacimiento\t\tTeléfono\t\tCorreo\t\tFecha de Entrada\t\tCategoria\t\tActivades");
        for (Socio s : soc) {
            System.out.println(s.getNumeroSocio() + "\t\t" + s.getNombre() + "\t\t" + s.getDni() + "\t\t" + s.getFechaNacimiento() + "\t\t" + s.getTelefono() + "\t\t" + s.getCorreo() + "\t\t" + s.getFechaEntrada() + "\t\t" + s.getCategoria() + "\t\t" + s.getActividades());
        }
    }

    public void muestraNombreTelefonoSocios(ArrayList<Object[]> soc) {
        System.out.println("Lista de Socios");
        System.out.println("************************");
        System.out.println("Nombre\t\tTeléfono");
        for (Object[] s : soc) {
            System.out.println(s[0] + "\t\t" + s[1]);
        }
    }

    public void muestraNombreCategoriaSocios(ArrayList<Object[]> soc) {
        System.out.println("Lista de Socios");
        System.out.println("************************");
        System.out.println("Nombre\t\tCategoria");
        for (Object[] s : soc) {
            System.out.println(s[0] + "\t\t" + s[1]);
        }
    }

    public void muestraMonitorResp(ArrayList<Object[]> mon) {
        System.out.println("Lista de Monitores");
        System.out.println("************************");
        System.out.println("Actividades\t\tCodigo\t\tNombre\t\tDNI\t\tTelefono\t\tCorreo\t\tFechaEntrada\t\tNick");
        for (Object[] m : mon) {
            System.out.println(m[7] + "\t\t" + m[0] + "\t\t" + m[1] + "\t\t" + m[2] + "\t\t" + m[3] + "\t\t" + m[4] + "\t\t" + m[5] + "\t\t" + m[6]);
        }
    }

    public void muestraActividadSocios(ArrayList<String> soc) {
        System.out.println("Lista de Socios");
        System.out.println("************************");
        System.out.println("Nombre");
        for (int i = 0; i < soc.size(); i++) {
            String elemento = soc.get(i);
            System.out.println(elemento);
        }
    }

    public void muestraListaSociosActividad(List<Actividad> act) {
        for (Actividad actividad : act) {
            Set<Socio> socios = actividad.getSocios();
            for (Socio soc : socios) {
               System.out.println(actividad.getIdActividad() + "\t" + soc.getNombre()+ "\t" + soc.getTelefono());
            }
        }
    }

    public void muestraListaActividadesSocio(List<Socio> soc) {
        for (Socio socios : soc) {
            Set<Actividad> actividades = socios.getActividades();
            for (Actividad actividad : actividades) {
                System.out.println(socios.getNumeroSocio()+"\t"+actividad.getNombre());
            }
        }
    }

    
}
