package Lab03.Zad3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class WaiterMonitor {
    final static PairsMonitor[] clientsReservations = new PairsMonitor[100]; // max 100 pairs
    private static RestaurantClient client1;
    private static RestaurantClient client2;
    private int max;

    final private Lock lock = new ReentrantLock();
    final private Condition notFull = lock.newCondition();
    final private Condition notEmpty = lock.newCondition();

    public WaiterMonitor(int max) {
        this.max = max;
        for (int i = 0; i < max; i++) {
            clientsReservations[i] = new PairsMonitor();
        }
    }

    public void reserve(RestaurantClient restaurantClient) throws InterruptedException {
        if (clientsReservations[(int) restaurantClient.getID()].getWaiting() == null) {
            System.out.println("Client " + (int) restaurantClient.getID() + " try to reserve table");
            clientsReservations[(int) restaurantClient.getID()].setWaiting(restaurantClient);
        } else if (clientsReservations[(int) restaurantClient.getID()].getWaiting() != null) {
            System.out.println("Clients pair " + (int) restaurantClient.getID() + " reserved table");
            client1 = clientsReservations[(int) restaurantClient.getID()].getWaiting();
            clientsReservations[(int) restaurantClient.getID()].unsetWaiting();
            client2 = restaurantClient;
            // notEmpty.signal();

        }

    }

    public void release(RestaurantClient restaurantClient) throws InterruptedException {
        System.out.println("Clients pair " + (int) restaurantClient.getID() + " release table");

        WaiterMonitor.client1 = null;
        WaiterMonitor.client2 = null;

        // while (client1 != null && client2 != null)
        //     notEmpty.await();
    }
}
