package Lab03.Zad1;

public class ProducerV2 extends Thread {
    private BoundedBuffer buffer;
    private String name;

    public ProducerV2(BoundedBuffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    public void run() {

        // for (int i = 0; i < 10; i++)
        Integer i = 0;
        while(true) {
            String message = "Message no. " + Integer.toString(i) + " from " + this.name;
            try {
                buffer.put(message);
                System.out.println("Producer " + this.name + " produced message " + message);
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
            }
            i += 1;
        }

    }
}
