import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 23.10.12
 * Time: 13:18
 */

public class RMIServer extends UnicastRemoteObject implements DateTime {

    protected RMIServer() throws RemoteException {
        super();
    }

    public String getDate() throws RemoteException {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    }

    public String getTime() throws RemoteException {
        return new SimpleDateFormat("hh:mm").format(new Date());
    }

    public static void main(String[] args) throws RemoteException{
        RMIServer remoteObject = new RMIServer();

        System.setSecurityManager(new RMISecurityManager());
        LocateRegistry.createRegistry(1099);
    }
}
