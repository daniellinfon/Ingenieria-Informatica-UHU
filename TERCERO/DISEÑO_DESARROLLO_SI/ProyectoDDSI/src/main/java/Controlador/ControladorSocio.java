/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Actividad;
import Modelo.ActividadDAO;
import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.PanelSocio;
import Vista.VistaMensajes;
import Vista.VistaSocioGrafico;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author danie
 */
public class ControladorSocio implements ActionListener {

    private Session sesion;
    private final SessionFactory sessionFactory;
    private Transaction tr;
    private final VistaSocioGrafico vCRUDSocio;
    private final PanelSocio pSocio;
    private final SocioDAO socDAO;
    private final ActividadDAO actDAO;
    private String modo;
    private final VistaMensajes vMensajes;
    private final UtilTablas utablas;

    public ControladorSocio(SessionFactory sessionFactory, PanelSocio psoc, UtilTablas tabla) {
        this.sessionFactory = sessionFactory;
        pSocio = psoc;
        vCRUDSocio = new VistaSocioGrafico();
        socDAO = new SocioDAO();
        utablas = tabla;
        vMensajes = new VistaMensajes();
        actDAO = new ActividadDAO();

        vCRUDSocio.jComboBoxCategoria.setSelectedIndex(0);

        addListeners();
    }

