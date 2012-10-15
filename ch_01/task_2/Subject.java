import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 15.10.12
 * Time: 23:44
 */
public class Subject implements Serializable {

    private static final long serialVersionUID = 42L;

    private String name;
    private String text;
    private Date date;

    public Subject() {
    }

    public Subject(@NotNull String name, @NotNull String text, @NotNull Date date) {
        this.name = name;
        this.text = text;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(@NotNull Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (date != null ? !date.equals(subject.date) : subject.date != null) return false;
        if (name != null ? !name.equals(subject.name) : subject.name != null) return false;
        if (text != null ? !text.equals(subject.text) : subject.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
