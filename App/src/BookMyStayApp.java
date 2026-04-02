// File: UseCase2RoomInitialization.java
// Version 2.1

abstract class Room {
    protected String roomType;
    protected int numberOfBeds;
    protected double pricePerNight;

    // Constructor
    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    // Abstract method to display room info
    public abstract void displayDetails();
}

// Concrete SingleRoom class
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 50.0);
    }

    @Override
    public void displayDetails() {
        System.out.println(roomType + " | Beds: " + numberOfBeds + " | Price: $" + pricePerNight);
    }
}

// Concrete DoubleRoom class
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 90.0);
    }

    @Override
    public void displayDetails() {
        System.out.println(roomType + " | Beds: " + numberOfBeds + " | Price: $" + pricePerNight);
    }
}

// Concrete SuiteRoom class
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 4, 200.0);
    }

    @Override
    public void displayDetails() {
        System.out.println(roomType + " | Beds: " + numberOfBeds + " | Price: $" + pricePerNight);
    }
}

public class UseCase2RoomInitialization {
    // Static availability for each room type
    static int singleRoomAvailable = 5;
    static int doubleRoomAvailable = 3;
    static int suiteRoomAvailable = 2;

    public static void main(String[] args) {
        System.out.println("Welcome to Book My Stay - UC2 Room Initialization\n");

        // Initialize room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Display room details
        singleRoom.displayDetails();
        System.out.println("Available: " + singleRoomAvailable + "\n");

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleRoomAvailable + "\n");

        suiteRoom.displayDetails();
        System.out.println("Available: " + suiteRoomAvailable + "\n");

        System.out.println("Thank you for using Book My Stay!");
    }
}