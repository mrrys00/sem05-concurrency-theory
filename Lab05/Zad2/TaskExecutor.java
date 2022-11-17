package Lab05.Zad2;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskExecutor {
    private ExecutorService threadsPool;
    private Set<Future<Integer>> set = new HashSet<Future<Integer>>();

    public TaskExecutor(int threadsNumber) {
        this.threadsPool = Executors.newFixedThreadPool(threadsNumber); 
    }

    public taskPartitioner(int tasksNumber) {
        
    }

    // time measurement
    public long getNanoStartTimestamp() {
        return System.nanoTime();
    }

    public long getNanoDuration(long startTimestamp) {
        return System.nanoTime() - startTimestamp;
    }
}
