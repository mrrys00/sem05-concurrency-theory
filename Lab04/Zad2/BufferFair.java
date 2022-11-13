package Lab04.Zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferFair {
    private final Lock lock = new ReentrantLock();
    private final Condition firstProducer = lock.newCondition();
    private final Condition producerRest = lock.newCondition();
    private final Condition firstConsumer = lock.newCondition();
    private final Condition consumerRest = lock.newCondition();

    private boolean isWaitingProducer;
    private boolean isWaitingConsumer;

    private int bufferSize;
    private boolean[] elements;
    private int currentNumberOfElements;

    public BufferFair (int bufferSize) {
        isWaitingConsumer = false;
        isWaitingProducer = false;

        this.bufferSize=bufferSize;
        elements = new boolean[bufferSize];
        for(int i = 0; i < bufferSize; i++)
            elements[i] = false;
        this.currentNumberOfElements = 0;
    }

    public int getBufferSize() { return this.bufferSize; }

    public void put(String name, int portion) throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Producer " + name + " try to put " + portion);

            if(isWaitingProducer)
                producerRest.await();
            isWaitingProducer = true;

            while (bufferSize - currentNumberOfElements < portion)
                firstProducer.await();

            System.out.println("Producer " + name + " put " + portion);
            for(int i = currentNumberOfElements; i < currentNumberOfElements + portion; i++)
                elements[i] = true;

            currentNumberOfElements += portion;
            System.out.println(currentNumberOfElements);

            isWaitingProducer = false;
            producerRest.signal();
            firstConsumer.signal();

        } finally {
            lock.unlock();
        }
    }

    public void get(String name, int portion) throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Consumer " + name + " try to get " + portion);

            if(isWaitingConsumer)
                consumerRest.await();
            isWaitingConsumer = true;

            while (currentNumberOfElements < portion)
                firstConsumer.await();


            System.out.println("Consumer " + name + " get " + portion);
            for(int i = currentNumberOfElements - 1; i >= currentNumberOfElements - portion; i--)
                elements[i] = false;

            currentNumberOfElements -= portion;
            System.out.println(currentNumberOfElements);

            isWaitingConsumer = false;
            firstProducer.signal();
            consumerRest.signal();

        } finally {
            lock.unlock();
        }
    }
}
