import javax.naming.*;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 23.10.12
 * Time: 13:21
 */

public class RMINamingTools {

    public static void main(String[] args) {

        try {
            Context context = new InitialContext();
            NamingEnumeration<NameClassPair> enumeration = context.list("rmi:");

            while (enumeration.hasMore()){
                System.out.println(enumeration.next());
            }

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
