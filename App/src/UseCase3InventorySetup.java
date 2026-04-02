public class UseCase3InventorySetup {
    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay - UC3 Centralized Inventory\n");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("Booking a Single Room...");
        if (inventory.bookRoom("Single Room")) {
            System.out.println("Booking successful!\n");
        } else {
            System.out.println("No Single Rooms available!\n");
        }

        inventory.displayInventory();

        System.out.println("Cancelling a booking for Double Room...");
        inventory.cancelBooking("Double Room");

        inventory.displayInventory();

        System.out.println("Thank you for using Book My Stay!");
    }
}