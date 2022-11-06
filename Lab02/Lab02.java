import java.util.ArrayList;
import java.util.List;

public class Lab02 {
    public static void main(String[] args) throws InterruptedException {
        if (args[0].equals("bin")) binarySemaphore();
        else if (args[0].equals("cnt")) counterSemaphore();
    }

    public static void binarySemaphore() throws InterruptedException{
        CounterV2 counterObject = new CounterV2();
        Thread inc = new RaceThread(true, counterObject);
        Thread dec = new RaceThread(false, counterObject);

        inc.start();
        dec.start();

        inc.join();
        dec.join();

        System.out.println(counterObject);
    }

    public static void counterSemaphore() throws InterruptedException {
        Integer cartsNumber = 5;
        CounterSemaphore selfShop = new CounterSemaphore(cartsNumber);
        List<Thread> customers = new ArrayList<>();
        Integer customerNumber = 50;
        for (Integer i=0; i < customerNumber; i++) {
            Thread cust = new Customer("Customer"+i.toString(), selfShop);
            customers.add(cust);
        } 

        for (Thread t : customers) {
            t.start();
        }

        for (Thread t : customers) {
            t.join();
        }

    }
}
