package session;

import org.jetbrains.annotations.NotNull;

import javax.ejb.Remote;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 31.10.12
 * Time: 13:49
 */

@Remote
public interface EJBDemo {

    boolean login(@NotNull String login, @NotNull String psw);

    @NotNull
    String getMessage (@NotNull String login);
}
