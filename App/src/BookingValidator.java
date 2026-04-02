import java.util.Map;

public class BookingValidator {

    private RoomInventory inventory;

    public BookingValidator(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void validate(Reservation reservation) throws InvalidBookingException {

        if (reservation == null) {
            throw new InvalidBookingException("Reservation cannot be null");
        }

        if (reservation.getGuestName() == null || reservation.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name is required");
        }

        String roomType = reservation.getRoomType();

        if (roomType == null || roomType.isEmpty()) {
            throw new InvalidBookingException("Room type is required");
        }

        Map<String, Integer> inventoryMap = inventory.getInventorySnapshot();

        if (!inventoryMap.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (inventoryMap.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
    }
}