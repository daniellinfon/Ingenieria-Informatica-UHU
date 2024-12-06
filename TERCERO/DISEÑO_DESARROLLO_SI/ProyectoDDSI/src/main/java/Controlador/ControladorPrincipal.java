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
import Vista.PanelMonitor;
import Vista.PanelPrincipal;
import Vista.PanelSocio;
import Vista.VistaActualizarCategoria;
import Vista.VistaCuotaActividad;
import Vista.VistaMensajes;
import Vista.VistaMonitores;
import Vista.VistaPrincipal;
import Vista.VistaSocios;
import java.awt.CardLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author danie
 */
class ControladorPrincipal implements ActionListener {

    private Session sesion;
    private final SessionFactory sessionFactory;
    private Transaction tr;
    private final VistaMensajes vMensajes;
    private final VistaSocios vSocios;
    private final VistaMonitores vMonitores;
    private final VistaPrincipal vPrincipal;
    private final SocioDAO socioDAO;
    private final MonitorDAO monitorDAO;
    private final ActividadDAO actividadDAO;
    private final PanelMonitor pMonitor;
    private final PanelSocio pSocio;
    private final PanelActividades pActividad;
    private final PanelPrincipal pPrincipal;
    private final UtilTablas uTablas;
    private final ControladorMonitor cMonitor;
    private final ControladorSocio cSocio;
    private final ControladorActividades cActividad;

    private final VistaActualizarCategoria vActualizarCat;
    private final VistaCuotaActividad vCuota;

    public ControladorPrincipal(SessionFactory sesionFactory) {

        this.sessionFactory = sesionFactory;
        socioDAO = new SocioDAO();
        monitorDAO = new MonitorDAO();
        actividadDAO = new ActividadDAO();
        vMensajes = new VistaMensajes();
        vSocios = new VistaSocios();
        vMonitores = new VistaMonitores();
        vPrincipal = new VistaPrincipal();
        uTablas = new UtilTablas();
        vCuota = new VistaCuotaActividad();

        pPrincipal = new PanelPrincipal();

        pSocio = new PanelSocio();
        uTablas.inicializarTabla(pSocio);

        pMonitor = new PanelMonitor();
        uTablas.inicializarTabla(pMonitor);

        pActividad = new PanelActividades();
        uTablas.inicializarTabla(pActividad);

        vActualizarCat = new VistaActualizarCategoria();
        uTablas.inicializarTablaJDialog(vActualizarCat);

        cMonitor = new ControladorMonitor(sessionFactory, pMonitor, uTablas);
        cSocio = new ControladorSocio(sessionFactory, pSocio, uTablas);
        cActividad = new ControladorActividades(sessionFactory, pActividad, uTablas);

        addListeners();

        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.getContentPane().setLayout(new CardLayout());
        vPrincipal.add(pPrincipal);
        vPrincipal.add(pMonitor);
        vPrincipal.add(pSocio);
        vPrincipal.add(pActividad);

        vPrincipal.setVisible(true);
        pPrincipal.setVisible(true);
        pMonitor.setVisible(false);
        pSocio.setVisible(false);
        pActividad.setVisible(false);

    }

    private void addListeners() {

        vPrincipal.jMenuItemCasa.addActionListener(this);
        vPrincipal.jMenuItemGestionMonitor.addActionListener(this);
        vPrincipal.jMenuItemSalirAplicacion.addActionListener(this);
        vPrincipal.jMenuIGestionSocios.addActionListener(this);
        vPrincipal.jMenuItemGestionActividades.addActionListener(this);
        vPrincipal.jMenuItemActualizacionCat.addActionListener(this);
        vPrincipal.jMenuItemCuotaActividad.addActionListener(this);

        vActualizarCat.jButtonSubir.addActionListener(this);
        vActualizarCat.jButtonBajar.addActionListener(this);

        vCuota.jButtonVerCuota.addActionListener(this);
    }

