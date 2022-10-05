public class SynchronizedCounter implements Runnable {
    private int variable;

    public SynchronizedCounter() {
        this.variable = 0;
    }

    @Override
    public void run() {     // wyrzucić poza
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                if (Thread.currentThread().getName().equals("inc")) {
                    this.incrementor();
                } else {
                    this.decrementor();
                }
            }
        }
    }

    public void incrementor() {
        this.variable++;
        // System.out.println(Thread.currentThread().getName() + " " + this.getVariable());
    }

    public void decrementor() {
        this.variable--;
        // System.out.println(Thread.currentThread().getName() + " " + this.getVariable());
    }

    public int getVariable() {
        return this.variable;
    }

    @Override
    public String toString() {
        return String.valueOf(this.variable);
    }
}
