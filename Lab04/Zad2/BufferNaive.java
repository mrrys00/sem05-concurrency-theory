package Lab04.Zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferNaive {
    private final Lock lock = new ReentrantLock();
    private final Condition canUseBuffer = lock.newCondition();

    private int bufferSize;
    private boolean[] elements;
    private int currentNumberOfElements;

    public BufferNaive(int bufferSize) {
        this.bufferSize = bufferSize;
        elements = new boolean[bufferSize];
        for (int i = 0; i < bufferSize; i++)
            elements[i] = false;
        this.currentNumberOfElements = 0;
    }

    public int getBufferSize() { return this.bufferSize; }

    public void bufferPrinter() {
        for ( boolean element : elements ) {
            if (element) System.out.print("1 ");
            else System.out.print("0 ");
        }
        System.out.println();
    }

    public void put(String name, int portion) throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Producer " + name + " try to put : " + portion);
            while (bufferSize - currentNumberOfElements < portion)
                canUseBuffer.await();

            System.out.println("Producer " + name + " put : " + portion);
            for (int i = currentNumberOfElements; i < currentNumberOfElements + portion; i++)
                elements[i] = true;

            currentNumberOfElements += portion;
            System.out.println(currentNumberOfElements);
            canUseBuffer.signalAll();

        } finally {
            lock.unlock();
        }
    }

    public void get(String name, int portion) throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Consumer " + name + " try to get : " + portion);
            while (currentNumberOfElements < portion)
                canUseBuffer.await();

            System.out.println("Consumer " + name + " get : " + portion);
            for (int i = currentNumberOfElements - 1; i >= currentNumberOfElements - portion; i--)
                elements[i] = false;

            currentNumberOfElements -= portion;
            System.out.println(currentNumberOfElements);
            canUseBuffer.signalAll();

        } finally {
            lock.unlock();
        }
    }
}
