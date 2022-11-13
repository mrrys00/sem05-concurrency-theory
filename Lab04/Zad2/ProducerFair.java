package Lab04.Zad2;

import java.util.Random;

public class ProducerFair extends Thread {
    private BufferFair bufferFair;
    private String name;
    private int bufferSize;
    private int iterations;
    private int portionTimes;

    public ProducerFair(BufferFair bufferFair, String name, int portionTimes, int producerNumber) {
        this.bufferFair = bufferFair;
        this.name = name;
        this.portionTimes = portionTimes;
        this.bufferSize = bufferFair.getBufferSize() / 2;
        this.iterations = bufferSize * portionTimes / producerNumber;
    }

    public void run() {
        if (this.portionTimes > 0) {
            while (this.iterations > 0) {
                var elementsNumber = new Random().nextInt(this.bufferSize);
                try {
                    sleep(new Random().nextInt(200));
                    bufferFair.put(name, elementsNumber);
                    this.iterations--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            while (true) {
                var elementsNumber = new Random().nextInt(this.bufferSize);
                try {
                    sleep(new Random().nextInt(200));
                    bufferFair.put(name, elementsNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
