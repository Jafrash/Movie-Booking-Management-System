import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Show {
    private final long id;
    private final Movie movie;
    private final Theater theater;
    private final LocalDateTime startTime;
    private final int totalSeats;
    private final Set<Integer> bookedSeats = Collections.synchronizedSet(new HashSet<>());
    private final ReentrantLock lock = new ReentrantLock();

    public Show(long id, Movie movie, Theater theater, LocalDateTime startTime, int totalSeats) {
        this.id = id;
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.totalSeats = totalSeats;
    }

    public long getId() { return id; }
    public Movie getMovie() { return movie; }
    public Theater getTheater() { return theater; }
    public LocalDateTime getStartTime() { return startTime; }
    public int getTotalSeats() { return totalSeats; }

    public List<Integer> getAvailableSeats() {
        return IntStream.rangeClosed(1, totalSeats)
                .boxed()
                .filter(s -> !bookedSeats.contains(s))
                .collect(Collectors.toList());
    }

    public boolean areSeatsValid(Collection<Integer> seats) {
        return seats.stream().allMatch(s -> s >= 1 && s <= totalSeats);
    }

    public boolean areSeatsAvailable(Collection<Integer> seats) {
        return seats.stream().noneMatch(bookedSeats::contains);
    }

    public void bookSeats(Collection<Integer> seats) {
        lock.lock();
        try {
            if (!areSeatsValid(seats)) {
                throw new IllegalArgumentException("One or more seat numbers are invalid for this show.");
            }
            if (!areSeatsAvailable(seats)) {
                throw new IllegalStateException("One or more seats are already booked.");
            }
            bookedSeats.addAll(seats);
        } finally {
            lock.unlock();
        }
    }

    public void releaseSeats(Collection<Integer> seats) {
        lock.lock();
        try {
            bookedSeats.removeAll(seats);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Show{" + "id=" + id + ", movie=" + movie.getTitle() + ", theater=" + theater.getName() +
                ", start=" + startTime + ", totalSeats=" + totalSeats + '}';
    }
}
