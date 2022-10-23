package Lab03.Zad3;

public class WaiterMonitor {
    final RestaurantClient[] clientsReservations = new RestaurantClient[100];     // max 100 pairs
    private int max;

    public WaiterMonitor(int max) {
        this.max = max;
        for (int i=0; i<max; i++) {
            clientsReservations[i] = null;
        }
    }

    public static void reserve(RestaurantClient restaurantClient) {

    }

    public static void release(RestaurantClient restaurantClient) {

    }
}
