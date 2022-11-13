package Lab04;

import java.util.ArrayList;
import java.util.List;

import Lab04.Zad1.Buffer;
import Lab04.Zad1.Consumer;
import Lab04.Zad1.Processor;
import Lab04.Zad1.Producer;

import Lab04.Zad2.BufferV2;
import Lab04.Zad2.ConsumerV2;
import Lab04.Zad2.ProducerV2;
import Lab04.Zad2.Statistics;

public class Lab04 {
    public static void main(String[] args) throws InterruptedException {
        if (args[0].equals("stre"))
            streamProcessing();
        else if (args[0].equals("naiv"))
            naiveProcessing();
        else if (args[0].equals("fair"))
            fairProcessing();
    }

    private static void streamProcessing() throws InterruptedException {
        int max = 10;
        int producerNumber = 1;
        int processorNumber = 10;
        int consumerNumber = 1;
        int expState = 0;

        Buffer buffer = new Buffer(max, producerNumber+processorNumber+consumerNumber);
        List<Producer> producers = new ArrayList<>();
        List<Consumer> consumers = new ArrayList<>();
        List<Processor> processors = new ArrayList<>();

        for (Integer i = 0; i < producerNumber; i++) {
            Producer prod = new Producer(buffer, "Producer" + i.toString(), -1, 0);
            producers.add(prod);
        }

        for (Integer i = 0; i < processorNumber; i++) {
            Processor proc = new Processor(buffer, "Processor" + i.toString(), expState, expState+1);
            processors.add(proc);
            expState += 1;
        }

        for (Integer i = 0; i < consumerNumber; i++) {
            Consumer cons = new Consumer(buffer, "Customer" + i.toString(), expState, -1);
            consumers.add(cons);
        }

        for (Thread t : producers) {
            t.start();
        }
        for (Thread t : processors) {
            t.start();
        }
        for (Thread t : consumers) {
            t.start();
        }

        for (Thread t : producers) {
            t.join();
        }
        for (Thread t : processors) {
            t.join();
        }
        for (Thread t : consumers) {
            t.join();
        }
    }

    private static void naiveProcessing() throws InterruptedException {
    }

    private static void fairProcessing() throws InterruptedException {
    }
}
