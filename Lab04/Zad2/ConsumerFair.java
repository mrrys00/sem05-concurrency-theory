package Lab04.Zad2;

import java.util.Random;

public class ConsumerFair extends Thread {
    private BufferFair bufferFair;
    private String name;
    private int bufferSize;

    public ConsumerFair(BufferFair bufferFair, String name) {
        this.bufferFair = bufferFair;
        this.name = name;
        this.bufferSize = bufferFair.getBufferSize() / 2;
    }

    public void run() {
        while(true) {
            var elementsNumber = new Random().nextInt(this.bufferSize);
            try {
                sleep(new Random().nextInt(200));
                bufferFair.get(name, elementsNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
