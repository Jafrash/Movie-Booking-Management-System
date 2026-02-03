// Fill in the Theater class (was empty)
import java.util.Objects;

public class Theater {
    private final long id;
    private final String name;

    public Theater(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Theater{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theater theater = (Theater) o;
        return id == theater.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
