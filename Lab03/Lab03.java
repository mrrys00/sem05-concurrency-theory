package Lab03;

import java.util.ArrayList;
import java.util.List;

import Lab03.Zad1.BoundedBuffer;
import Lab03.Zad1.ProducerV2;
import Lab03.Zad1.ConsumerV2;

import Lab03.Zad2.Client;
import Lab03.Zad2.PrinterMonitor;

import Lab03.Zad3.RestaurantClient;
import Lab03.Zad3.WaiterMonitor;
import Lab03.Zad3.Consumer;
import Lab03.Zad3.Buffer;
import Lab03.Zad3.Producer;

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
        // int max = 100;
        // Buffer buffer = new Buffer();
        // Producer producer = new Producer(buffer);
        // Consumer consumer = new Consumer(buffer);

        // Thread producerThread = new Thread(producer);
        // Thread consumerThread = new Thread(consumer);

        // producerThread.start();
        // consumerThread.start();
        // try {
        //     consumerThread.join();
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        
        Integer pairsNumber = 5;
        WaiterMonitor waiterMonitor = new WaiterMonitor(pairsNumber);
        List<RestaurantClient> restaurantClients = new ArrayList<>();

        for (Integer i = 0; i < pairsNumber; i++) {
            RestaurantClient restaurantClientF = new RestaurantClient(waiterMonitor, "female", i);
            RestaurantClient restaurantClientM = new RestaurantClient(waiterMonitor, "male", i);
            restaurantClients.add(restaurantClientF);
            restaurantClients.add(restaurantClientM);
        }

        // jedna condition queue na parę 

        // idea rozwiązania jest taka, że zainicjowana nullami tablica przechowuje na odpowiednich
        // indeksach (indeks = ID klienta) informację czy ktoś z pary klientów ubiega się o rezerwację 
        // stolika; jeśli tak i stolik jest wolny to dajemy im ilość czasu na zjedzenie; jeśli nie to 
        // oczekuje na zgłoszenie się drugiego wątka z pary
        // po zjedzeniu obydwa wątki zwalniają stolik, żeby nie doszło do sytuacji, w której
        // jeden z nich blokuje cały czas stolik nie dopuszczając innych par do rezerwacji  

        for (Thread t : restaurantClients) {
            t.start();
        }
        for (Thread t : restaurantClients) {
            t.join();
        }
    }
}
