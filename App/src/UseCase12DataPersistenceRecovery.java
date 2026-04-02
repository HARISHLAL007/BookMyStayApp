public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        PersistenceService service = new PersistenceService();

        // LOAD PREVIOUS STATE
        service.loadState(inventory, history);

        // Simulate new data
        inventory.addRoom("Single", 2);
        history.addRecord("Booked: Single-101");

        System.out.println("\nCurrent Inventory: " + inventory.getInventorySnapshot());
        System.out.println("Booking History: " + history.getHistory());

        // SAVE STATE
        service.saveState(inventory, history);
    }
}