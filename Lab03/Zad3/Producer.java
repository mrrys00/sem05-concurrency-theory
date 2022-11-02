package Lab03.Zad3;

public class Producer implements Runnable {
    private Buffer buffer;
    private int max;

    public Producer(Buffer buffer, int max) {
        this.buffer = buffer;
        this.max = max;
    }

    public void run() {
        for(int i = 0;  i < this.max;   i++) {
            try {
                buffer.put("msg " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
