/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Actividad;
import Modelo.ActividadDAO;
import Modelo.Monitor;
import Modelo.MonitorDAO;
import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.PanelActividades;
import Vista.VistaActividadesGrafico;
import Vista.VistaMensajes;
import Vista.VistaSociosEnActividades;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author danie
 */
public class ControladorActividades implements ActionListener {

    private Session sesion;
    private final SessionFactory sessionFactory;
    private Transaction tr;
    private final VistaActividadesGrafico vCRUDActividad;
    private final VistaSociosEnActividades vCRUDSociosActividad;
    private final PanelActividades pActividades;
    private final ActividadDAO actDAO;
    private final MonitorDAO monDAO;
    private final SocioDAO socDAO;
    private String modo;
    private String idActividadAltaBaja;
    private final VistaMensajes vMensajes;
    private final UtilTablas uTablas;

    public ControladorActividades(SessionFactory sessionFactory, PanelActividades pActividades, UtilTablas utablas) {
        this.sessionFactory = sessionFactory;
        this.pActividades = pActividades;
        this.uTablas = utablas;
        vMensajes = new VistaMensajes();
        actDAO = new ActividadDAO();
        monDAO = new MonitorDAO();
        socDAO = new SocioDAO();
        vCRUDActividad = new VistaActividadesGrafico();

        vCRUDSociosActividad = new VistaSociosEnActividades();
        this.uTablas.inicializarTablaJDialog(vCRUDSociosActividad);

        addListeners();
    }

