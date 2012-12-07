package client;

import session.EJBDemo;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 31.10.12
 * Time: 14:13
 */

public class Client {

    public static void main(String[] args) {
        try {
            InitialContext context = new InitialContext();
            EJBDemo ejbDemo = (EJBDemo) context.lookup("session.EJBDemo");

            ejbDemo.login("adm", "adm");

            System.out.println(ejbDemo.getMessage("adm"));

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
