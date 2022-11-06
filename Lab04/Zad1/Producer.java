package Lab04.Zad1;

public class Producer extends Thread {
    private Buffer buffer;
    private String name;

    public Producer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    public void run() {

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
