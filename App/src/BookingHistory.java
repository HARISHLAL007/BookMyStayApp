import java.util.*;

public class BookingHistory {
    public List<String> getHistory() {
        return history;
    }

    public void addRecord(String record) {
        history.add(record);
    }
    private List<Reservation> history = new ArrayList<>();

    // Add confirmed booking
    public void addBooking(Reservation reservation) {
        history.add(reservation);
    }

    // Get all bookings
    public List<Reservation> getAllBookings() {
        return history;
    }
}