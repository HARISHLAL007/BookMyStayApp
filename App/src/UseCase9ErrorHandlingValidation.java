public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single", 1);

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Luxury"));
        queue.addRequest(new Reservation("", "Single"));
        queue.addRequest(new Reservation("Charlie", "Single"));

        BookingHistory history = new BookingHistory();
        BookingValidator validator = new BookingValidator(inventory);

        RoomAllocationService service =
                new RoomAllocationService(inventory, queue, history, validator);

        service.processRequests();
    }
}