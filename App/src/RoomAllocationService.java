import java.util.*;

public class RoomAllocationService {

    private RoomInventory inventory;
    private BookingRequestQueue requestQueue;
    private BookingHistory history;
    private BookingValidator validator;

    private Set<String> allocatedRoomIds = new HashSet<>();
    private HashMap<String, Set<String>> roomAllocations = new HashMap<>();

    private int roomCounter = 1;

    public RoomAllocationService(RoomInventory inventory,
                                 BookingRequestQueue requestQueue,
                                 BookingHistory history,
                                 BookingValidator validator) {
        this.inventory = inventory;
        this.requestQueue = requestQueue;
        this.history = history;
        this.validator = validator;
    }
    public void addRoom(String type, int count) {
        inventory.put(type, inventory.getOrDefault(type, 0) + count);
    }
    public void processRequests() {

        Queue<Reservation> queue = requestQueue.getQueue();

        while (!queue.isEmpty()) {

            Reservation request = queue.poll();

            try {
                // ✅ VALIDATION
                validator.validate(request);

                String roomType = request.getRoomType();
                String guest = request.getGuestName();

                String roomId = roomType + "-" + roomCounter++;

                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);

                    roomAllocations
                            .computeIfAbsent(roomType, k -> new HashSet<>())
                            .add(roomId);

                    inventory.reduceRoom(roomType);

                    history.addBooking(request);

                    System.out.println("Booking Confirmed: " + guest +
                            " -> " + roomType +
                            " | Room ID: " + roomId);
                }

            } catch (InvalidBookingException e) {

                // ✅ GRACEFUL FAILURE
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }
    }
}