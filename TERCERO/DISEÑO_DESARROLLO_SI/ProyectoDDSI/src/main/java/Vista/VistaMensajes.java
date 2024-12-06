package Vista;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class VistaMensajes {
/*
    public void mensajeConsola(String texto) {
        System.out.println("--------------------------");
        System.out.println(texto);
        System.out.println("--------------------------");
    }

    public void mensajeConsola(String texto, String error) {
        System.out.println("--------------------------");
        System.out.println(texto);
        System.out.println(error);
        System.out.println("--------------------------");
    }

    public void menuControlador() {
        System.out.println("Daniel Linfon Ye Liu     Proyecto DDSI      2023/2024");
        System.out.println("******************************************************");
        System.out.println("1. Información de los socios (HQL).");
        System.out.println("2. Información de los socios (SQL Nativo).");
        System.out.println("3. Información de los socios (Consulta nombrada).");
        System.out.println("4. Nombre y teléfono de los socios.");
        System.out.println("5. Nombre y categoría de los socios.");
        System.out.println("6. Responsable de una actividad.");
        System.out.println("7. Socios de una actividad.");
        System.out.println("8. Alta de un socio.");
        System.out.println("9. Baja de un socio.");
        System.out.println("10. Actualización de la Categoria de un socio.");
        System.out.println("11. Inscripción de un socio en una actividad.");
        System.out.println("12. Baja de un socio de una actividad.");
        System.out.println("13. Listado de socios inscritos en una actividad.");
        System.out.println("14. Listado de actividades de un socio.");
        System.out.println("0. Salir.");
        System.out.println("******************************************************");
        System.out.println("Elije la opción: ");
    }

    public void mensajeCorto(String texto) {
        System.out.println(texto);
    }
*/
    public void Mensaje(Component C, String tipoMensaje, String texto) {
        switch (tipoMensaje) {
            case "info":
                JOptionPane.showMessageDialog(C, texto, "Información",JOptionPane.INFORMATION_MESSAGE);
                break;
            case "error":
                JOptionPane.showMessageDialog(C,  texto, "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "aviso":
                JOptionPane.showMessageDialog(C,  texto, "Aviso", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    public int Dialogo(Component C, String texto) {
        int opcion = JOptionPane.showConfirmDialog(C, texto,
                "Atención", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
        return opcion;
    }
}
