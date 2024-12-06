/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IRemoto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author pablo
 */
public interface IRemoto extends Remote{

    void entraEfectivo(int id) throws RemoteException, InterruptedException;

    void entraTarjeta(int id) throws RemoteException, InterruptedException;

    void saleEfectivo(int id) throws RemoteException;

    void saleTarjeta(int id) throws RemoteException;
    
}
