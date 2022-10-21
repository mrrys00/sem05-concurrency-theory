public class BinarySemaphore {
    private boolean unlocked;
    // true - unlocked - 1; false - locked - 0
    public BinarySemaphore() {
        this(true);
    }

    public BinarySemaphore(boolean unlocked) {
        this.unlocked = unlocked;
        System.out.println("Semaphore initialized …");
    }

    public synchronized void unlock() {
        if (unlocked) {
            this.notify();
        }
        this.unlocked = true;
        System.out.println("Semaphore unlocked …");
    }

    public synchronized void lock() {
        while (!unlocked) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.unlocked = false;
        System.out.println("Semaphore locked …");
    }

    public boolean getState() {
        return this.unlocked;
    }
}
