import java.util.*;

public class RoomAllocationService {

    private RoomInventory inventory;
    private BookingRequestQueue requestQueue;
    private BookingHistory history;

    // Prevent duplicate room IDs
    private Set<String> allocatedRoomIds = new HashSet<>();

    // Map room type -> allocated room IDs
    private HashMap<String, Set<String>> roomAllocations = new HashMap<>();

    private int roomCounter = 1;

    public RoomAllocationService(RoomInventory inventory,
                                 BookingRequestQueue requestQueue,
                                 BookingHistory history) {
        this.inventory = inventory;
        this.requestQueue = requestQueue;
        this.history = history;
    }

    public void processRequests() {

        Queue<Reservation> queue = requestQueue.getQueue();

        while (!queue.isEmpty()) {

            Reservation request = queue.poll(); // FIFO

            String roomType = request.getRoomType();
            String guest = request.getGuestName();

            int available = inventory
                    .getInventorySnapshot()
                    .getOrDefault(roomType, 0);

            if (available > 0) {

                // Generate unique room ID
                String roomId = roomType + "-" + roomCounter++;

                // Ensure no duplicate
                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);

                    // Map room type to allocated IDs
                    roomAllocations
                            .computeIfAbsent(roomType, k -> new HashSet<>())
                            .add(roomId);

                    // Reduce inventory
                    inventory.reduceRoom(roomType);

                    // Add to booking history (UC8)
                    history.addBooking(request);

                    System.out.println("Booking Confirmed: " + guest +
                            " -> " + roomType +
                            " | Room ID: " + roomId);
                }

            } else {
                System.out.println("Booking Failed (No Rooms): " + guest +
                        " -> " + roomType);
            }
        }
    }
}