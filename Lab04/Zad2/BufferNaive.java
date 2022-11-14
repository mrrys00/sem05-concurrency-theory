package Lab04.Zad2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferNaive {
    private final Lock lock = new ReentrantLock();
    private final Condition canUseBuffer = lock.newCondition();

    List<Integer> producersPortions = new ArrayList<>();
    List<Integer> consumersPortions = new ArrayList<>();

    private int bufferSize;
    private boolean[] elements;
    private int currentNumberOfElements;

    private int portionTimes;

    private Statistics statistics;

    public BufferNaive(int bufferSize, int portionTimes, Statistics statistics) {
        this.bufferSize = bufferSize;
        elements = new boolean[bufferSize];
        for (int i = 0; i < bufferSize; i++)
            elements[i] = false;

        this.portionTimes = portionTimes;
        if (this.portionTimes > 0) {
            for (int i = 0; i < bufferSize / 2; i++) {
                for (int a = 0; a < portionTimes; a++) {
                    producersPortions.add(i);
                    consumersPortions.add(i);
                }
            }
            Collections.shuffle(producersPortions);
            Collections.shuffle(consumersPortions);
        }
        this.currentNumberOfElements = 0;

        this.statistics = statistics;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void bufferPrinter() {
        for (boolean element : elements) {
            if (element)
                System.out.print("1 ");
            else
                System.out.print("0 ");
        }
        System.out.println();
    }

    public void put(String name, int portion) throws InterruptedException {
        long ts = statistics.getNanoStartTimestamp();
        lock.lock();
        if (this.portionTimes > 0)
            portion = producersPortions.remove(0);
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
            statistics.putNaiveProducer(portion, statistics.getNanoDuration(ts));
        }
    }

    public void get(String name, int portion) throws InterruptedException {
        long ts = statistics.getNanoStartTimestamp();
        lock.lock();
        if (this.portionTimes > 0)
            portion = consumersPortions.remove(0);
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
            statistics.putNaiveCustomer(portion, statistics.getNanoDuration(ts));
        }
    }
}
