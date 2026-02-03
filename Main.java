import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        InMemoryDatabase db = new InMemoryDatabase();
        BookingService service = new BookingService(db);

        Movie m1 = db.addMovie("Edge of Tomorrow", 113);
        Theater t1 = db.addTheater("IMAX Downtown");
        Show s1 = db.addShow(m1, t1, LocalDateTime.now().plusDays(1).withHour(18).withMinute(30), 20);

        System.out.println("Shows:");
        service.listShows().forEach(System.out::println);

        System.out.println("Available seats before booking: " + service.availableSeats(s1.getId()));

        Booking b1 = service.book("alice", s1.getId(), Arrays.asList(1, 2));
        System.out.println("Booking made: " + b1);

        System.out.println("Available seats after booking: " + service.availableSeats(s1.getId()));

        try {
            service.book("bob", s1.getId(), Arrays.asList(2, 3)); // seat 2 already booked -> will throw
        } catch (Exception e) {
            System.out.println("Expected booking failure: " + e.getMessage());
        }

        service.cancel(b1.getId());
        System.out.println("Available seats after cancellation: " + service.availableSeats(s1.getId()));
    }
}
