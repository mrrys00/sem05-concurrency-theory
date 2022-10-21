public class CounterSemaphore {
    private int counter;
    private int max;
    // https://www.infoworld.com/article/2077413/semaphore.html
    
    // max ilość koszyków i jeśli spadnie poniżej 0 to wait
    public CounterSemaphore() {
        this(10);
    }
    public CounterSemaphore(int max) {
        this.counter = 0;
        if (max <= 0) throw new IllegalArgumentException(max + " < 0");
        this.max = max;
    }
    
    public synchronized void release() {        // cart taken
        while (counter == max) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.notifyAll();
        counter++;
    }

    public synchronized void acquire() {        // cart put
        while (counter == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.notifyAll();
        counter--;
    }
}
