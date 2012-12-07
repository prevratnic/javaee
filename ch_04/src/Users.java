import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 30.10.12
 * Time: 12:02
 */
public class Users {

    private String login;
    private List<Registration> registrationList;

    public Users() {
        registrationList = new ArrayList<Registration>();
    }

    public Users(@NotNull String login) {
        this();
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void addRegistrationData(@NotNull String role, @NotNull Timestamp timestamp){
        registrationList.add(new Registration(role, timestamp));
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (login != null ? !login.equals(users.login) : users.login != null) return false;
        if (registrationList != null ? !registrationList.equals(users.registrationList) : users.registrationList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (registrationList != null ? registrationList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "login='" + login + '\'' +
                ", registrationList=" + registrationList +
                '}';
    }
}
