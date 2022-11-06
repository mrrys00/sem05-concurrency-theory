package Lab03.Zad2;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class PrinterMonitor {
    // almost copy - paste : https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Condition.html
    final private Lock lock = new ReentrantLock();
    final private Condition notFull = lock.newCondition();
    final private Condition notEmpty = lock.newCondition();

    private List<Integer> printers = new ArrayList<>();    
    private int M;

    public PrinterMonitor(int M) {
        this.M = M;
        for (int i=0; i<M; i++) {
            printers.add(i);
        }
    }

    public void release(int x) throws InterruptedException {
        lock.lock();
        try {
            printers.add(x);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int reserve() throws InterruptedException {
        lock.lock();
        try {
            while (printers.size() == 0)
                notEmpty.await();
            int x = printers.get(printers.size()-1);
            printers.remove(printers.size()-1);
            return x;
        } finally {
            lock.unlock();
        }
    }
}
