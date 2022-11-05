package Lab03.Zad3;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class PairsMonitor {
    final private Lock lock = new ReentrantLock();
    final private Condition notFull = lock.newCondition();
    final private Condition notEmpty = lock.newCondition();

    private RestaurantClient waitingClient = null;

    // public PairsMonitor() {
        
    // }

    public RestaurantClient getWaiting() { return this.waitingClient; }
    
    public void setWaiting(RestaurantClient client) throws InterruptedException {
        lock.lock();
        try {
            this.waitingClient = client;
            while (this.waitingClient != null)
                notEmpty.await();
        } finally {
            lock.unlock();
        }
    }

    public void unsetWaiting() throws InterruptedException {
        lock.lock();
        try {
            this.waitingClient = null;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
}
