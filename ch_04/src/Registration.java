import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 30.10.12
 * Time: 12:02
 */
public class Registration {

    private String role;
    private Timestamp dataTime;

    public Registration(@NotNull String role, @NotNull Timestamp dataTime) {
        this.role = role;
        this.dataTime = dataTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(@NotNull String role) {
        this.role = role;
    }

    public Timestamp getDataTime() {
        return dataTime;
    }

    public void setDataTime(@NotNull Timestamp dataTime) {
        this.dataTime = dataTime;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Registration that = (Registration) o;

        if (dataTime != null ? !dataTime.equals(that.dataTime) : that.dataTime != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (dataTime != null ? dataTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "role='" + role + '\'' +
                ", dataTime=" + dataTime +
                '}';
    }
}
