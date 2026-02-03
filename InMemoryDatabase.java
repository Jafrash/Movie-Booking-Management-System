import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDatabase {
    private final AtomicLong movieIdSeq = new AtomicLong(1);
    private final AtomicLong theaterIdSeq = new AtomicLong(1);
    private final AtomicLong showIdSeq = new AtomicLong(1);
    private final AtomicLong bookingIdSeq = new AtomicLong(1);

    private final Map<Long, Movie> movies = new ConcurrentHashMap<>();
    private final Map<Long, Theater> theaters = new ConcurrentHashMap<>();
    private final Map<Long, Show> shows = new ConcurrentHashMap<>();
    private final Map<Long, Booking> bookings = new ConcurrentHashMap<>();

    public Movie addMovie(String title, int durationMinutes) {
        long id = movieIdSeq.getAndIncrement();
        Movie m = new Movie(id, title, durationMinutes);
        movies.put(id, m);
        return m;
    }

    public Theater addTheater(String name) {
        long id = theaterIdSeq.getAndIncrement();
        Theater t = new Theater(id, name);
        theaters.put(id, t);
        return t;
    }

    public Show addShow(Movie movie, Theater theater, java.time.LocalDateTime startTime, int totalSeats) {
        long id = showIdSeq.getAndIncrement();
        Show s = new Show(id, movie, theater, startTime, totalSeats);
        shows.put(id, s);
        return s;
    }

    public Booking saveBooking(String user, long showId, Set<Integer> seats, java.time.LocalDateTime time) {
        long id = bookingIdSeq.getAndIncrement();
        Booking b = new Booking(id, user, showId, seats, time);
        bookings.put(id, b);
        return b;
    }

    public Optional<Movie> getMovie(long id) { return Optional.ofNullable(movies.get(id)); }
    public Optional<Show> getShow(long id) { return Optional.ofNullable(shows.get(id)); }
    public Optional<Booking> getBooking(long id) { return Optional.ofNullable(bookings.get(id)); }

    public Collection<Show> listShows() { return Collections.unmodifiableCollection(shows.values()); }

    public void removeBooking(long bookingId) { bookings.remove(bookingId); }
}
