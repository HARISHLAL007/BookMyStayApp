import java.util.HashMap;

public class UseCase4RoomSearch {
    public static void main(String[] args) {

        // Step 1: Inventory (reuse UC3)
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single", 5);
        inventory.addRoom("Double", 0);
        inventory.addRoom("Suite", 3);

        // Step 2: Room Details
        HashMap<String, Room> roomDetails = new HashMap<>();
        roomDetails.put("Single", new Room("Single", 2000));
        roomDetails.put("Double", new Room("Double", 3500));
        roomDetails.put("Suite", new Room("Suite", 5000));

        // Step 3: Search Service
        RoomSearchService service = new RoomSearchService(inventory, roomDetails);

        // Step 4: Display
        service.displayAvailableRooms();
    }
}