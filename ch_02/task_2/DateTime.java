import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 23.10.12
 * Time: 13:08
 */

public interface DateTime extends Remote {
    String getDate() throws RemoteException;
    String getTime() throws RemoteException;
}
