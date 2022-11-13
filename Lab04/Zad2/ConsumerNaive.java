package Lab04.Zad2;

import java.util.Random;

public class ConsumerNaive extends Thread {
    private BufferNaive bufferNaive;
    private String name;
    private int bufferSize;
    private int iterations;
    private int portionTimes;

    public ConsumerNaive(BufferNaive bufferNaive, String name, int portionTimes, int consumerNumber) {
        this.bufferNaive = bufferNaive;
        this.name = name;
        this.portionTimes = portionTimes;
        this.bufferSize = bufferNaive.getBufferSize() / 2;
        this.iterations = bufferSize * portionTimes / consumerNumber;
    }

    public void run() {
        if (this.portionTimes > 0) {
            while (this.iterations > 0) {
                var elementsNumber = new Random().nextInt(this.bufferSize);
                try {
                    sleep(new Random().nextInt(200));
                    bufferNaive.get(name, elementsNumber);
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
                    bufferNaive.get(name, elementsNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
