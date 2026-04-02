import java.util.HashMap;

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {

        // Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single", 2);
        inventory.addRoom("Suite", 1);

        // Queue
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Single"));
        queue.addRequest(new Reservation("Charlie", "Single")); // should fail
        queue.addRequest(new Reservation("David", "Suite"));

        // Allocation Service
        RoomAllocationService service =
                new RoomAllocationService(inventory, queue);

        service.processRequests();
    }
}