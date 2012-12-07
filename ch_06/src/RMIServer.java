import database.ConnectionManager;
import rmi.History;
import rmi.HistoryImpl;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Contact: degas.developer@gmail.com
 * Date: 14.11.12
 * Time: 16:07
 */

public class RMIServer {
    public static void main(String[] args) {

        try{
            System.out.println("Start RMI Server");

            History remoteObject = new HistoryImpl();
            History stub = (History) UnicastRemoteObject.exportObject(remoteObject, 0);

            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            registry.rebind("history", stub);

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                if(scanner.next().trim().toLowerCase().equals("quit")){
                    System.out.println("Goodbye");
                    ConnectionManager.close();
                    System.exit(0);
                }
            }

        }catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
