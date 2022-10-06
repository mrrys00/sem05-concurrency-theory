public class Counter {
    private int variable;

    public Counter() {
        this.variable = 0;
    }

    public void incrementor() {
        this.variable++;
        // System.out.println(Thread.currentThread().getName() + " " + this.getVariable());
    }

    public void decrementor() {
        this.variable--;
        // System.out.println(Thread.currentThread().getName() + " " + this.getVariable());
    }

    @Override
    public String toString() {
        return String.valueOf(this.variable);
    }
}
