import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 23.10.12
 * Time: 13:18
 */

public class RMIServer implements DateTime {

    public String getDate() throws RemoteException {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    }

    public String getTime() throws RemoteException {
        return new SimpleDateFormat("hh:mm").format(new Date());
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {

        System.out.println("Start RMI Server");

        RMIServer remoteObject = new RMIServer();
        DateTime stub = (DateTime) UnicastRemoteObject.exportObject(remoteObject, 0);

        LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        Naming.rebind("mytest", stub);

        System.out.println("Waiting client...");
    }
}
