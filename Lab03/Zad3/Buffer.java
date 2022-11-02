package Lab03.Zad3;

import java.util.ArrayDeque;
import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private String message;
    private boolean isEmpty;
    private final Lock lock = new ReentrantLock();
    private final Condition empty = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public Buffer(){
        isEmpty = true;
    }
    public void put(String message) throws InterruptedException {

        lock.lock();
        try
        {
            while(!isEmpty)
                empty.await();

            this.message = message;
            isEmpty = false;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String take() throws InterruptedException
    {
        String message;
        lock.lock();
        try
        {
            while(isEmpty)
                notEmpty.await();

            message = this.message;
            isEmpty = true;
            empty.signal();
        } finally {
            lock.unlock();
        }

        return message;
    }
}
