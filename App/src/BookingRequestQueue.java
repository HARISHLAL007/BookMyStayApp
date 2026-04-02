import java.util.LinkedList;
import java.util.Queue;

public class BookingRequestQueue {
    private Queue<Reservation> queue;
    public Queue<Reservation> getQueue() {
        return queue;
    }
    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }
    public String pollRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
    // Add request (enqueue)
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added: " 
            + reservation.getGuestName() + " -> " 
            + reservation.getRoomType());
    }

    // View all requests (without removing)
    public void displayQueue() {
        System.out.println("\nBooking Request Queue:");

        for (Reservation r : queue) {
            System.out.println(
                r.getGuestName() + " requested " + r.getRoomType()
            );
        }
    }
}