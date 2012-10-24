import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 23.10.12
 * Time: 13:19
 */

public class RMIClient {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry();
            DateTime skeleton = (DateTime) registry.lookup("mytest");

            System.out.println(skeleton.getDate());
            System.out.println(skeleton.getTime());

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
