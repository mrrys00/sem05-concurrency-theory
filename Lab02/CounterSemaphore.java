public class CounterSemaphore {
    private int counter;
    // https://www.infoworld.com/article/2077413/semaphore.html
    
    public CounterSemaphore() {
        this(0);
    }
    public CounterSemaphore(int i) {
        if (i < 0) throw new IllegalArgumentException(i + " < 0");
        counter = i;
    }
    
    public synchronized void release() {
        if (counter == 0) {
            this.notify();
        }
        counter++;
    }

    public synchronized void acquire() throws InterruptedException {
        while (counter == 0) {
            this.wait();
        }
        counter--;
    }
}
