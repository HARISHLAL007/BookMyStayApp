public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single", 1);

        CancellationService cancelService =
                new CancellationService(inventory);

        // Simulate confirmed booking
        String reservationId = "Single-1";
        cancelService.registerBooking(reservationId);

        // Valid cancellation
        cancelService.cancelBooking("Single-1", "Single");

        // Try cancelling again (should fail)
        cancelService.cancelBooking("Single-1", "Single");

        // Invalid booking
        cancelService.cancelBooking("Fake-99", "Single");

        cancelService.showRollbackStack();
    }
}