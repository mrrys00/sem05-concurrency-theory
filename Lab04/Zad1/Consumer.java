package Lab04.Zad1;

public class Consumer extends Thread {
    private Buffer buffer;
    private String name;

    public Consumer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    public void run() {

        while(true) {
            try {
                String message = buffer.take();
                System.out.println("Consumer " + this.name + " consumed message " + message);
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
            }
        }

    }
}
