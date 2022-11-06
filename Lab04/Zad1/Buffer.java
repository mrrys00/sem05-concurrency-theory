package Lab04.Zad1;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Buffer {
    final private Lock lock = new ReentrantLock();
    final private Condition notFull = lock.newCondition();
    final private Condition notEmpty = lock.newCondition();

    private List<String> buffer = new ArrayList<>();    
    private int M;

    public Buffer(int M) {
        this.M = M;
    }

    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == 0)
                notEmpty.await();
            String x = buffer.get(buffer.size()-1);
            buffer.remove(buffer.size()-1);
            return x;
        } finally {
            lock.unlock();
        }
    }

    public void put(String x) throws InterruptedException {
        lock.lock();
        try {
            buffer.add(x);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
}
