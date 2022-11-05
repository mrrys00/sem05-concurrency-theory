package Lab03.Zad3;

public class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for(int i = 0;  i < 10;   i++) {
            try {
                buffer.put("msg " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}