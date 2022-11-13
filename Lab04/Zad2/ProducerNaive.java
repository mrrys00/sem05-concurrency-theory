package Lab04.Zad2;

import java.util.Random;

public class ProducerNaive extends Thread {
    private BufferNaive bufferNaive;
    private String name;
    private int bufferSize;

    public ProducerNaive(BufferNaive bufferNaive, String name) {
        this.bufferNaive = bufferNaive;
        this.name = name;
        this.bufferSize = bufferNaive.getBufferSize() / 2;
    }

    public void run() {
        while(true) {
            var elementsNumber = new Random().nextInt(this.bufferSize);
            try {
                sleep(new Random().nextInt(200));
                bufferNaive.put(name, elementsNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
