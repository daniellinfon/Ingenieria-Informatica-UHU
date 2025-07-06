package Aplicacion;

import Controlador.ControladorLogin;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
/**
 *
 * @author danie
 */
public class ProyectoDDSI {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Mensaje de error");
        }
        ControladorLogin cLogin = new ControladorLogin();
    }
}
