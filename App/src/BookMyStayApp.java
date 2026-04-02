import java.io.*;
import java.util.*;

// Booking class
class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    int bookingId;
    String customerName;
    int roomNumber;

    public Booking(int bookingId, String customerName, int roomNumber) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                ", Customer: " + customerName +
                ", Room: " + roomNumber;
    }
}

// Inventory class
class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<Integer, Boolean> rooms = new HashMap<>();

    public Inventory(int totalRooms) {
        for (int i = 1; i <= totalRooms; i++) {
            rooms.put(i, true); // true = available
        }
    }

    public boolean bookRoom(int roomNumber) {
        if (rooms.getOrDefault(roomNumber, false)) {
            rooms.put(roomNumber, false);
            return true;
        }
        return false;
    }

    public void restoreRoom(int roomNumber) {
        rooms.put(roomNumber, true);
    }

    public void displayRooms() {
        System.out.println("Room Availability:");
        for (Map.Entry<Integer, Boolean> entry : rooms.entrySet()) {
            System.out.println("Room " + entry.getKey() + " : "
                    + (entry.getValue() ? "Available" : "Booked"));
        }
    }
}

// Wrapper class for persistence
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    List<Booking> bookings;
    Inventory inventory;

    public SystemState(List<Booking> bookings, Inventory inventory) {
        this.bookings = bookings;
        this.inventory = inventory;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "hotel_state.ser";

    public static void saveState(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    public static SystemState loadState() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("Loading saved state...");
            return (SystemState) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("Corrupted data. Starting with clean state.");
        }
        return null;
    }
}

// Main class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Booking> bookings;
        Inventory inventory;

        // Step 1: Load persisted data
        SystemState state = PersistenceService.loadState();

        if (state != null) {
            bookings = state.bookings;
            inventory = state.inventory;
        } else {
            bookings = new ArrayList<>();
            inventory = new Inventory(5); // 5 rooms
        }

        int choice;

        do {
            System.out.println("\n--- Hotel Booking System ---");
            System.out.println("1. Book Room");
            System.out.println("2. View Bookings");
            System.out.println("3. View Inventory");
            System.out.println("4. Exit (Save State)");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Booking ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Room Number: ");
                    int room = sc.nextInt();

                    if (inventory.bookRoom(room)) {
                        bookings.add(new Booking(id, name, room));
                        System.out.println("Booking successful!");
                    } else {
                        System.out.println("Room already booked!");
                    }
                    break;

                case 2:
                    System.out.println("\nBookings:");
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings found.");
                    } else {
                        for (Booking b : bookings) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 3:
                    inventory.displayRooms();
                    break;

                case 4:
                    // Step 2: Save state before shutdown
                    PersistenceService.saveState(
                            new SystemState(bookings, inventory));
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}