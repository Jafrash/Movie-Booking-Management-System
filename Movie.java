// Fill in the Movie class (was empty)
import java.util.Objects;

public class Movie {
    private final long id;
    private final String title;
    private final int durationMinutes;

    public Movie(long id, String title, int durationMinutes) {
        this.id = id;
        this.title = title;
        this.durationMinutes = durationMinutes;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public int getDurationMinutes() { return durationMinutes; }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title='" + title + '\'' + ", duration=" + durationMinutes + "m}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
