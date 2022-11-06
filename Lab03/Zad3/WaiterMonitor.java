package Lab03.Zad3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class WaiterMonitor {
    final static PairsMonitor[] clientsReservations = new PairsMonitor[100]; // max 100 pairs
    private static RestaurantClient client1;
    private static RestaurantClient client2;
    private long clientThreadID1;
    private long clientThreadID2;
    private int max;

    final private Lock lock = new ReentrantLock();
    final private Condition client1State = lock.newCondition();
    final private Condition client2State = lock.newCondition();

    public WaiterMonitor(int max) {
        this.max = max;
        for (int i = 0; i < max; i++) {
            clientsReservations[i] = new PairsMonitor();
        }
    }

    public void reserve(RestaurantClient restaurantClient) throws InterruptedException {
        if (clientsReservations[restaurantClient.getID()].getWaiting() == null) {
            System.out.println("Client " + restaurantClient.getID() + " try to reserve table ; thread " + restaurantClient.getId());
            clientsReservations[restaurantClient.getID()].setWaiting(restaurantClient);
        } else if (clientsReservations[restaurantClient.getID()].getWaiting() != null) {
            lock.lock();
            try {
                while (client1 != null)
                    client1State.await();

                while (client2 != null)
                    client2State.await();
            } finally {
                lock.unlock();
            }

            System.out.println("Clients pair " + restaurantClient.getID() + " reserved table ; thread " + restaurantClient.getId());
            client1 = clientsReservations[restaurantClient.getID()].getWaiting();
            clientsReservations[restaurantClient.getID()].unsetWaiting();
            client2 = restaurantClient;
            clientThreadID1 = client1.getId();
            clientThreadID2 = client2.getId();
        }

    }

    public void release(RestaurantClient restaurantClient) throws InterruptedException {
        System.out.println(
                "Clients pair " + restaurantClient.getID() + " release table ; thread " + restaurantClient.getId());

        lock.lock();
        try {
            if (restaurantClient.getId() == clientThreadID1) {
                WaiterMonitor.client1 = null;
                client1State.signal();
            } else if (restaurantClient.getId() == clientThreadID2) {
                WaiterMonitor.client2 = null;
                client2State.signal();
            }
        } finally {
            lock.unlock();
        }

    }
}
