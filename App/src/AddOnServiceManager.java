import java.util.*;

public class AddOnServiceManager {

    // Map<ReservationID, List<Service>>
    private HashMap<String, List<Service>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, Service service) {

        serviceMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println("Added Service: " + service.getName() +
                " to Reservation: " + reservationId);
    }

    // Display services
    public void displayServices(String reservationId) {

        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services for " + reservationId);
            return;
        }

        System.out.println("\nServices for " + reservationId + ":");

        for (Service s : services) {
            System.out.println(s.getName() + " - ₹" + s.getCost());
        }
    }

    // Calculate total cost
    public int calculateTotalCost(String reservationId) {

        List<Service> services = serviceMap.get(reservationId);

        if (services == null) return 0;

        int total = 0;

        for (Service s : services) {
            total += s.getCost();
        }

        return total;
    }
}