    private void muestraPanel(JPanel panel) {
        pPrincipal.setVisible(false);
        pMonitor.setVisible(false);
        pSocio.setVisible(false);
        pActividad.setVisible(false);
        panel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Home":
                muestraPanel(pPrincipal);
                break;
            case "GestionMonitores":
                muestraPanel(pMonitor);
                uTablas.dibujarTabla(pMonitor);
                sesion = sessionFactory.openSession();
                tr = sesion.beginTransaction();
                try {
                    ArrayList<Monitor> lMonitores = monitorDAO.listaMonitoresHQL(sesion);
                    uTablas.vaciarTabla('M');
                    uTablas.rellenarTabla(lMonitores, 'M');
                    tr.commit();
                } catch (SQLException ex) {
                    tr.rollback();
                    vMensajes.Mensaje(vPrincipal, "error", ex.getMessage());
                } finally {
                    if (sesion != null && sesion.isOpen()) {
                        sesion.close();
                    }
                }
                break;

            case "GestionSocios":
                muestraPanel(pSocio);
                pSocio.jButtonNuevoSocio.setText("Nuevo Socio");
                pSocio.jButtonBajaSocio.setText("Baja Socio");
                pSocio.jButtonActualizacionSocio.setText("Actualización Socio");
                pSocio.jComboBoxFiltro.setSelectedIndex(0);
                pSocio.jTextFieldBuscador.setText(null);
                uTablas.dibujarTabla(pSocio);
                sesion = sessionFactory.openSession();
                try {
                    ArrayList<Socio> lSocios = socioDAO.listaSociosHQL(sesion);
                    uTablas.vaciarTabla('S');
                    uTablas.rellenarTabla(lSocios, 'S');
                } catch (SQLException ex) {
                    vMensajes.Mensaje(vPrincipal, "error", ex.getMessage());
                } finally {
                    if (sesion != null && sesion.isOpen()) {
                        sesion.close();
                    }
                }
                break;

            case "GestionActividades":
                muestraPanel(pActividad);
                uTablas.dibujarTabla(pActividad);
                sesion = sessionFactory.openSession();
                tr = sesion.beginTransaction();
                try {
                    ArrayList<Actividad> lActividad = actividadDAO.listaActividadesHQL(sesion);
                    uTablas.vaciarTabla('A');
                    uTablas.rellenarTabla(lActividad, 'A');
                    tr.commit();
                } catch (SQLException ex) {
                    tr.rollback();
                    vMensajes.Mensaje(vPrincipal, "error", ex.getMessage());

                } finally {
                    if (sesion != null && sesion.isOpen()) {
                        sesion.close();
                    }
                }
                break;
            case "ActualizacionCat":
                uTablas.dibujarTablaDialog(vActualizarCat);
                sesion = sessionFactory.openSession();
                tr = sesion.beginTransaction();
                try {
                    ArrayList<Socio> lSocios = socioDAO.listaSociosHQL(sesion);
                    uTablas.vaciarTabla('C');
                    uTablas.rellenarTabla(lSocios, 'C');
                    tr.commit();
                    inicializarJDialog(vActualizarCat);
                } catch (SQLException ex) {
                    tr.rollback();
                    vMensajes.Mensaje(vPrincipal, "error", ex.getMessage());

                } finally {
                    if (sesion != null && sesion.isOpen()) {
                        sesion.close();
                    }
                }
                break;
            case "Subir":
                sesion = sessionFactory.openSession();
                tr = sesion.beginTransaction();
                if (vMensajes.Dialogo(vActualizarCat, "¿Seguro que desea incrementar la categoria a todos los socios?") == JOptionPane.YES_OPTION) {
                    try {
                        ArrayList<Socio> lSocios = socioDAO.listaSociosHQL(sesion);
                        socioDAO.actualizaCategoria(sesion, lSocios, 0);
                        uTablas.vaciarTabla('C');
                        uTablas.rellenarTabla(lSocios, 'C');
                        tr.commit();
                    } catch (SQLException ex) {
                        tr.rollback();
                        vMensajes.Mensaje(vPrincipal, "error", ex.getMessage());

                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                }
                break;
            case "Bajar":
                sesion = sessionFactory.openSession();
                tr = sesion.beginTransaction();
                if (vMensajes.Dialogo(vActualizarCat, "¿Seguro que desea decrementar la categoria a todos los socios?") == JOptionPane.YES_OPTION) {
                    try {
                        ArrayList<Socio> lSocios = socioDAO.listaSociosHQL(sesion);
                        socioDAO.actualizaCategoria(sesion, lSocios, 1);
                        uTablas.vaciarTabla('C');
                        uTablas.rellenarTabla(lSocios, 'C');
                        tr.commit();
                    } catch (SQLException ex) {
                        tr.rollback();
                        vMensajes.Mensaje(vPrincipal, "error", ex.getMessage());

                    } finally {
                        if (sesion != null && sesion.isOpen()) {
                            sesion.close();
                        }
                    }
                }
                break;

            case "CuotaActividad":
                inicializarJDialog(vCuota);
                break;

            case "VerCuota":
                sesion = sessionFactory.openSession();
                String codigo = vCuota.jTextFieldCodigo.getText();

                try {
                    Actividad act = ActividadDAO.buscaActividad(sesion, codigo);
                    if (act == null) {
                        vMensajes.Mensaje(vCuota, "error", "Código Incorrecto");
                    } else {
                        
                        Monitor m = act.getMonitorResponsable();
                        Set<Socio> socios = act.getSocios();
                        int cuotaMensualDescuento = actividadDAO.CuotaMensual(sesion, act, socios);
                        int cuotaMensual = act.getPrecioBaseMes() * socios.size();

                        vCuota.jLabelMonitor.setText(m.getNombre());
                        vCuota.jLabelNumSocs.setText(String.valueOf(socios.size()));
                        vCuota.jLabelPrecioBase.setText(String.valueOf(act.getPrecioBaseMes()));
                        vCuota.jLabelCuotaMensual.setText(String.valueOf(cuotaMensual));
                        vCuota.jLabelCuotaMensualDescuento.setText(String.valueOf(cuotaMensualDescuento));
                   }

                } catch (SQLException ex) {
                    vMensajes.Mensaje(vCuota, "error", ex.getMessage());

                } finally {
                    if (sesion != null && sesion.isOpen()) {
                        sesion.close();
                    }
                }
                break;

            case "SalirAplicacion":
                vPrincipal.dispose();
                System.exit(0);
                break;
        }
    }