    private void addListeners() {

        //Opciones Actividades
        pActividades.jButtonNuevaActividad.addActionListener(this);
        pActividades.jButtonEliminarActividad.addActionListener(this);
        pActividades.jButtonActualizarActividad.addActionListener(this);
        pActividades.jButtonVerInscripciones.addActionListener(this);

        //Opciones Nueva Actividad
        vCRUDActividad.jButtonInsertar.addActionListener(this);
        vCRUDActividad.jButtonCancelar.addActionListener(this);

        //Opciones Incripciones
        vCRUDSociosActividad.jButtonSalir.addActionListener(this);
        vCRUDSociosActividad.jButtonAlta.addActionListener(this);
        vCRUDSociosActividad.jButtonBaja.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String idActividad = null, nombre, monitorRes, precio, descripcion;
        int precioInt, fila;
        switch (e.getActionCommand()) {
            case "NuevaActividad":
            try {
                sesion = sessionFactory.openSession();
                vCRUDActividad.jTextFieldId.setText(nuevoCodigo());
                vCRUDActividad.jButtonInsertar.setText("Insertar");
                insertarComboBox();
                vCRUDActividad.jComboBoxMonitorRes.setSelectedIndex(0);
                modo = "insertado";
                inicializarJDialog(vCRUDActividad);
            } catch (SQLException ex) {
                vMensajes.Mensaje(vCRUDActividad, "error", ex.getMessage());
            } finally {
                if (sesion != null && sesion.isOpen()) {
                    sesion.close();
                    vaciarCamposInsertar();
                }
            }
            break;
            case "Insertar":
                nombre = vCRUDActividad.jTextFieldNombre.getText();
                precio = vCRUDActividad.jTextFieldPrecio.getText();

                if (nombre.isEmpty()) {
                    vMensajes.Mensaje(vCRUDActividad, "error", "El campo 'Nombre' es obligatorio.");
                } else if (precio.isEmpty()) {
                    vMensajes.Mensaje(vCRUDActividad, "error", "El campo 'Precio' es obligatorio.");
                } else if (!esNumero(precio)) {
                    vMensajes.Mensaje(vCRUDActividad, "error", "El formato del campo 'Precio' no es válido.");
                } else {
                    //Si todo esta correcto 
                    try {
                        precioInt = Integer.parseInt(precio);
                        idActividad = vCRUDActividad.jTextFieldId.getText();
                        monitorRes = (String) vCRUDActividad.jComboBoxMonitorRes.getSelectedItem();
                        Monitor mon = MonitorDAO.buscaMonitor(sesion, monitorRes);
                        descripcion = vCRUDActividad.jTextAreaDescripcion.getText();

                        Actividad act = new Actividad();

                        sesion = sessionFactory.openSession();
                        tr = sesion.beginTransaction();

                        actDAO.nuevaActividad(sesion, act, idActividad, nombre, precioInt, mon, descripcion);
                        //Refrescar tabla
                        ArrayList<Actividad> lActividades = actDAO.listaActividadesHQL(sesion);
                        uTablas.vaciarTabla('A');
                        uTablas.rellenarTabla(lActividades, 'A');
                        tr.commit();
                        vCRUDActividad.dispose();
                        vMensajes.Mensaje(pActividades, "info", "La Actividad " + vCRUDActividad.jTextFieldNombre.getText() + " ha sido " + modo + " con éxito");

                    } catch (SQLException ex) {
                        tr.rollback();
                        vMensajes.Mensaje(vCRUDActividad, "error", ex.getMessage());
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
                vCRUDActividad.dispose();
                break;
            case "EliminaActividad":
                fila = pActividades.jTableActividades.getSelectedRow();
                if (fila != -1) {
                    if (vMensajes.Dialogo(pActividades, "¿Seguro que quieres eliminar " + pActividades.jTableActividades.getValueAt(fila, 1) + "?") == JOptionPane.YES_OPTION) {
                        sesion = sessionFactory.openSession();
                        tr = sesion.beginTransaction();
                        try {
                            Actividad act = new Actividad();
                            String nom = (String) pActividades.jTableActividades.getValueAt(fila, 1);
                            actDAO.eliminarActividad(sesion, (String) pActividades.jTableActividades.getValueAt(fila, 0), act);
                            //Refrescar tabla
                            ArrayList<Actividad> lActividades = actDAO.listaActividadesHQL(sesion);
                            uTablas.vaciarTabla('A');
                            uTablas.rellenarTabla(lActividades, 'A');
                            vMensajes.Mensaje(pActividades, "info", nom + " ha sido eliminado con éxito.");
                            tr.commit();
                        } catch (SQLException ex) {
                            tr.rollback();
                            vMensajes.Mensaje(pActividades, "error", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                    }
                } else {
                    vMensajes.Mensaje(pActividades, "info", "No se ha seleccionado ninguna actividad");
                }
                break;
            case "ActualizacionActividad":
                fila = pActividades.jTableActividades.getSelectedRow();
                if (fila != -1) {
                    sesion = sessionFactory.openSession();
                    try {
                        idActividad = (String) pActividades.jTableActividades.getValueAt(fila, 0);
                        nombre = (String) pActividades.jTableActividades.getValueAt(fila, 1);
                        precioInt = (int) pActividades.jTableActividades.getValueAt(fila, 2);
                        precio = String.valueOf(precioInt);
                        monitorRes = (String) pActividades.jTableActividades.getValueAt(fila, 3);
                        descripcion = (String) pActividades.jTableActividades.getValueAt(fila, 4);

                        vCRUDActividad.jTextFieldId.setText(idActividad);
                        vCRUDActividad.jTextFieldNombre.setText(nombre);
                        vCRUDActividad.jTextFieldPrecio.setText(precio);
                        vCRUDActividad.jTextAreaDescripcion.setText(descripcion);
                        insertarComboBox();
                        vCRUDActividad.jComboBoxMonitorRes.setSelectedItem(MonitorDAO.buscaMonitor(sesion, monitorRes).getCodMonitor());
                        vCRUDActividad.jButtonInsertar.setText("Actualizar");
                        vCRUDActividad.jButtonInsertar.setActionCommand("Insertar");
                        modo = "actualizado";
                        inicializarJDialog(vCRUDActividad);

                    } catch (SQLException ex) {
                        vMensajes.Mensaje(vCRUDActividad, "error", ex.getMessage());
                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }

                } else {
                    vMensajes.Mensaje(pActividades, "info", "No se ha seleccionado ninguna actividad");
                }
                break;
            case "VerInscripciones":
                fila = pActividades.jTableActividades.getSelectedRow();
                if (fila != -1) {
                    idActividad = (String) pActividades.jTableActividades.getValueAt(fila, 0);
                    idActividadAltaBaja = idActividad;
                    vCRUDSociosActividad.jLabelSociosInscritosEn.setText("Socios inscritos en " + idActividad);
                    uTablas.dibujarTablaDialog(vCRUDSociosActividad);
                    sesion = sessionFactory.openSession();
                    tr = sesion.beginTransaction();
                    try {
                        Actividad actividad = ActividadDAO.buscaActividad(sesion, idActividad);
                        ArrayList<Socio> lSociosNOActividad = socDAO.listaSociosNOactividad(sesion, idActividad);

                        ArrayList<Socio> lSociosEnActividad = new ArrayList<>();

                        Set<Socio> socios = actividad.getSocios();
                        for (Socio soc : socios) {
                            lSociosEnActividad.add(soc);
                        }

                        uTablas.vaciarTabla('O');
                        uTablas.rellenarTablaInscripciones(lSociosEnActividad, lSociosNOActividad);
                        tr.commit();
                        inicializarJDialog(vCRUDSociosActividad);
                    } catch (SQLException ex) {
                        tr.rollback();
                        vMensajes.Mensaje(vCRUDActividad, "error", ex.getMessage());
                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                } else {
                    vMensajes.Mensaje(pActividades, "info", "No se ha seleccionado ninguna actividad");
                }
                break;
            case "Salir":
                vCRUDSociosActividad.dispose();
                break;
            case "DardeAlta":
                if (vCRUDSociosActividad.jTableSociosActividad.getSelectedRow() != -1) {
                    vMensajes.Mensaje(vCRUDSociosActividad, "aviso", "El socio seleccionado ya está inscrito.");
                } else {
                    fila = vCRUDSociosActividad.jTableSociosNOActividad.getSelectedRow();
                    if (fila != -1) {
                        if (vMensajes.Dialogo(vCRUDSociosActividad, "¿Seguro que quieres dar de alta a " + vCRUDSociosActividad.jTableSociosNOActividad.getValueAt(fila, 1) + "?") == JOptionPane.YES_OPTION) {
                            String numsoc = (String) vCRUDSociosActividad.jTableSociosNOActividad.getValueAt(fila, 0);
                            sesion = sessionFactory.openSession();
                            tr = sesion.beginTransaction();
                            try {
                                Socio socio = SocioDAO.buscaSocio(sesion, numsoc);
                                System.out.println(idActividadAltaBaja);
                                Actividad act = ActividadDAO.buscaActividad(sesion, idActividadAltaBaja);
                                act.altaSocio(socio);

                                //Refrescar tabla
                                ArrayList<Socio> lSociosNOActividad = socDAO.listaSociosNOactividad(sesion, idActividadAltaBaja);
                                lSociosNOActividad.remove(socio);
                                Actividad actividad = ActividadDAO.buscaActividad(sesion, idActividadAltaBaja);
                                ArrayList<Socio> lSociosEnActividad = new ArrayList<>();
                                Set<Socio> socios = actividad.getSocios();
                                for (Socio soc : socios) {
                                    lSociosEnActividad.add(soc);
                                }

                                uTablas.vaciarTabla('O');
                                uTablas.rellenarTablaInscripciones(lSociosEnActividad, lSociosNOActividad);
                                tr.commit();
                                vMensajes.Mensaje(vCRUDSociosActividad, "info", socio.getNombre() + " ha sido dado de alta con éxito.");

                            } catch (SQLException ex) {
                                tr.rollback();
                                vMensajes.Mensaje(vCRUDSociosActividad, "error", ex.getMessage());
                            } finally {
                                if (sesion != null && sesion.isOpen()) {
                                    sesion.close();
                                }
                            }
                        }

                    } else {
                        vMensajes.Mensaje(vCRUDSociosActividad, "info", "No se ha seleccionado ningun socio");
                    }
                }
                break;
            case "DardeBaja":
                if (vCRUDSociosActividad.jTableSociosNOActividad.getSelectedRow() != -1) {
                    vMensajes.Mensaje(vCRUDSociosActividad, "aviso", "El socio seleccionado no está inscrito.");
                } else {
                    fila = vCRUDSociosActividad.jTableSociosActividad.getSelectedRow();
                    if (fila != -1) {
                        if (vMensajes.Dialogo(vCRUDSociosActividad, "¿Seguro que quieres dar de baja a " + vCRUDSociosActividad.jTableSociosActividad.getValueAt(fila, 1) + "?") == JOptionPane.YES_OPTION) {
                            String numsoc = (String) vCRUDSociosActividad.jTableSociosActividad.getValueAt(fila, 0);
                            sesion = sessionFactory.openSession();
                            tr = sesion.beginTransaction();
                            try {
                                Socio socio = SocioDAO.buscaSocio(sesion, numsoc);
                                Actividad act = ActividadDAO.buscaActividad(sesion, idActividadAltaBaja);
                                act.bajaSocio(socio);

                                //Refrescar tabla
                                Actividad actividad = ActividadDAO.buscaActividad(sesion, idActividadAltaBaja);
                                ArrayList<Socio> lSociosEnActividad = new ArrayList<>();
                                Set<Socio> socios = actividad.getSocios();
                                for (Socio soc : socios) {
                                    lSociosEnActividad.add(soc);
                                }
                                ArrayList<Socio> lSociosNOActividad = socDAO.listaSociosNOactividad(sesion, idActividadAltaBaja);
                                lSociosNOActividad.add(socio);
                                uTablas.vaciarTabla('O');
                                uTablas.rellenarTablaInscripciones(lSociosEnActividad, lSociosNOActividad);
                                tr.commit();
                                vMensajes.Mensaje(vCRUDSociosActividad, "info", socio.getNombre() + " ha sido dado de baja con éxito.");

                            } catch (SQLException ex) {
                                tr.rollback();
                                vMensajes.Mensaje(vCRUDSociosActividad, "error", ex.getMessage());
                            } finally {
                                if (sesion != null && sesion.isOpen()) {
                                    sesion.close();
                                }
                            }
                        }
                    } else {
                        vMensajes.Mensaje(vCRUDSociosActividad, "info", "No se ha seleccionado ningun socio");
                    }
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

    private String nuevoCodigo() throws SQLException {
        ArrayList<String> valor = actDAO.ultimaActividad(sesion);
        String codigo = valor.get(0);

        String prefijo = codigo.substring(0, codigo.length() - 2);
        int num = Integer.parseInt(codigo.substring(codigo.length() - 2));
        num++;

        codigo = prefijo + String.format("%02d", num);
        return codigo;
    }

    private void vaciarCamposInsertar() {
        vCRUDActividad.jTextFieldNombre.setText(null);
        vCRUDActividad.jTextFieldPrecio.setText(null);
        vCRUDActividad.jTextAreaDescripcion.setText(null);
    }

    private boolean esNumero(String cadena) {
        // Utilizar una expresión regular para verificar si la cadena solo contiene números
        return cadena.matches("\\d*\\.?\\d+");
    }

    private void insertarComboBox() throws SQLException {
        vCRUDActividad.jComboBoxMonitorRes.removeAllItems();
        ArrayList<Monitor> lMonitores = monDAO.listaMonitoresHQL(sesion);
        for (Monitor monitor : lMonitores) {
            vCRUDActividad.jComboBoxMonitorRes.addItem(monitor.getCodMonitor());
        }
    }

}
