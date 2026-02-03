import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class Booking {
    private final long id;
    private final String userName;
    private final long showId;
    private final Set<Integer> seats;
    private final LocalDateTime bookingTime;

    public Booking(long id, String userName, long showId, Set<Integer> seats, LocalDateTime bookingTime) {
        this.id = id;
        this.userName = userName;
        this.showId = showId;
        this.seats = Collections.unmodifiableSet(seats);
        this.bookingTime = bookingTime;
    }

    public long getId() { return id; }
    public String getUserName() { return userName; }
    public long getShowId() { return showId; }
    public Set<Integer> getSeats() { return seats; }
    public LocalDateTime getBookingTime() { return bookingTime; }

    @Override
    public String toString() {
        return "Booking{" + "id=" + id + ", userName='" + userName + '\'' + ", showId=" + showId +
                ", seats=" + seats + ", bookingTime=" + bookingTime + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
