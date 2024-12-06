/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Actividad;
import Modelo.Monitor;
import Modelo.Socio;
import Vista.PanelActividades;
import Vista.PanelMonitor;
import Vista.PanelSocio;
import Vista.VistaActualizarCategoria;
import Vista.VistaSociosEnActividades;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class UtilTablas {

    private DefaultTableModel modeloTablaMonitores;
    private DefaultTableModel modeloTablaSocios;
    private DefaultTableModel modeloTablaActividades;
    private DefaultTableModel modeloTablaSociosActividades;
    private DefaultTableModel modeloTablaSociosNOActividades;
    private DefaultTableModel modeloTablaActualizarCategoria;

    public void inicializarTabla(JPanel panel) {
        if (panel.getClass().equals(PanelMonitor.class)) {
            modeloTablaMonitores = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            ((PanelMonitor) panel).jTableMonitores.setModel(modeloTablaMonitores);
        } else if (panel.getClass().equals(PanelSocio.class)) {
            modeloTablaSocios = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            ((PanelSocio) panel).jTableSocios.setModel(modeloTablaSocios);
        } else {
            modeloTablaActividades = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            ((PanelActividades) panel).jTableActividades.setModel(modeloTablaActividades);
        }
    }

    public void inicializarTablaJDialog(JDialog dialog) {
        if (dialog.getClass().equals(VistaSociosEnActividades.class)) {
            modeloTablaSociosActividades = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosActividad.setModel(modeloTablaSociosActividades);

            modeloTablaSociosNOActividades = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosNOActividad.setModel(modeloTablaSociosNOActividades);
        }else if(dialog.getClass().equals(VistaActualizarCategoria.class)){
             modeloTablaActualizarCategoria = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            ((Vista.VistaActualizarCategoria) dialog).jTableActualizarCategoria.setModel(modeloTablaActualizarCategoria);
        }
    }

    public void dibujarTabla(JPanel panel) {
        if (panel.getClass().equals(PanelMonitor.class)) {
            String[] columnasTabla = {
                "Código", "Nombre", "DNI", "Teléfono", "Correo", "Fecha de Incorporación", "Nick"};
            modeloTablaMonitores.setColumnIdentifiers(columnasTabla);

            //Para no permitir el redimensionamiento de las columnas con el raton
            ((PanelMonitor) panel).jTableMonitores.getTableHeader().setReorderingAllowed(false);
            ((PanelMonitor) panel).jTableMonitores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

            //Fijar el ancho columnas
            ((PanelMonitor) panel).jTableMonitores.getColumnModel().getColumn(0).setPreferredWidth(40);
            ((PanelMonitor) panel).jTableMonitores.getColumnModel().getColumn(1).setPreferredWidth(240);
            ((PanelMonitor) panel).jTableMonitores.getColumnModel().getColumn(2).setPreferredWidth(70);
            ((PanelMonitor) panel).jTableMonitores.getColumnModel().getColumn(3).setPreferredWidth(70);
            ((PanelMonitor) panel).jTableMonitores.getColumnModel().getColumn(4).setPreferredWidth(200);
            ((PanelMonitor) panel).jTableMonitores.getColumnModel().getColumn(5).setPreferredWidth(130);
            ((PanelMonitor) panel).jTableMonitores.getColumnModel().getColumn(6).setPreferredWidth(60);
        } else if (panel.getClass().equals(PanelSocio.class)) {
            String[] columnasTabla = {
                "Socio", "Nombre", "DNI", "Fecha de Nacimiento", "Teléfono", "Correo", "Fecha de Alta", "Categoría"};
            modeloTablaSocios.setColumnIdentifiers(columnasTabla);

            //Para no permitir el redimensionamiento de las columnas con el raton
            ((PanelSocio) panel).jTableSocios.getTableHeader().setReorderingAllowed(false);
            ((PanelSocio) panel).jTableSocios.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

            //Fijar el ancho columnas
            ((PanelSocio) panel).jTableSocios.getColumnModel().getColumn(0).setPreferredWidth(40);
            ((PanelSocio) panel).jTableSocios.getColumnModel().getColumn(1).setPreferredWidth(240);
            ((PanelSocio) panel).jTableSocios.getColumnModel().getColumn(2).setPreferredWidth(70);
            ((PanelSocio) panel).jTableSocios.getColumnModel().getColumn(3).setPreferredWidth(130);
            ((PanelSocio) panel).jTableSocios.getColumnModel().getColumn(4).setPreferredWidth(75);
            ((PanelSocio) panel).jTableSocios.getColumnModel().getColumn(5).setPreferredWidth(200);
            ((PanelSocio) panel).jTableSocios.getColumnModel().getColumn(6).setPreferredWidth(130);
            ((PanelSocio) panel).jTableSocios.getColumnModel().getColumn(7).setPreferredWidth(70);
        } else {
            String[] columnasTabla = {
                "Actividad", "Nombre", "Precio Base Mes", "Monitor Responsable", "Descripcion"};
            modeloTablaActividades.setColumnIdentifiers(columnasTabla);

            //Para no permitir el redimensionamiento de las columnas con el raton
            ((PanelActividades) panel).jTableActividades.getTableHeader().setReorderingAllowed(false);
            ((PanelActividades) panel).jTableActividades.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

            //Fijar el ancho columnas
            ((PanelActividades) panel).jTableActividades.getColumnModel().getColumn(0).setPreferredWidth(40);
            ((PanelActividades) panel).jTableActividades.getColumnModel().getColumn(1).setPreferredWidth(140);
            ((PanelActividades) panel).jTableActividades.getColumnModel().getColumn(2).setPreferredWidth(70);
            ((PanelActividades) panel).jTableActividades.getColumnModel().getColumn(3).setPreferredWidth(80);
            ((PanelActividades) panel).jTableActividades.getColumnModel().getColumn(4).setPreferredWidth(300);
        }
    }

    public void dibujarTablaDialog(JDialog dialog) {
        if (dialog.getClass().equals(VistaSociosEnActividades.class)) {
            String[] columnasTabla = {
                "Socio", "Nombre", "DNI"};
            modeloTablaSociosActividades.setColumnIdentifiers(columnasTabla);
            modeloTablaSociosNOActividades.setColumnIdentifiers(columnasTabla);

            //Para no permitir el redimensionamiento de las columnas con el raton
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosActividad.getTableHeader().setReorderingAllowed(false);
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosActividad.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosNOActividad.getTableHeader().setReorderingAllowed(false);
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosNOActividad.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

            //Fijar el ancho columnas
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosActividad.getColumnModel().getColumn(0).setPreferredWidth(60);
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosActividad.getColumnModel().getColumn(1).setPreferredWidth(240);
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosActividad.getColumnModel().getColumn(2).setPreferredWidth(90);
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosNOActividad.getColumnModel().getColumn(0).setPreferredWidth(60);
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosNOActividad.getColumnModel().getColumn(1).setPreferredWidth(240);
            ((Vista.VistaSociosEnActividades) dialog).jTableSociosNOActividad.getColumnModel().getColumn(2).setPreferredWidth(90);
        }else if(dialog.getClass().equals(VistaActualizarCategoria.class)){
             String[] columnasTabla = {
                "Nombre", "DNI", "Categoría"};
            modeloTablaActualizarCategoria.setColumnIdentifiers(columnasTabla);
            
            //Para no permitir el redimensionamiento de las columnas con el raton
            ((VistaActualizarCategoria) dialog).jTableActualizarCategoria.getTableHeader().setReorderingAllowed(false);
            ((VistaActualizarCategoria) dialog).jTableActualizarCategoria.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            
            //Fijar el ancho columnas
            ((VistaActualizarCategoria) dialog).jTableActualizarCategoria.getColumnModel().getColumn(0).setPreferredWidth(240);
            ((VistaActualizarCategoria) dialog).jTableActualizarCategoria.getColumnModel().getColumn(1).setPreferredWidth(90);
            ((VistaActualizarCategoria) dialog).jTableActualizarCategoria.getColumnModel().getColumn(2).setPreferredWidth(70);
            System.out.println("fff");
        }
    }

    public void rellenarTabla(ArrayList obj, char tipo) {
        switch (tipo) {
            case 'M': {
                ArrayList<Monitor> monitores = new ArrayList<>();
                for (Object elemento : obj) {
                    Monitor mon = (Monitor) elemento;
                    monitores.add(mon);
                }
                Object[] fila = new Object[7];
                for (Monitor monitor : monitores) {
                    fila[0] = monitor.getCodMonitor();
                    fila[1] = monitor.getNombre();
                    fila[2] = monitor.getDni();
                    fila[3] = monitor.getTelefono();
                    fila[4] = monitor.getCorreo();
                    fila[5] = monitor.getFechaEntrada();
                    fila[6] = monitor.getNick();
                    modeloTablaMonitores.addRow(fila);

                }
                break;
            }
            case 'S': {
                ArrayList<Socio> socios = new ArrayList<>();
                for (Object elemento : obj) {
                    Socio soc = (Socio) elemento;
                    socios.add(soc);
                }
                Object[] fila = new Object[8];
                for (Socio socio : socios) {
                    fila[0] = socio.getNumeroSocio();
                    fila[1] = socio.getNombre();
                    fila[2] = socio.getDni();
                    fila[3] = socio.getFechaNacimiento();
                    fila[4] = socio.getTelefono();
                    fila[5] = socio.getCorreo();
                    fila[6] = socio.getFechaEntrada();
                    fila[7] = socio.getCategoria();

                    modeloTablaSocios.addRow(fila);
                }
                break;
            }
            
            case 'C':{
                ArrayList<Socio> socios = new ArrayList<>();
                for (Object elemento : obj) {
                    Socio soc = (Socio) elemento;
                    socios.add(soc);
                }
                Object[] fila = new Object[3];
                for (Socio socio : socios) {
                    fila[0] = socio.getNombre();
                    fila[1] = socio.getDni();
                    fila[2] = socio.getCategoria();

                    modeloTablaActualizarCategoria.addRow(fila);
                }
                break;
            }

            default: {
                ArrayList<Actividad> actividades = new ArrayList<>();
                for (Object elemento : obj) {
                    Actividad act = (Actividad) elemento;
                    actividades.add(act);
                }
                Object[] fila = new Object[5];
                for (Actividad actividad : actividades) {
                    fila[0] = actividad.getIdActividad();
                    fila[1] = actividad.getNombre();
                    fila[2] = actividad.getPrecioBaseMes();
                    fila[3] = actividad.getMonitorResponsable().getCodMonitor();
                    fila[4] = actividad.getDescripcion();

                    modeloTablaActividades.addRow(fila);
                }
                break;
            }
        }
    }

    public void rellenarTablaInscripciones(ArrayList SociosSi, ArrayList SociosNo) {
        ArrayList<Socio> socios = new ArrayList<>();
        for (Object elemento : SociosSi) {
            Socio soc = (Socio) elemento;
            socios.add(soc);
        }
        Object[] fila = new Object[3];
        for (Socio socio : socios) {
            fila[0] = socio.getNumeroSocio();
            fila[1] = socio.getNombre();
            fila[2] = socio.getDni();

            modeloTablaSociosActividades.addRow(fila);
        }

        ArrayList<Socio> noSocios = new ArrayList<>();
          for (Object elemento2 : SociosNo) {
            Socio soc = (Socio) elemento2;
            noSocios.add(soc);
        }
        Object[] fila2 = new Object[3];
        for (Socio socio : noSocios) {
            fila2[0] = socio.getNumeroSocio();
            fila2[1] = socio.getNombre();
            fila2[2] = socio.getDni();

            modeloTablaSociosNOActividades.addRow(fila2);
        }
        
    }

    public void vaciarTabla(char tipo) {
        switch (tipo) {
            case 'M':
                while (modeloTablaMonitores.getRowCount() > 0) {
                    modeloTablaMonitores.removeRow(0);
                }
                break;
            case 'S':
                while (modeloTablaSocios.getRowCount() > 0) {
                    modeloTablaSocios.removeRow(0);
                }
                break;
            case 'O':
                while (modeloTablaSociosActividades.getRowCount() > 0) {
                    modeloTablaSociosActividades.removeRow(0);
                }
                while (modeloTablaSociosNOActividades.getRowCount() > 0) {
                    modeloTablaSociosNOActividades.removeRow(0);
                }
                break;
            case 'C':
                 while (modeloTablaActualizarCategoria.getRowCount() > 0) {
                    modeloTablaActualizarCategoria.removeRow(0);
                }
                break;
            default:
                while (modeloTablaActividades.getRowCount() > 0) {
                    modeloTablaActividades.removeRow(0);
                }
                break;
        }
    }
}
