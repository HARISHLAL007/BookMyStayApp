public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {

        // Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single", 2);
        inventory.addRoom("Suite", 1);

        // Queue
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Suite"));
        queue.addRequest(new Reservation("Charlie", "Single"));

        // History
        BookingHistory history = new BookingHistory();

        // Allocation
        RoomAllocationService service =
                new RoomAllocationService(inventory, queue, history);

        service.processRequests();

        // Reporting
        BookingReportService report =
                new BookingReportService(history);

        report.displayAllBookings();
        report.generateSummary();
    }
}