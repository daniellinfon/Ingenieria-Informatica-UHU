/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Actividad;
import Modelo.ActividadDAO;
import Modelo.Monitor;
import Modelo.MonitorDAO;
import Vista.PanelMonitor;
import Vista.VistaMensajes;
import Vista.VistaMonitorGrafico;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JDialog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class ControladorMonitor implements ActionListener {

    private Session sesion;
    private final SessionFactory sessionFactory;
    private Transaction tr;
    private final VistaMonitorGrafico vCRUDMonitor;
    private final PanelMonitor pMonitor;
    private final VistaMensajes vMensajes;
    private final MonitorDAO monDAO;
    private final ActividadDAO actDAO;
    private final UtilTablas utablas;
    private String modo;

    public ControladorMonitor(SessionFactory sessionFactory, PanelMonitor pmonitor, UtilTablas tabla) {
        this.sessionFactory = sessionFactory;
        pMonitor = pmonitor;
        vCRUDMonitor = new VistaMonitorGrafico();
        vMensajes = new VistaMensajes();
        monDAO = new MonitorDAO();
        actDAO = new ActividadDAO();
        utablas = tabla;

        addListeners();
    }

    private void addListeners() {

        //Opciones Gestion Monitores
        pMonitor.jButtonNuevoMonitor.addActionListener(this);
        pMonitor.jButtonBajaMonitor.addActionListener(this);
        pMonitor.jButtonActualizacionMonitor.addActionListener(this);

        //Opciones Nuevo monitor
        vCRUDMonitor.jButtonInsertar.addActionListener(this);
        vCRUDMonitor.jButtonCancelar.addActionListener(this);

    }

    @Override
    @SuppressWarnings("empty-statement")
    public void actionPerformed(ActionEvent e) {
        String formatoDni = "\\d{8}[A-Z]";
        String formatoTelefono = "\\d{9}";
        String patronCorreo = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
        String nombre;
        String dni;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaChooser;
        Date fechaActual = new Date();
        String fechaString;
        String codigo;
        String telefono;
        String correo;
        String nick;

        switch (e.getActionCommand()) {
            case "NuevoMonitor":
                sesion = sessionFactory.openSession();
                try {
                    vCRUDMonitor.jTextFieldCodigo.setText(nuevoCodigo());
                    vCRUDMonitor.jButtonInsertar.setText("Insertar");
                    modo = "insertado";
                    inicializarJDialog(vCRUDMonitor);

                } catch (Exception ex) {
                    vMensajes.Mensaje(pMonitor, "error", ex.getMessage());
                } finally {
                    if (sesion != null && sesion.isOpen()) {
                        sesion.close();
                    }
                }
                break;
            case "Insertar":
                nombre = vCRUDMonitor.jTextFieldNombre.getText();
                dni = vCRUDMonitor.jTextFieldDni.getText();
                fechaChooser = vCRUDMonitor.jDateChooserFechaEntrada.getDate();
                telefono = vCRUDMonitor.jTextFieldTelefono.getText();
                correo = vCRUDMonitor.jTextFieldCorreo.getText();
                if (nombre.isEmpty()) {
                    vMensajes.Mensaje(vCRUDMonitor, "error", "El campo 'Nombre' es obligatorio.");
                } else if (dni.isEmpty()) {
                    vMensajes.Mensaje(vCRUDMonitor, "error", "El campo 'DNI' es obligatorio.");
                } else if (!dni.matches(formatoDni)) {
                    vMensajes.Mensaje(vCRUDMonitor, "error", "El formato del 'DNI' no es válido.");
                } else if (fechaChooser == null) {
                    vMensajes.Mensaje(vCRUDMonitor, "error", "El campo 'Fecha de entrada' es obligatorio");
                } else if (fechaChooser.after(fechaActual)) {
                    vMensajes.Mensaje(vCRUDMonitor, "error", "La 'Fecha de Entrada' no puede ser posterior a la fecha actual.");
                } else if (telefono != null && !telefono.matches(formatoTelefono)) {
                    vMensajes.Mensaje(vCRUDMonitor, "error", "El formato del 'Teléfono' no es válido.");
                } else if (correo != null && !correo.matches(patronCorreo)) {
                    vMensajes.Mensaje(vCRUDMonitor, "error", "El formato del 'Correo' no es válido.");
                } else {
                    //Si todo esta correcto 
                    fechaString = formatoFecha.format(fechaChooser);
                    codigo = vCRUDMonitor.jTextFieldCodigo.getText();
                    nick = vCRUDMonitor.jTextFieldNick.getText();
                    Monitor m = new Monitor();

                    sesion = sessionFactory.openSession();
                    tr = sesion.beginTransaction();
                    try {
                        monDAO.AltaMonitor(sesion, m, codigo, nombre, dni, telefono, correo, fechaString, nick);
                        //Refrescar tabla
                        ArrayList<Monitor> lMonitores = monDAO.listaMonitoresHQL(sesion);;
                        utablas.vaciarTabla('M');
                        utablas.rellenarTabla(lMonitores, 'M');
                        tr.commit();
                        vCRUDMonitor.dispose();
                        vMensajes.Mensaje(pMonitor, "info", "El Monitor " + vCRUDMonitor.jTextFieldNombre.getText() + " ha sido " + modo + " con éxito");

                    } catch (SQLException ex) {
                        tr.rollback();
                        vMensajes.Mensaje(vCRUDMonitor, "error", ex.getMessage());
                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                            vaciarCamposInsertar();
                        }
                    }
                }
                break;
            case "Cancelar":
                vaciarCamposInsertar();
                vCRUDMonitor.dispose();
                break;
            case "BajaMonitor":
                boolean existeMonitorActividad = false;
                int fila = pMonitor.jTableMonitores.getSelectedRow();
                if (fila != -1) {
                    try {
                        sesion = sessionFactory.openSession();
                        //si existe alguna actividad de la que es responsable no se podra
                        //borrar a menos que se borre primero dicha actividad o que cambie de monitor responsable
                        String nom = (String) pMonitor.jTableMonitores.getValueAt(fila, 1);
                        ArrayList<Actividad> lacts = actDAO.listaActividadesHQL(sesion);
                        for (Actividad act : lacts) {
                            if (act.getMonitorResponsable().getNombre().equals(nom)) {
                                existeMonitorActividad = true;
                            }
                        }
                        if (!existeMonitorActividad) {
                            if (vMensajes.Dialogo(pMonitor, "¿Seguro que quieres dar de baja a " + pMonitor.jTableMonitores.getValueAt(fila, 1) + "?") == JOptionPane.YES_OPTION) {
                                tr = sesion.beginTransaction();
                                Monitor m = new Monitor();

                                monDAO.BajaMonitor(sesion, (String) pMonitor.jTableMonitores.getValueAt(fila, 0), m);
                                //Refrescar tabla
                                ArrayList<Monitor> lMonitores = monDAO.listaMonitoresHQL(sesion);
                                utablas.vaciarTabla('M');
                                utablas.rellenarTabla(lMonitores, 'M');
                                vMensajes.Mensaje(pMonitor, "info", nom + " ha sido dado de baja con éxito.");
                                tr.commit();
                            }
                        }else{
                            vMensajes.Mensaje(pMonitor, "info", nom + " no puede ser dado de baja ya que existe una actividad de la que es responsable");
                        }
                    } catch (SQLException ex) {
                        vMensajes.Mensaje(pMonitor, "error", ex.getMessage());
                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                } else {
                    vMensajes.Mensaje(pMonitor, "info", "No se ha seleccionado ningún monitor");
                }
                break;
            case "ActualizacionMonitor":
                int fila2 = pMonitor.jTableMonitores.getSelectedRow();
                if (fila2 != -1) {
                    sesion = sessionFactory.openSession();
                    try {
                        codigo = (String) pMonitor.jTableMonitores.getValueAt(fila2, 0);
                        nombre = (String) pMonitor.jTableMonitores.getValueAt(fila2, 1);
                        dni = (String) pMonitor.jTableMonitores.getValueAt(fila2, 2);
                        telefono = (String) pMonitor.jTableMonitores.getValueAt(fila2, 3);
                        correo = (String) pMonitor.jTableMonitores.getValueAt(fila2, 4);
                        fechaString = (String) pMonitor.jTableMonitores.getValueAt(fila2, 5);
                        fechaChooser = formatoFecha.parse(fechaString);
                        nick = (String) pMonitor.jTableMonitores.getValueAt(fila2, 6);

                        vCRUDMonitor.jTextFieldCodigo.setText(codigo);
                        vCRUDMonitor.jTextFieldNombre.setText(nombre);
                        vCRUDMonitor.jTextFieldDni.setText(dni);
                        vCRUDMonitor.jTextFieldTelefono.setText(telefono);
                        vCRUDMonitor.jTextFieldCorreo.setText(correo);
                        vCRUDMonitor.jDateChooserFechaEntrada.setDate(fechaChooser);
                        vCRUDMonitor.jTextFieldNick.setText(nick);
                        vCRUDMonitor.jButtonInsertar.setText("Actualizar");
                        vCRUDMonitor.jButtonInsertar.setActionCommand("Insertar");
                        modo = "actualizado";
                        inicializarJDialog(vCRUDMonitor);

                    } catch (ParseException ex) {
                        vMensajes.Mensaje(pMonitor, "error", ex.getMessage());
                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }

                } else {
                    vMensajes.Mensaje(pMonitor, "info", "No se ha seleccionado ningún monitor");
                }
                break;
        }
    }

    private void inicializarJDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    private String nuevoCodigo() {
        ArrayList<String> valor = monDAO.ultimoMonitor(sesion);
        String codigo = valor.get(0);

        String prefijo = codigo.substring(0, codigo.length() - 3);
        int num = Integer.parseInt(codigo.substring(codigo.length() - 3));
        num++;

        codigo = prefijo + String.format("%03d", num);
        return codigo;
    }

    private void vaciarCamposInsertar() {
        vCRUDMonitor.jTextFieldNombre.setText(null);
        vCRUDMonitor.jTextFieldDni.setText(null);
        vCRUDMonitor.jTextFieldTelefono.setText(null);
        vCRUDMonitor.jTextFieldCorreo.setText(null);
        vCRUDMonitor.jTextFieldNick.setText(null);
        vCRUDMonitor.jDateChooserFechaEntrada.setDate(null);
    }
}
