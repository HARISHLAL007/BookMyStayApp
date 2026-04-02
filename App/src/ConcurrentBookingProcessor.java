import java.util.*;

public class ConcurrentBookingProcessor {

    private BookingRequestQueue queue;
    private RoomInventory inventory;

    public ConcurrentBookingProcessor(BookingRequestQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    // THREAD SAFE METHOD
    public synchronized void processBooking() {

        if (queue.isEmpty()) {
            return;
        }

        String request = queue.pollRequest();

        if (request == null) return;

        String roomType = request.split("-")[0];

        Map<String, Integer> snapshot = inventory.getInventorySnapshot();

        int available = snapshot.getOrDefault(roomType, 0);

        if (available > 0) {
            snapshot.put(roomType, available - 1);

            System.out.println(Thread.currentThread().getName() +
                    " SUCCESS Booking: " + request);
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " FAILED Booking: " + request);
        }
    }
}