import java.util.*;

public class BookingReportService {

    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    // Display all bookings
    public void displayAllBookings() {

        List<Reservation> bookings = history.getAllBookings();

        System.out.println("\nBooking History:");

        for (Reservation r : bookings) {
            System.out.println(
                    r.getGuestName() + " -> " + r.getRoomType()
            );
        }
    }

    // Summary report
    public void generateSummary() {

        List<Reservation> bookings = history.getAllBookings();

        HashMap<String, Integer> countMap = new HashMap<>();

        for (Reservation r : bookings) {
            String type = r.getRoomType();
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        System.out.println("\nBooking Summary:");

        for (String type : countMap.keySet()) {
            System.out.println(type + " Rooms Booked: " + countMap.get(type));
        }
    }
}