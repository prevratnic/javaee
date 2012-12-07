package session;

import org.jetbrains.annotations.NotNull;

import javax.ejb.Stateful;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 31.10.12
 * Time: 14:10
 */

@Stateful
public class EJBDemoBean implements EJBDemo {

    private boolean flag = false;

    @Override
    public boolean login(@NotNull String login, @NotNull String psw) {
        if(login.equals("adm") && psw.equals("adm")){
            flag = true;
        }
        return flag;
    }

    @NotNull
    @Override
    public String getMessage(@NotNull String login) {
        if(flag){
            return "Hello " + login;
        }
        return login + " Not register";
    }
}
