package rmi;

import org.jetbrains.annotations.NotNull;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Contact: degas.developer@gmail.com
 * Date: 14.11.12
 * Time: 15:59
 */
public interface History extends Remote {
    @NotNull
    String[] getHistory (int code) throws RemoteException;
    boolean login (@NotNull String user, @NotNull String password) throws RemoteException;
    boolean logout () throws RemoteException;
}
