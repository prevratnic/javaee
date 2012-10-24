import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
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

public class RMIServer implements DateTime, Serializable {

    public RMIServer() throws RemoteException {
    }

    public String getDate() throws RemoteException {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    }

    public String getTime() throws RemoteException {
        return new SimpleDateFormat("hh:mm").format(new Date());
    }

    public static void main(String[] args) throws RemoteException {

        RMIServer remoteObject = new RMIServer();

        System.out.println("Start RMI Server");

       try {

            //DateTime stub = (DateTime) UnicastRemoteObject.exportObject(remoteObject, 0);
            //Registry registry = LocateRegistry.getRegistry();
            //registry.bind("rmi:mytest", stub);
      //  Naming.rebind("rmi://localhost/test", remoteObject);

            Context context = new InitialContext();
            context.bind("rmi:mytest", remoteObject);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        System.out.println("Waiting client");

    }
}
