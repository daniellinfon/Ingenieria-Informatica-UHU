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
public class SocioDAO {

    /* private boolean ExisteSocio(Session sesion, String numsoc) throws SQLException {

        Socio s = sesion.get(Socio.class, numsoc);

        return s != null;

    }
     */
    public ArrayList<Socio> listaSociosHQL(Session sesion) throws SQLException {
        Query consulta = sesion.createQuery("SELECT s FROM Socio s", Socio.class);
        ArrayList<Socio> socios = (ArrayList<Socio>) consulta.getResultList();

        return socios;
    }

    public ArrayList<Socio> listaSociosSQLNativo(Session sesion) throws SQLException {
        Query consulta = sesion.createNativeQuery("SELECT * FROM SOCIO s", Socio.class);
        ArrayList<Socio> socios = (ArrayList<Socio>) consulta.getResultList();

        return socios;
    }

    public ArrayList<Socio> listaSociosNombrada(Session sesion) throws SQLException {

        Query consulta = sesion.createNamedQuery("Socio.findAll", Socio.class);
        ArrayList<Socio> socios = (ArrayList<Socio>) consulta.getResultList();

        return socios;
    }

    /*
    public ArrayList<Object[]> listaNombreTelefonoSocios(Session sesion) throws Exception {
        Query consulta = sesion.createQuery("SELECT s.nombre, s.telefono FROM Socio s");
        ArrayList<Object[]> socios = (ArrayList<Object[]>) consulta.getResultList();
        return socios;
    }
     */
    //Devuelve los socios que cumplen con los criterios del filtro
    public ArrayList<Socio> listaSociosFiltro(Session sesion, int tipo, String filtro) throws SQLException {
        Query consulta = null;
        switch (tipo) {
            case 0:
                consulta = sesion.createQuery("SELECT s FROM Socio s", Socio.class);
                break;
            case 1:
                consulta = sesion.createNativeQuery("SELECT * FROM SOCIO s WHERE s.nombre LIKE :filtro", Socio.class).setParameter("filtro", "%" + filtro + "%");
                break;
            case 2:
                consulta = sesion.createNativeQuery("SELECT * FROM SOCIO s WHERE s.dni LIKE :filtro", Socio.class).setParameter("filtro", "%" + filtro + "%");
                break;
            case 3:
                consulta = sesion.createNativeQuery("SELECT * FROM SOCIO s WHERE s.categoria =:filtro", Socio.class).setParameter("filtro", filtro);
                break;
            case 4:
                consulta = sesion.createNativeQuery("SELECT * FROM SOCIO s WHERE s.telefono LIKE :filtro", Socio.class).setParameter("filtro", "%" + filtro + "%");
                break;
        }

        ArrayList<Socio> socios = (ArrayList<Socio>) consulta.getResultList();

        return socios;
    }

    //Devuelve los socios que realizan una actividad cuyo ID se pasa por parametro
    public ArrayList<String> listaActividadesSocio(Session sesion, String idActividad) throws Exception {
        Query consulta = sesion.createNativeQuery("SELECT s.nombre FROM SOCIO s INNER JOIN REALIZA r ON s.numeroSocio = r.numeroSocio INNER JOIN ACTIVIDAD a ON r.idActividad = a.idActividad WHERE a.idActividad =:act").setParameter("act", idActividad);
        ArrayList<String> socios = (ArrayList<String>) consulta.getResultList();
        return socios;
    }

    //Método para añadir un Socio
    public void AltaSocio(Session sesion, Socio soc, String numsoc, String nombre, String dni, String fechaNac, String telefono, String correo, String fechaAlta, char cat) {
        soc.setNumeroSocio(numsoc);
        soc.setNombre(nombre);
        soc.setDni(dni);
        soc.setFechaNacimiento(fechaNac);
        soc.setTelefono(telefono);
        soc.setCorreo(correo);
        soc.setFechaEntrada(fechaAlta);
        soc.setCategoria(cat);
        sesion.saveOrUpdate(soc);
    }

    //Método para eliminar un socio
    public void BajaSocio(Session sesion, String numsoc, Socio socio) throws SQLException {
        socio = sesion.get(Socio.class, numsoc);
        sesion.delete(socio);
    }

    //Actualizar la categoria de todos los socios a uno superior o inferior
    public void actualizaCategoria(Session sesion, ArrayList obj, int num) throws SQLException {
        ArrayList<Socio> socios = new ArrayList<>();
        for (Object elemento : obj) {
            Socio soc = (Socio) elemento;
            socios.add(soc);
        }
        if (num == 0) {
            for (Socio socio : socios) {
                Character cat = socio.getCategoria();
                switch (cat) {
                    case 'A':
                        break;
                    case 'B':
                        socio.setCategoria('A');
                        break;
                    case 'C':
                        socio.setCategoria('B');

                        break;
                    case 'D':
                        socio.setCategoria('C');
                        break;
                    case 'E':
                        socio.setCategoria('D');
                        break;
                }
                sesion.saveOrUpdate(socio);
            }
        }else{
            for (Socio socio : socios) {
                Character cat = socio.getCategoria();
                switch (cat) {
                    case 'A':
                        socio.setCategoria('B');
                        break;
                    case 'B':
                        socio.setCategoria('C');
                        break;
                    case 'C':
                        socio.setCategoria('D');

                        break;
                    case 'D':
                        socio.setCategoria('E');
                        break;
                    case 'E':
                        break;
                }
                sesion.saveOrUpdate(socio);
            }
        }

    }

    //Obtener el numero del ultimo socio registrado
    public ArrayList<String> ultimoSocio(Session sesion) {
        Query consulta = sesion.createNativeQuery("SELECT MAX(numeroSocio) AS max_valor FROM SOCIO");
        ArrayList<String> socios = (ArrayList<String>) consulta.getResultList();
        return socios;
    }

    //Busca un socio segun su numero
    public static Socio buscaSocio(Session sesion, String cod) throws SQLException {
        return sesion.get(Socio.class, cod);
    }

    // Devuelve la lista de socios que NO realiza una actividad cuyo número se pasa por parámetro
    public ArrayList<Socio> listaSociosNOactividad(Session sesion, String idActividad) throws SQLException {

        Query consulta = sesion.createNativeQuery("SELECT s.* "
                + "FROM SOCIO s "
                + "WHERE NOT EXISTS ("
                + "    SELECT 1 "
                + "    FROM ACTIVIDAD a "
                + "    INNER JOIN REALIZA r ON a.idActividad = r.idActividad "
                + "    WHERE a.idActividad = :act AND s.numeroSocio = r.numeroSocio"
                + ")")
                .setParameter("act", idActividad);

        ArrayList<Object[]> resultados = (ArrayList<Object[]>) consulta.getResultList();

        ArrayList<Socio> socios = new ArrayList<>();
        for (Object[] resultado : resultados) {
            Socio socio = new Socio();
            socio.setNumeroSocio((String) resultado[0]);
            socio.setNombre((String) resultado[1]);
            socio.setDni((String) resultado[2]);
            socio.setFechaNacimiento((String) resultado[3]);
            socio.setTelefono((String) resultado[4]);
            socio.setCorreo((String) resultado[5]);
            socio.setFechaEntrada((String) resultado[6]);
            socio.setCategoria((char) resultado[7]);

            socios.add(socio);
        }

        return socios;
    }
}
