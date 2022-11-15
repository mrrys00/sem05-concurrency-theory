package Lab04.Zad1;

public class Producer extends Thread {
    private Buffer buffer;
    private String name;
    private int currentIdx;
    private int expPorcessState;
    private int nextPorcessState;

    public Producer(Buffer buffer, String name, int expPorcessState, int nextPorcessState) {
        this.buffer = buffer;
        this.name = name;
        this.expPorcessState = expPorcessState;
        this.nextPorcessState = nextPorcessState;
        this.currentIdx = 0;
    }

    public void run() {

        while(true) {
            try {
                System.out.println("Producer " + this.name + " try to produce message on idx" + this.currentIdx );
                this.currentIdx = buffer.put(this.currentIdx, this.expPorcessState, this.nextPorcessState);
                System.out.println("Producer " + this.name + " produced message on idx" + this.currentIdx );
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
            }
        }

    }
}