    private void inicializarJDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }
}

//PRACTICA 3 (CONSOLA)
/* int op;
        try (Scanner sc = new Scanner(System.in)) {
            do {

                sesion = sesionFactory.openSession();
                tr = sesion.beginTransaction();

                vMensajes.menuControlador();
                op = sc.nextInt();
                switch (op) {
                    case 1 -> {
                        try {
                            ArrayList<Socio> lsocio = socioDAO.listaSociosHQL(sesion);
                            vSocios.muestraSocios(lsocio);
                            tr.commit();
                        } catch (Exception ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición de socios", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                    }

                    case 2 -> {
                        try {
                            ArrayList<Socio> lsocio = socioDAO.listaSociosSQLNativo(sesion);
                            vSocios.muestraSocios(lsocio);
                            tr.commit();
                        } catch (Exception ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición de socios", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                    }

                    case 3 -> {
                        try {
                            ArrayList<Socio> lsocio = socioDAO.listaSociosNombrada(sesion);
                            vSocios.muestraSocios(lsocio);
                            tr.commit();
                        } catch (Exception ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición de socios", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                    }

                    case 4 -> {
                        try {
                            ArrayList<Object[]> lsocio = socioDAO.listaNombreTelefonoSocios(sesion);
                            System.out.println("siji2");
                            vSocios.muestraNombreTelefonoSocios(lsocio);
                            tr.commit();
                        } catch (Exception ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición de socios", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                    }

                    case 5 -> {
                        try {
                            vMensajes.mensajeCorto("Introduce la categoria: ");
                            char cat = sc.next().charAt(0);
                            ArrayList<Object[]> lsocio = socioDAO.listaNombreCategoriaSocios(sesion, cat);
                            vSocios.muestraNombreCategoriaSocios(lsocio);
                            tr.commit();
                        } catch (Exception ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición de socios", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }

                    }

                    case 6 -> {
                        try {
                            vMensajes.mensajeCorto("Introduce el codigo de la actividad: ");
                            String cod = sc.next();
                            ArrayList<Object[]> lmonitor = monitorDAO.listaMonitorResponsable(sesion, cod);
                            vMonitores.muestraMonitorResp(lmonitor);
                            tr.commit();
                        } catch (Exception ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                    }

                    case 7 -> {
                        try {
                            vMensajes.mensajeCorto("Introduce el codigo de la actividad: ");
                            String cod = sc.next();
                            ArrayList<String> lsocio = socioDAO.listaActividadesSocio(sesion, cod);
                            vSocios.muestraActividadSocios(lsocio);
                            tr.commit();
                        } catch (Exception ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                    }

                    case 8 -> {
                        try {
                            vMensajes.mensajeCorto("Introduce el numero del socio: ");
                            String cod = sc.next();
                            Socio socio = new Socio();
                            socioDAO.AltaSocio(sesion, socio, cod);
                            vMensajes.mensajeConsola("Socio " + socio.getNumeroSocio() + " dado de alta con exito.");
                            tr.commit();
                        } catch (SQLException ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición: ", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }

                    }
                    case 9 -> {
                        try {
                            vMensajes.mensajeCorto("Introduce el numero del socio: ");
                            String cod = sc.next();
                            Socio socio = new Socio();
                            socioDAO.BajaSocio(sesion, cod, socio);
                            vMensajes.mensajeConsola("Socio " + cod + "dado de baja con exito.");
                            tr.commit();
                        } catch (SQLException ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición: ", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }

                    }
                    case 10 -> {
                        try {
                            vMensajes.mensajeCorto("Introduce el numero del socio: ");
                            String numsoc = sc.next();
                            Socio socio = sesion.get(Socio.class, numsoc);

                            if (socio != null) {
                                vMensajes.mensajeCorto("Introduce la categoria: ");
                                char cat = sc.next().charAt(0);
                                socioDAO.actualizaCategoria(sesion, numsoc, cat, socio);
                                tr.commit();
                                vMensajes.mensajeConsola("El Socio "+socio.getNumeroSocio()+" ha actualizado su categoria a "+socio.getCategoria()+" con exito.");

                            } else {
                                throw new SQLException("No existe un socio con dicho numero");
                            }

                        } catch (SQLException ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición: ", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                    }
                    case 11 -> {
                        try {
                            vMensajes.mensajeCorto("Introduce el numero del socio: ");
                            String numsoc = sc.next();
                            Socio socio = sesion.get(Socio.class, numsoc);

                            if (socio != null) {
                                vMensajes.mensajeCorto("Introduce el codigo de la actividad: ");
                                String cod = sc.next();
                                Actividad actividad = sesion.get(Actividad.class, cod);
                                if (actividad != null) {
                                    actividad.altaSocio(socio);
                                    sesion.saveOrUpdate(actividad);
                                    tr.commit();
                                    vMensajes.mensajeConsola("El Socio "+socio.getNumeroSocio()+" ha sido inscrito en la Actividad "+actividad.getIdActividad()+" con exito.");
                                } else {
                                    throw new SQLException("No existe una Actividad con dicho codigo");
                                }

                            } else {
                                throw new SQLException("No existe un Socio con dicho numero");
                            }

                        } catch (SQLException ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición: ", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                    }

                    case 12 -> {
                        try {
                            vMensajes.mensajeCorto("Introduce el numero del socio: ");
                            String numsoc = sc.next();
                            Socio socio = sesion.get(Socio.class, numsoc);

                            if (socio != null) {
                                vMensajes.mensajeCorto("Introduce el codigo de la actividad: ");
                                String cod = sc.next();
                                Actividad actividad = sesion.get(Actividad.class, cod);
                                if (actividad != null) {
                                    actividad.bajaSocio(socio);
                                    sesion.saveOrUpdate(actividad);
                                    tr.commit();
                                    vMensajes.mensajeConsola("El Socio "+socio.getNumeroSocio()+" ha sido dado de baja en la Actividad "+actividad.getIdActividad()+" con exito.");
                                } else {
                                    throw new SQLException("No existe una Actividad con dicho codigo");
                                }

                            } else {
                                throw new SQLException("No existe un Socio con dicho numero");
                            }

                        } catch (SQLException ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición: ", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }

                    }
                    case 13 -> {
                        try {
                            vMensajes.mensajeCorto("Introduce el codigo de la actividad: ");
                            String cod = sc.next();
                            Actividad actividad = sesion.get(Actividad.class, cod);
                            if (actividad != null) {
                                vSocios.muestraListaSociosActividad(actividadDAO.actividadSocios(sesion,cod));
                                tr.commit();
                            } else {
                                throw new SQLException("No existe una Actividad con dicho codigo");
                            }

                        } catch (SQLException ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición: ", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }

                    }

                    case 14 -> {
                         try {
                            vMensajes.mensajeCorto("Introduce el numero del socio: ");
                            String numsoc = sc.next();
                            Socio socio = sesion.get(Socio.class, numsoc);
                            if (socio != null) {
                                vSocios.muestraListaActividadesSocio(socioDAO.socioActividad(sesion, numsoc));
                                tr.commit();
                            } else {
                                throw new SQLException("No existe el socio indicado");
                            }

                        } catch (SQLException ex) {
                            tr.rollback();
                            vMensajes.mensajeConsola("Error en la petición: ", ex.getMessage());
                        } finally {
                            if (sesion != null && sesion.isOpen()) {
                                sesion.close();
                            }
                        }
                        
                    }

                    case 0 -> {
                        //Desconectar BD
                        sesionFactory.close();
                        vMensajes.mensajeConsola("Desconexion Correcta de la BD.");
                    }

                    default ->
                        vMensajes.mensajeCorto("Opción no válida");

                }
                vMensajes.mensajeCorto("\nPresiona una tecla para continuar...");
                sc.next();

            } while (op != 0);
        }*/