    private void addListeners() {

        //Opciones Socios
        pSocio.jButtonNuevoSocio.addActionListener(this);
        pSocio.jButtonBajaSocio.addActionListener(this);
        pSocio.jButtonActualizacionSocio.addActionListener(this);
        pSocio.jButtonFiltro.addActionListener(this);

        //Opciones Nuevo socio
        vCRUDSocio.jButtonInsertar.addActionListener(this);
        vCRUDSocio.jButtonCancelar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String formatoDni = "\\d{8}[A-Z]";
        String formatoTelefono = "\\d{9}";
        String patronCorreo = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
        String nombre;
        String dni;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaAlta;
        Date fechaNac;
        Date fechaActual = new Date();
        String fechaAltaString;
        String fechaNacString;
        String numsoc;
        String telefono;
        String correo;
        String catstring;
        char catchar;

        switch (e.getActionCommand()) {
            case "NuevoSocio":
                sesion = sessionFactory.openSession();
                try {
                    vCRUDSocio.jTextFieldNumSocio.setText(nuevoCodigo());
                    vCRUDSocio.jButtonInsertar.setText("Insertar");
                    modo = "insertado";
                    inicializarJDialog(vCRUDSocio);

                } catch (Exception ex) {
                    vMensajes.Mensaje(pSocio, "error", ex.getMessage());
                } finally {
                    if (sesion != null && sesion.isOpen()) {
                        sesion.close();
                    }
                }
                break;
            case "Insertar":
                nombre = vCRUDSocio.jTextFieldNombre.getText();
                dni = vCRUDSocio.jTextFieldDni.getText();
                fechaAlta = vCRUDSocio.jDateChooserFechaAlta.getDate();
                fechaNac = vCRUDSocio.jDateChooserFechaNac.getDate();
                telefono = vCRUDSocio.jTextFieldTelefono.getText();
                correo = vCRUDSocio.jTextFieldCorreo.getText();
                LocalDate fechaNacLocal = fechaNac.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                LocalDate fechaActLocal = fechaActual.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                Period periodo = Period.between(fechaNacLocal, fechaActLocal);

                if (nombre.isEmpty()) {
                    vMensajes.Mensaje(vCRUDSocio, "error", "El campo 'Nombre' es obligatorio.");
                } else if (dni.isEmpty()) {
                    vMensajes.Mensaje(vCRUDSocio, "error", "El campo 'DNI' es obligatorio.");
                } else if (!dni.matches(formatoDni)) {
                    vMensajes.Mensaje(vCRUDSocio, "error", "El formato del 'DNI' no es válido.");
                } else if (fechaAlta == null) {
                    vMensajes.Mensaje(vCRUDSocio, "error", "El campo 'Fecha de Entrada' es obligatorio");
                } else if (fechaNac == null) {
                    vMensajes.Mensaje(vCRUDSocio, "error", "El campo 'Fecha de Nacimiento' es obligatorio");
                } else if (fechaAlta.after(fechaActual)) {
                    vMensajes.Mensaje(vCRUDSocio, "error", "La 'Fecha de Entrada' no puede ser posterior a la fecha actual.");
                } else if (fechaNac != null && fechaNac.after(fechaActual)) {
                    vMensajes.Mensaje(vCRUDSocio, "error", "La 'Fecha de Nacimiento' no puede ser posterior a la fecha actual.");
                } else if (periodo.getYears() < 18) {
                    vMensajes.Mensaje(vCRUDSocio, "error", "El socio debe ser mayor de edad.");
                } else if (telefono != null && !telefono.matches(formatoTelefono)) {
                    vMensajes.Mensaje(vCRUDSocio, "error", "El formato del 'Teléfono' no es válido.");
                } else if (correo != null && !correo.matches(patronCorreo)) {
                    vMensajes.Mensaje(vCRUDSocio, "error", "El formato del 'Correo' no es válido.");
                } else {
                    //Si todo esta correcto 
                    fechaAltaString = formatoFecha.format(fechaAlta);

                    if (fechaNac != null) {
                        fechaNacString = formatoFecha.format(fechaNac);
                    } else {
                        fechaNacString = null;
                    }
                    numsoc = vCRUDSocio.jTextFieldNumSocio.getText();

                    catstring = (String) vCRUDSocio.jComboBoxCategoria.getSelectedItem();
                    catchar = catstring.charAt(0);
                    Socio soc = new Socio();

                    sesion = sessionFactory.openSession();
                    tr = sesion.beginTransaction();
                    try {
                        socDAO.AltaSocio(sesion, soc, numsoc, nombre, dni, fechaNacString, telefono, correo, fechaAltaString, catchar);
                        //Refrescar tabla
                        ArrayList<Socio> lSocios = socDAO.listaSociosNombrada(sesion);
                        utablas.vaciarTabla('S');
                        utablas.rellenarTabla(lSocios, 'S');
                        tr.commit();
                        vCRUDSocio.dispose();
                        vMensajes.Mensaje(pSocio, "info", "El Socio " + vCRUDSocio.jTextFieldNombre.getText() + " ha sido " + modo + " con éxito");

                    } catch (SQLException ex) {
                        tr.rollback();
                        vMensajes.Mensaje(vCRUDSocio, "error", ex.getMessage());
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
                vCRUDSocio.dispose();
                break;
            case "BajaSocio":
                int fila = pSocio.jTableSocios.getSelectedRow();
                if (fila != -1) {
                    if (vMensajes.Dialogo(pSocio, "¿Seguro que quieres dar de baja a " + pSocio.jTableSocios.getValueAt(fila, 1) + "?") == JOptionPane.YES_OPTION) {
                        sesion = sessionFactory.openSession();
                        tr = sesion.beginTransaction();
                        try {
                            //Antes de eliminar al socio, lo damos de baja de todas las actividades a las 
                            //que estaba incrito
                            Socio s = new Socio();
                            String nom = (String) pSocio.jTableSocios.getValueAt(fila, 1);
                            ArrayList<Actividad> lacts = actDAO.listaActividadesHQL(sesion);
                            for (Actividad act : lacts) {
                                for (Socio soc : act.getSocios()) {
                                    if(nom.equals(soc.getNombre())){
                                        act.bajaSocio(soc);
                                    }
                                }
                            }
                            socDAO.BajaSocio(sesion, (String) pSocio.jTableSocios.getValueAt(fila, 0), s);
                            //Refrescar tabla
                            ArrayList<Socio> lSocios = socDAO.listaSociosHQL(sesion);
                            utablas.vaciarTabla('S');
                            utablas.rellenarTabla(lSocios, 'S');
                            vMensajes.Mensaje(pSocio, "info", nom + " ha sido dado de baja con éxito.");
                            tr.commit();
                        } catch (SQLException ex) {
                            tr.rollback();
                            vMensajes.Mensaje(pSocio, "error", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                    }
                } else {
                    vMensajes.Mensaje(pSocio, "info", "No se ha seleccionado ningún socio");
                }
                break;
            case "ActualizacionSocio":
                int fila2 = pSocio.jTableSocios.getSelectedRow();
                if (fila2 != -1) {
                    sesion = sessionFactory.openSession();
                    try {
                        numsoc = (String) pSocio.jTableSocios.getValueAt(fila2, 0);
                        nombre = (String) pSocio.jTableSocios.getValueAt(fila2, 1);
                        dni = (String) pSocio.jTableSocios.getValueAt(fila2, 2);
                        fechaNacString = (String) pSocio.jTableSocios.getValueAt(fila2, 3);
                        if (fechaNacString != null) {
                            fechaNac = formatoFecha.parse(fechaNacString);
                        } else {
                            fechaNac = null;
                        }
                        telefono = (String) pSocio.jTableSocios.getValueAt(fila2, 4);
                        correo = (String) pSocio.jTableSocios.getValueAt(fila2, 5);
                        fechaAltaString = (String) pSocio.jTableSocios.getValueAt(fila2, 6);
                        fechaAlta = formatoFecha.parse(fechaAltaString);
                        catchar = (char) pSocio.jTableSocios.getValueAt(fila2, 7);
                        catstring = Character.toString(catchar);

                        vCRUDSocio.jTextFieldNumSocio.setText(numsoc);
                        vCRUDSocio.jTextFieldNombre.setText(nombre);
                        vCRUDSocio.jTextFieldDni.setText(dni);
                        vCRUDSocio.jTextFieldTelefono.setText(telefono);
                        vCRUDSocio.jTextFieldCorreo.setText(correo);
                        vCRUDSocio.jDateChooserFechaNac.setDate(fechaNac);
                        vCRUDSocio.jDateChooserFechaAlta.setDate(fechaAlta);
                        vCRUDSocio.jComboBoxCategoria.setSelectedItem(catstring);
                        vCRUDSocio.jButtonInsertar.setText("Actualizar");
                        vCRUDSocio.jButtonInsertar.setActionCommand("Insertar");
                        modo = "actualizado";
                        inicializarJDialog(vCRUDSocio);

                    } catch (ParseException ex) {
                        vMensajes.Mensaje(pSocio, "error", ex.getMessage());
                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }

                } else {
                    vMensajes.Mensaje(pSocio, "info", "No se ha seleccionado ningún monitor");
                }
                break;
            case "filtro":
                String busqueda = pSocio.jTextFieldBuscador.getText();
                int tipoBusqueda = pSocio.jComboBoxFiltro.getSelectedIndex();
                sesion = sessionFactory.openSession();

                if (tipoBusqueda == 0) {
                    try {
                        ArrayList<Socio> lSocios = socDAO.listaSociosHQL(sesion);
                        utablas.vaciarTabla('S');
                        utablas.rellenarTabla(lSocios, 'S');
                    } catch (SQLException ex) {
                        vMensajes.Mensaje(pSocio, "error", ex.getMessage());
                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                } else if (tipoBusqueda != 0 && !busqueda.isEmpty()) {
                    try {
                        ArrayList<Socio> lSocios = socDAO.listaSociosFiltro(sesion, tipoBusqueda, busqueda);
                        utablas.vaciarTabla('S');
                        utablas.rellenarTabla(lSocios, 'S');
                    } catch (SQLException ex) {
                        vMensajes.Mensaje(pSocio, "error", ex.getMessage());
                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                } else {
                    vMensajes.Mensaje(pSocio, "info", "El filtro está vacío ");
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
        ArrayList<String> valor = socDAO.ultimoSocio(sesion);
        String codigo = valor.get(0);

        String prefijo = codigo.substring(0, codigo.length() - 3);
        int num = Integer.parseInt(codigo.substring(codigo.length() - 3));
        num++;

        codigo = prefijo + String.format("%03d", num);
        return codigo;
    }

    private void vaciarCamposInsertar() {
        vCRUDSocio.jTextFieldNombre.setText(null);
        vCRUDSocio.jTextFieldDni.setText(null);
        vCRUDSocio.jTextFieldTelefono.setText(null);
        vCRUDSocio.jTextFieldCorreo.setText(null);
        vCRUDSocio.jDateChooserFechaNac.setDate(null);
        vCRUDSocio.jDateChooserFechaAlta.setDate(null);
        vCRUDSocio.jComboBoxCategoria.setSelectedItem("A");
    }
}
