public class RaceThread extends Thread implements Runnable {
    private boolean isIncrementor;
    private CounterV2 cnt;
    private int repeats;

    RaceThread(boolean isInc, CounterV2 cnt) {
        this.isIncrementor = isInc;
        this.cnt = cnt;
        this.repeats = 10;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeats; i++) {
            if (isIncrementor) {
                cnt.incrementor();
            } else {
                cnt.decrementor();
            }
            System.out.println(cnt);
        }
    }
}
