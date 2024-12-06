/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Config.HibernateUtilMariaDB;
import Config.HibernateUtilOracle;
import Vista.VistaMensajes;
import Vista.vistaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hibernate.SessionFactory;

/**
 *
 * @author danie
 */
public class ControladorLogin implements ActionListener {

    private SessionFactory sessionFactory;
    private final VistaMensajes vMensaje;
    private final vistaLogin vLogin;
    private ControladorPrincipal controladorP;

    public ControladorLogin() {
        vMensaje = new VistaMensajes();
        vLogin = new vistaLogin();

        addListeners();

        vLogin.setLocationRelativeTo(null);
        vLogin.setVisible(true);

        vLogin.jComboBox1Servidores.setSelectedIndex(0);
    }

    private void conectarBD() {
        boolean conexion = false;
        while (!conexion) {
            try {
                String server = (String) (vLogin.jComboBox1Servidores.getSelectedItem());
                if (server.equals("Oracle")) {
                    sessionFactory = HibernateUtilOracle.getSessionFactory();
                } else if (server.equals("MariaDB")) {
                    sessionFactory = HibernateUtilMariaDB.getSessionFactory();
                }
               // vMensaje.mensajeConsola("Conexión Correcta con Hibernate");
                conexion = true;
            } catch (ExceptionInInitializerError e) {
                vMensaje.Mensaje(vLogin,"error",e.getMessage());
            }
        }
    }

    private void addListeners() {
        vLogin.jButtonSalirDialogoConexion.addActionListener(this);
        vLogin.jButtonConectar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Conectar":
                conectarBD();
                vLogin.dispose();
                vMensaje.Mensaje(vLogin, "info", "Conexión Correcta con Hibernate" );
                controladorP = new ControladorPrincipal(sessionFactory);
                break;
            case "SalirDialogoConexion":
                vLogin.dispose();
                System.exit(0);
                break;
        }
    }

}
