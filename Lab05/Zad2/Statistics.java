package Lab05.Zad2;

import java.util.Arrays;

public class Statistics {
    public long[] tasksEqualThreadsArr = new long[10];
    public long[] tenTimesThreadsTasksArr = new long[10];
    public long[] taskPerPixelArr = new long[10];

    // time measurement
    public long getNanoStartTimestamp() {
        return System.nanoTime();
    }

    public long getNanoDuration(long startTimestamp) {
        return System.nanoTime() - startTimestamp;
    }

    private static double[] arrayStats(long[] array) {
        long sum = 0;
        for (long i : array) {
            sum += i;
        }

        int length = array.length;
        double mean = sum / length;
    
        double standardDeviation = 0.0;
        for (double num : array) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        
        double[] res = new double[2];
        res[0] = mean;
        res[1] = Math.sqrt(standardDeviation / length);

        return res;
    }

    public double[] statsTasksEqualThreadsArr() {
      return arrayStats(tasksEqualThreadsArr);
    }    
    public double[] statsTenTimesThreadsTasksArr() {
      return arrayStats(tenTimesThreadsTasksArr);
    }
    public double[] statsTaskPerPixelArr() {
      return arrayStats(taskPerPixelArr);
    }

    public void table() {
        System.out.println(Arrays.toString(statsTasksEqualThreadsArr()));        
        System.out.println(Arrays.toString(statsTenTimesThreadsTasksArr()));
        System.out.println(Arrays.toString(statsTaskPerPixelArr()));
    }
}
