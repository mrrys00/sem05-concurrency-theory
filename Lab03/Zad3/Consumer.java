package Lab03.Zad3;

public class Consumer implements Runnable {
    private Buffer buffer;
    private int max;

    public Consumer(Buffer buffer, int max) {
        this.buffer = buffer;
        this.max = max;
    }

    public void run() {
        for(int i = 0;  i < this.max;   i++) {
            try {
                String message = buffer.take();
                System.out.println(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
