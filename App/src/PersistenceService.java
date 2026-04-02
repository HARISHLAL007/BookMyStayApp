import java.io.*;
import java.util.*;

public class PersistenceService {

    private static final String FILE_NAME = "system_state.txt";

    // SAVE DATA
    public void saveState(RoomInventory inventory, BookingHistory history) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            // Save Inventory
            writer.write("INVENTORY\n");
            for (Map.Entry<String, Integer> entry : inventory.getInventorySnapshot().entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }

            // Save Booking History
            writer.write("HISTORY\n");
            for (String record : history.getHistory()) {
                writer.write(record + "\n");
            }

            System.out.println("State Saved Successfully");

        } catch (IOException e) {
            System.out.println("Error Saving State");
        }
    }

    // LOAD DATA
    public void loadState(RoomInventory inventory, BookingHistory history) {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No previous state found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            boolean isInventory = false;
            boolean isHistory = false;

            while ((line = reader.readLine()) != null) {

                if (line.equals("INVENTORY")) {
                    isInventory = true;
                    isHistory = false;
                    continue;
                }

                if (line.equals("HISTORY")) {
                    isInventory = false;
                    isHistory = true;
                    continue;
                }

                if (isInventory) {
                    String[] parts = line.split(":");
                    inventory.addRoom(parts[0], Integer.parseInt(parts[1]));
                }

                if (isHistory) {
                    history.addRecord(line);
                }
            }

            System.out.println("State Loaded Successfully");

        } catch (Exception e) {
            System.out.println("Error Loading State. Starting safe.");
        }
    }
}