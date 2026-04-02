public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single", 1);

        BookingRequestQueue queue = new BookingRequestQueue();

        // Valid booking
        queue.addRequest(new Reservation("Alice", "Single"));

        // Invalid room type
        queue.addRequest(new Reservation("Bob", "Luxury"));

        // Empty guest name
        queue.addRequest(new Reservation("", "Single"));

        // No availability
        queue.addRequest(new Reservation("Charlie", "Single"));

        BookingHistory history = new BookingHistory();
        BookingValidator validator = new BookingValidator(inventory);

        RoomAllocationService service =
                new RoomAllocationService(inventory, queue, history, validator);

        service.processRequests();
    }
}