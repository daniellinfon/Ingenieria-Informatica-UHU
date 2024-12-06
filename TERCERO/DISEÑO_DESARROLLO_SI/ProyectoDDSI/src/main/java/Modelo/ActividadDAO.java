/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author danie
 */
public class ActividadDAO {

    public ArrayList<Actividad> listaActividadesHQL(Session sesion) throws SQLException {
        Query consulta = sesion.createQuery("SELECT a FROM Actividad a", Actividad.class);
        ArrayList<Actividad> acts = (ArrayList<Actividad>) consulta.getResultList();

        return acts;
    }

    public ArrayList<Actividad> listaActividadesSQLNativo(Session sesion) throws SQLException {
        Query consulta = sesion.createQuery("SELECT * FROM ACTIVIDAD a", Actividad.class);
        ArrayList<Actividad> acts = (ArrayList<Actividad>) consulta.getResultList();

        return acts;
    }

    public ArrayList<Actividad> listaActividadesNombrada(Session sesion) throws SQLException {
        Query consulta = sesion.createQuery("Actividad.findAll", Actividad.class);
        ArrayList<Actividad> acts = (ArrayList<Actividad>) consulta.getResultList();

        return acts;
    }

    //Obtener el ID de la ultima actividad registrada
    public ArrayList<String> ultimaActividad(Session sesion) throws SQLException {
        Query consulta = sesion.createNativeQuery("SELECT MAX(idActividad) AS max_valor FROM ACTIVIDAD");
        ArrayList<String> actividades = (ArrayList<String>) consulta.getResultList();
        return actividades;
    }

    //Método para insertar una nueva actividad
    public void nuevaActividad(Session sesion, Actividad act, String idActividad, String nombre, int precio, Monitor monitorRes, String descripcion) throws SQLException {
        act.setIdActividad(idActividad);
        act.setNombre(nombre);
        act.setPrecioBaseMes(precio);
        act.setMonitorResponsable(monitorRes);
        act.setDescripcion(descripcion);
        sesion.saveOrUpdate(act);
    }

    //Método para eliminar una nueva actividad
    public void eliminarActividad(Session sesion, String idActividad, Actividad act) throws SQLException {
        act = sesion.get(Actividad.class, idActividad);
        sesion.delete(act);
    }

    //Buscar una actividad dado su ID
    public static Actividad buscaActividad(Session sesion, String cod) throws SQLException {
        return sesion.get(Actividad.class, cod);
    }

    public int CuotaMensual(Session sesion, Actividad act, Set<Socio> socios) throws SQLException {
        int cuota = 0;
        for (Socio socio : socios) {
            char cat = socio.getCategoria();
            int precioBaseMes = act.getPrecioBaseMes();;
            switch (cat) {
                case 'A':
                    cuota += precioBaseMes;
                    break;
                case 'B':
                    cuota += precioBaseMes*0.9;
                    break;
                case 'C':
                    cuota += precioBaseMes*0.8;
                    break;
                case 'D':
                    cuota += precioBaseMes*0.7;
                    break;
                case 'E':
                    cuota += precioBaseMes*0.6;
                    break;
            }
        }
        return cuota;
    }
}
