package Lab03.Zad1;

public class ConsumerV2 extends Thread {
    private BoundedBuffer buffer;
    private String name;

    public ConsumerV2(BoundedBuffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    public void run() {

        while(true) {
            try {
                String message = String.valueOf(buffer.take());
                System.out.println("Consumer " + this.name + " consumed message " + message);
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
            }
        }

    }
}
