public class BinarySemaphore {
    private boolean state;
    // true - released; false - acquired
    public BinarySemaphore() {
        this(true);
    }

    public BinarySemaphore(boolean state) {
        this.state = state;
    }

    public synchronized void release() {
        if (state) {
            this.notify(); // or wait?
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
