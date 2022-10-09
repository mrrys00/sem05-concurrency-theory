public class Producer extends Thread {
    private Buffer buffer;
    private String name;

    public Producer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    public void run() {

        for (int i = 0; i < 10; i++) {
            String message = "message " + Integer.toString(i);
            buffer.put(message);
            System.out.println("Producer " + this.name + " produced message " + message);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }

    }
}
