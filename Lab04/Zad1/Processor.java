package Lab04.Zad1;

public class Processor extends Thread {
    private Buffer buffer;
    private String name;
    private int currentIdx;
    private int expPorcessState;
    private int nextPorcessState;

    public Processor(Buffer buffer, String name, int expPorcessState, int nextPorcessState) {
        this.buffer = buffer;
        this.name = name;
        this.expPorcessState = expPorcessState;
        this.nextPorcessState = nextPorcessState;
        this.currentIdx = 0;
    }

    public void run() {

        while(true) {
            try {
                System.out.println("Processor " + this.name + " try to process message on idx" + this.currentIdx );
                this.currentIdx = buffer.process(this.currentIdx, this.expPorcessState, this.nextPorcessState);
                System.out.println("Processor " + this.name + " processed message on idx" + this.currentIdx );
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
            }
        }

    }
    
}
