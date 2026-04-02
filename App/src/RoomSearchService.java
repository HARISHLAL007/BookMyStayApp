import java.util.HashMap;

public class RoomSearchService {
    private RoomInventory inventory;
    private HashMap<String, Room> roomDetails;

    public RoomSearchService(RoomInventory inventory, HashMap<String, Room> roomDetails) {
        this.inventory = inventory;
        this.roomDetails = roomDetails;
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");

        HashMap<String, Integer> rooms = inventory.getInventorySnapshot();

        for (String roomType : rooms.keySet()) {
            int available = rooms.get(roomType);

            if (available > 0) { // ✅ filtering
                Room room = roomDetails.get(roomType);

                System.out.println(
                        roomType + " | Price: " + room.getPrice() + " | Available: " + available
                );
            }
        }
        System.out.println();
    }
}