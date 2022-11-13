package Lab04.Zad1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Buffer {
    final private Lock lock = new ReentrantLock();
    final private int MAX = 1000;
    
    private int buffer[] = new int[MAX];
    final private Condition[] conditions = new Condition[MAX];
    private int M;

    public Buffer(int M, int conditionsNumber) {
        this.M = M;
        for (int i=0; i < this.M; i++) {
            buffer[i] = -1;
            conditions[i] = lock.newCondition();
        }
    }

    private void bufPrinter() {
        for( int x = 0; x < this.M; x++ ) {
            System.out.print(this.buffer[x]);
            System.out.print(" ");
        }
        System.out.println();
    }

    private void signaller(int nextIdx) {
        for ( int i = this.M-1; i > -1; i--) {
            conditions[(i+nextIdx)%this.M].signal();
        }
    }

    private int stateChanger(int idx, int expProcessState, int nextPorcessState) throws InterruptedException {
        lock.lock();
        try {
            int nextIdx = idx+1;
            if (nextIdx == this.M) nextIdx = 0;
            while (this.buffer[idx] != expProcessState)
                conditions[idx].await();        // na tej linijce ustawić breakpointa jeśli chcemy zobaczyć, że kod działa naprawdę a nie na słowo honoru
            this.buffer[idx] = nextPorcessState;
            signaller(nextIdx);
            return nextIdx;
        } finally {
            lock.unlock();
            this.bufPrinter();
        }
    }

    public int take(int idx, int expProcessState, int nextPorcessState) throws InterruptedException {
        return stateChanger(idx, expProcessState, nextPorcessState);
    }

    public int process(int idx, int expProcessState, int nextPorcessState)  throws InterruptedException {
        return stateChanger(idx, expProcessState, nextPorcessState);
    }

    public int put(int idx, int expProcessState, int nextPorcessState) throws InterruptedException {
        return stateChanger(idx, expProcessState, nextPorcessState);
    }
}
