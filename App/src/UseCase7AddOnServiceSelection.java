public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "Single-1";

        // Add services
        manager.addService(reservationId, new Service("Breakfast", 500));
        manager.addService(reservationId, new Service("Spa", 1500));
        manager.addService(reservationId, new Service("WiFi", 200));

        // Display services
        manager.displayServices(reservationId);

        // Total cost
        int total = manager.calculateTotalCost(reservationId);

        System.out.println("\nTotal Add-on Cost: ₹" + total);
    }
}