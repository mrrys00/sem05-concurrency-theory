
public class Main {

    public static void main(String[] args) throws InterruptedException {
        if (args[0].equals("not")) notSynchronizedSolution();
        else if (args[0].equals("sync")) synchronizedSolution();
        // else if (args[0].equals("prod"))
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

    }

}
