import java.util.*;

public class CancellationService {

    private RoomInventory inventory;

    // Stack for rollback (LIFO)
    private Stack<String> rollbackStack = new Stack<>();

    // Track active bookings
    private Set<String> activeBookings = new HashSet<>();

    public CancellationService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Register confirmed booking
    public void registerBooking(String reservationId) {
        activeBookings.add(reservationId);
    }

    // Cancel booking
    public void cancelBooking(String reservationId, String roomType) {

        // Validation
        if (!activeBookings.contains(reservationId)) {
            System.out.println("Cancellation Failed: Booking not found -> " + reservationId);
            return;
        }

        // Remove from active bookings
        activeBookings.remove(reservationId);

        // Push to rollback stack
        rollbackStack.push(reservationId);

        // Restore inventory (increment)
        int current = inventory.getInventorySnapshot().getOrDefault(roomType, 0);
        inventory.getInventorySnapshot().put(roomType, current + 1);

        System.out.println("Booking Cancelled: " + reservationId +
                " | Room Type: " + roomType);
    }

    // Show rollback stack
    public void showRollbackStack() {
        System.out.println("\nRollback Stack: " + rollbackStack);
    }
}