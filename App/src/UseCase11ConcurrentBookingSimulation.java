public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single", 1);

        BookingRequestQueue queue = new BookingRequestQueue();

        // Multiple requests (same room → conflict simulation)
        queue.addRequest("Single-1");
        queue.addRequest("Single-2");
        queue.addRequest("Single-3");

        ConcurrentBookingProcessor processor =
                new ConcurrentBookingProcessor(queue, inventory);

        // THREADS (simulate multiple users)
        Runnable task = () -> {
            for (int i = 0; i < 2; i++) {
                processor.processBooking();
            }
        };

        Thread t1 = new Thread(task, "User-1");
        Thread t2 = new Thread(task, "User-2");
        Thread t3 = new Thread(task, "User-3");

        t1.start();
        t2.start();
        t3.start();
    }
}