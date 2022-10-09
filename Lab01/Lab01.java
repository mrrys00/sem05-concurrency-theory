
public class Lab01 {

    public static void main(String[] args) throws InterruptedException {
        if (args[0].equals("not")) notSynchronizedSolution();
        else if (args[0].equals("sync")) synchronizedSolution();
        else if (args[0].equals("prod")) producerConsumer();
    }

    private static void notSynchronizedSolution() throws InterruptedException {
        Counter counterObject = new Counter();
        Thread inc = new MyThread(true, false, counterObject);
        Thread dec = new MyThread(false, false, counterObject);

        inc.start();
        dec.start();

        inc.join();
        dec.join();

        System.out.println(counterObject);
    }

    private static void synchronizedSolution() throws InterruptedException {
        Counter counterObject = new Counter();
        Thread inc = new MyThread(true, true, counterObject);
        Thread dec = new MyThread(false, true, counterObject);

        inc.start();
        dec.start();

        inc.join();
        dec.join();

        System.out.println(counterObject);
    }

    private static void producerConsumer() throws InterruptedException {
        Buffer buffer = new Buffer();

        Consumer cons0 = new Consumer(buffer, "0");
        Consumer cons1 = new Consumer(buffer, "1");
        Consumer cons2 = new Consumer(buffer, "2");
        Consumer cons3 = new Consumer(buffer, "3");

        Producer prod0 = new Producer(buffer, "0");
        Producer prod1 = new Producer(buffer, "1");
        Producer prod2 = new Producer(buffer, "2");
        Producer prod3 = new Producer(buffer, "3");

        prod0.start();
        prod1.start();
        prod2.start();
        prod3.start();

        cons0.start();
        cons1.start();
        cons2.start();
        cons3.start();

        prod0.join();
        prod1.join();
        prod2.join();
        prod3.join();

        cons0.join();
        cons1.join();
        cons2.join();
        cons3.join();

    }

}
