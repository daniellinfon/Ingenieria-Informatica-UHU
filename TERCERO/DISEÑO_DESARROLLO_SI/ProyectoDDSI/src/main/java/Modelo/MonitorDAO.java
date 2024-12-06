/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author danie
 */
public class MonitorDAO {

    public ArrayList<Monitor> listaMonitoresHQL(Session sesion) throws SQLException{
        Query consulta = sesion.createQuery("SELECT m FROM Monitor m", Monitor.class);
        ArrayList<Monitor> monitores = (ArrayList<Monitor>) consulta.getResultList();

        return monitores;
    }
    
    public ArrayList<Monitor> listaMonitoresSQLNativo(Session sesion)throws SQLException{
        Query consulta = sesion.createNativeQuery("SELECT * FROM MONITOR m", Monitor.class);
        ArrayList<Monitor> monitores = (ArrayList<Monitor>) consulta.getResultList();

        return monitores;
    }

    public ArrayList<Monitor> listaMonitoresNombrada(Session sesion)throws SQLException{

        Query consulta = sesion.createNamedQuery("Monitor.findAll", Monitor.class);
        ArrayList<Monitor> monitores = (ArrayList<Monitor>) consulta.getResultList();

        return monitores;
    }

    //Obtener el código del último monitor registrado
    public ArrayList<String> ultimoMonitor(Session sesion) {
        Query consulta = sesion.createNativeQuery("SELECT MAX(codMonitor) AS max_valor FROM MONITOR");
        ArrayList<String> monitores = (ArrayList<String>) consulta.getResultList();
        return monitores;
    }

    //Método para añadir un monitor
    public void AltaMonitor(Session sesion, Monitor mon, String cod, String nombre, String dni, String telefono, String correo, String fecha, String nick) throws SQLException {
        mon.setCodMonitor(cod);
        mon.setNombre(nombre);
        mon.setDni(dni);
        mon.setTelefono(telefono);
        mon.setCorreo(correo);
        mon.setFechaEntrada(fecha);
        mon.setNick(nick);
        sesion.saveOrUpdate(mon);
    }

    //Método para eliminar un monitor
    public void BajaMonitor(Session sesion, String cod, Monitor m) throws SQLException {
        m = sesion.get(Monitor.class, cod);
        sesion.delete(m);
    }

    //Buscar un monitor dado su código
    public static Monitor buscaMonitor(Session sesion, String cod) throws SQLException{
        return sesion.get(Monitor.class, cod);
    }
}
