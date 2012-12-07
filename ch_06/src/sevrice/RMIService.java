package sevrice;

import rmi.History;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Contact: degas.developer@gmail.com
 * Date: 18.11.12
 * Time: 15:36
 */

public class RMIService {

    private History skeleton;

    public RMIService(){

        try {
            Registry registry = LocateRegistry.getRegistry();
            skeleton = (History) registry.lookup("history");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    public boolean login(String user, String password, HttpServletRequest request){
        try {
            if(skeleton.login(user, password)){
                request.getSession(true);
                return true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String[] getHistory(int i, HttpServletRequest request){
        try {
            if(request.getSession(false) != null){
                return skeleton.getHistory(i);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    public boolean logout(HttpServletRequest request, HttpServletResponse response){
        try {
            if(request.getSession(false) != null){
                request.getSession(false).invalidate();
                response.sendRedirect("login");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
