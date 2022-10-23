package Lab03;

import java.util.ArrayList;
import java.util.List;

import Lab03.Zad1.BoundedBuffer;
import Lab03.Zad1.ProducerV2;
import Lab03.Zad1.ConsumerV2;
import Lab03.Zad2.Client;
import Lab03.Zad2.PrinterMonitor;

public class Lab03 {
    public static void main(String[] args) throws InterruptedException {
        if (args[0].equals("prod"))
            producerConsumer();
        else if (args[0].equals("prin"))
            printers();
        else if (args[0].equals("tabl"))
            doubleTable();
    }

    private static void producerConsumer() throws InterruptedException {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        Integer producerNumber = 5;
        Integer consumerNumber = 4;
        List<ProducerV2> producers = new ArrayList<>();
        List<ConsumerV2> consumers = new ArrayList<>();

        for (Integer i = 0; i < producerNumber; i++) {
            ProducerV2 prod = new ProducerV2(boundedBuffer, "Producer" + i.toString());
            producers.add(prod);
        }

        for (Integer i = 0; i < consumerNumber; i++) {
            ConsumerV2 cons = new ConsumerV2(boundedBuffer, "Customer" + i.toString());
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

    private static void printers() throws InterruptedException {
        Integer N = 53;
        Integer M = 18;
        PrinterMonitor printerMonitor = new PrinterMonitor(M);
        List<Client> clients = new ArrayList<>();

        for (Integer i = 0; i < N; i++) {
            Client client = new Client("Client" + String.valueOf(i), printerMonitor);
            clients.add(client);
        }

        for (Thread t : clients) {
            t.start();
        }
        for (Thread t : clients) {
            t.join();
        }
    }

    private static void doubleTable() throws InterruptedException {

    }
}
