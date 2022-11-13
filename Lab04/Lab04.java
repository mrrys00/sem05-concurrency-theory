package Lab04;

import java.util.ArrayList;
import java.util.List;

import Lab04.Zad1.Buffer;
import Lab04.Zad1.Consumer;
import Lab04.Zad1.Processor;
import Lab04.Zad1.Producer;

import Lab04.Zad2.BufferFair;
import Lab04.Zad2.BufferNaive;
import Lab04.Zad2.ConsumerFair;
import Lab04.Zad2.ConsumerNaive;
import Lab04.Zad2.ProducerFair;
import Lab04.Zad2.ProducerNaive;
import Lab04.Zad2.Statistics;

public class Lab04 {
    public static void main(String[] args) throws InterruptedException {
        if (args[0].equals("stre"))
            streamProcessing();
        else if (args[0].equals("naiv"))
            naiveProcessing();
        else if (args[0].equals("fair"))
            fairProcessing();
        else if (args[0].equals("comp"))
            compare();
    }

    private static void streamProcessing() throws InterruptedException {
        int max = 10;
        int producerNumber = 1;
        int processorNumber = 10;
        int consumerNumber = 1;
        int expState = 0;

        Buffer buffer = new Buffer(max, producerNumber + processorNumber + consumerNumber);
        List<Producer> producers = new ArrayList<>();
        List<Consumer> consumers = new ArrayList<>();
        List<Processor> processors = new ArrayList<>();

        for (Integer i = 0; i < producerNumber; i++) {
            Producer prod = new Producer(buffer, "Producer" + i.toString(), -1, 0);
            producers.add(prod);
        }

        for (Integer i = 0; i < processorNumber; i++) {
            Processor proc = new Processor(buffer, "Processor" + i.toString(), expState, expState + 1);
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
        naiveProcessing(10, 10, 10, -1);     // jeśli portion times > 0 to dla statystyk robimy, jeśli nie to w nieskończoność
    }

    private static void fairProcessing() throws InterruptedException {
        fairProcessing(10, 10, 10, -1);     // jeśli portion times > 0 to dla statystyk robimy, jeśli nie to w nieskończoność
    }

    private static void naiveProcessing(int producerNumber, int consumerNumber, int M, int portionTimes) throws InterruptedException {
        BufferNaive bufferNaive = new BufferNaive(2 * M, portionTimes);

        List<ProducerNaive> producers = new ArrayList<>();
        List<ConsumerNaive> consumers = new ArrayList<>();

        for (Integer i = 0; i < producerNumber; i++) {
            ProducerNaive prod = new ProducerNaive(bufferNaive, "Producer" + i.toString(), portionTimes, producerNumber);
            producers.add(prod);
        }

        for (Integer i = 0; i < consumerNumber; i++) {
            ConsumerNaive cons = new ConsumerNaive(bufferNaive, "Customer" + i.toString(), portionTimes, consumerNumber);
            consumers.add(cons);
        }

        for (Thread t : producers) {
            t.start();
        }
        for (Thread t : consumers) {
            t.start();
        }

        for (Thread t : producers) {
            t.join();
        }
        for (Thread t : consumers) {
            t.join();
        }
    }

    private static void fairProcessing(int producerNumber, int consumerNumber, int M, int portionTimes) throws InterruptedException {
        BufferFair bufferFair = new BufferFair(2 * M, portionTimes);

        List<ProducerFair> producers = new ArrayList<>();
        List<ConsumerFair> consumers = new ArrayList<>();

        for (Integer i = 0; i < producerNumber; i++) {
            ProducerFair prod = new ProducerFair(bufferFair, "Producer" + i.toString(), portionTimes, producerNumber);
            producers.add(prod);
        }

        for (Integer i = 0; i < consumerNumber; i++) {
            ConsumerFair cons = new ConsumerFair(bufferFair, "Customer" + i.toString(), portionTimes, consumerNumber);
            consumers.add(cons);
        }

        for (Thread t : producers) {
            t.start();
        }
        for (Thread t : consumers) {
            t.start();
        }

        for (Thread t : producers) {
            t.join();
        }
        for (Thread t : consumers) {
            t.join();
        }
    }

    private static void compare() {
        Statistics statistics = new Statistics();
        
        int maxM = 1000000;
        int maxPC = 1000;

        for (int M = 10; M <= maxM; M *= 10) {
            for (int PC = 10; PC < maxPC; PC *= 10) {

            }
        }
    }
}
