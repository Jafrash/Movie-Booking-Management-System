import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private final InMemoryDatabase db;

    public BookingService(InMemoryDatabase db) {
        this.db = db;
    }

    public Booking book(String userName, long showId, Collection<Integer> seatNumbers) {
        Show show = db.getShow(showId).orElseThrow(() -> new NoSuchElementException("Show not found: " + showId));
        // attempt booking under show lock
        show.bookSeats(seatNumbers);
        Booking booking = db.saveBooking(userName, showId, new HashSet<>(seatNumbers), LocalDateTime.now());
        return booking;
    }

    public void cancel(long bookingId) {
        Booking booking = db.getBooking(bookingId).orElseThrow(() -> new NoSuchElementException("Booking not found: " + bookingId));
        Show show = db.getShow(booking.getShowId()).orElseThrow(() -> new NoSuchElementException("Show not found: " + booking.getShowId()));
        show.releaseSeats(booking.getSeats());
        db.removeBooking(bookingId);
    }

    public List<Integer> availableSeats(long showId) {
        return db.getShow(showId).map(Show::getAvailableSeats).orElse(Collections.emptyList());
    }

    public List<Show> listShows() {
        return db.listShows().stream().collect(Collectors.toList());
    }
}
