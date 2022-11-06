public class MyThread extends Thread {
    private boolean isIncrementor, isSynchronized;
    private Counter cnt;
    private int repeats;

    MyThread(boolean isInc, boolean isSync, Counter cnt) {
        this.isIncrementor = isInc;
        this.isSynchronized = isSync;
        this.cnt = cnt;
        this.repeats = 10000;
    }

    @Override
    public void run() {
        if (isSynchronized) {
            synchronized (Counter.class) {
                for (int i = 0; i < repeats; i++) {
                    if (isIncrementor) {
                        cnt.incrementor();
                    } else {
                        cnt.decrementor();
                    }
                }
            }
        } else {
            for (int i = 0; i < repeats; i++) {
                if (isIncrementor) {
                    cnt.incrementor();
                } else {
                    cnt.decrementor();
                }
            }
        }
    }
}
