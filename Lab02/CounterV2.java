public class CounterV2 {
    private int variable;
    BinarySemaphore binarySemaphore = new BinarySemaphore(true);

    public CounterV2() {
        this.variable = 0;
    }

    public void incrementor() {
        binarySemaphore.lock();
        this.variable++;
        binarySemaphore.unlock();
        // System.out.println(Thread.currentThread().getName() + " " +
        // this.getVariable());
    }

    public void decrementor() {
        binarySemaphore.lock();
        this.variable--;
        binarySemaphore.unlock();
        // System.out.println(Thread.currentThread().getName() + " " +
        // this.getVariable());
    }

    @Override
    public String toString() {
        return String.valueOf(this.variable);
    }
}
