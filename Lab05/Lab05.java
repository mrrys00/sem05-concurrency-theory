package Lab05;

import java.util.concurrent.ExecutionException;

import Lab05.Zad2.Statistics;
import Lab05.Zad1.Mandelbrot;
import Lab05.Zad2.TaskExecutor;

public class Lab05 {

    private static int width = 8000;
    private static int height = 6000;

    private static int coresNumber = 6;
    private static int threadsNumber = 12;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        if (args[0].equals("mand")) {
            mandelbrot();
        } else if (args[0].equals("equa")) {
            tasksEqualThreads();
        } else if (args[0].equals("tent")) {
            tenTimesThreadsTasks();
        } else if (args[0].equals("pixe")) {
            taskPerPixel();
        } else if (args[0].equals("stat")) {
            statistics();
        }
    }

    private static void mandelbrot() {
        new Mandelbrot().setVisible(true);
    }

    private static void tasksEqualThreads() throws InterruptedException, ExecutionException {
        tasksEqualThreads(true);
    }
    private static void tenTimesThreadsTasks() throws InterruptedException, ExecutionException {
        tenTimesThreadsTasks(true);
    }
    private static void taskPerPixel() throws InterruptedException, ExecutionException {
        taskPerPixel(true);
    }

    private static void tasksEqualThreads(boolean print) throws InterruptedException, ExecutionException {
        // https://www.intel.com/content/www/us/en/products/sku/208921/intel-core-i71165g7-processor-12m-cache-up-to-4-70-ghz-with-ipu/specifications.html
        // https://www.amd.com/en/products/cpu/amd-ryzen-5-2600x
        // int coresNumber = 6;
        // int threadsNumber = 12;
        int tasksNumber = threadsNumber;

        TaskExecutor taskExecutor = new TaskExecutor(100, 100, width, height, threadsNumber);

        taskExecutor.taskPartitioner(tasksNumber);
        if (print) taskExecutor.setVisible(true);
    }

    private static void tenTimesThreadsTasks(boolean print) throws InterruptedException, ExecutionException {
        // int coresNumber = 6;
        // int threadsNumber = 12;

        int tasksNumber = 10 * threadsNumber;

        TaskExecutor taskExecutor = new TaskExecutor(100, 100, width, height, threadsNumber);

        taskExecutor.taskPartitioner(tasksNumber);
        if (print) taskExecutor.setVisible(true);
    }

    private static void taskPerPixel(boolean print) throws InterruptedException, ExecutionException {
        // int coresNumber = 6;
        // int threadsNumber = 12;

        int tasksNumber = width * height;

        TaskExecutor taskExecutor = new TaskExecutor(100, 100, width, height, threadsNumber);

        taskExecutor.taskPartitioner(tasksNumber);
        if (print) taskExecutor.setVisible(true);
    }

    private static void statistics() throws InterruptedException, ExecutionException {
        Statistics statistics = new Statistics();
        for (int i = 0; i < 10; i++) {
            long ts = statistics.getNanoStartTimestamp();
            tasksEqualThreads(false);
            statistics.tasksEqualThreadsArr[i] = statistics.getNanoDuration(ts);
            System.out.println("equal tasks" + i);

            ts = statistics.getNanoStartTimestamp();
            tenTimesThreadsTasks(false);
            statistics.tenTimesThreadsTasksArr[i] = statistics.getNanoDuration(ts);
            System.out.println("ten times tasks" + i);

            ts = statistics.getNanoStartTimestamp();
            taskPerPixel(false);
            statistics.taskPerPixelArr[i] = statistics.getNanoDuration(ts);
            System.out.println("task per pixel" + i);
        }

        statistics.table();
        // intel cores 4, threads 8, 800x600, max iter 2137, zoom 412
        // [4.798440399E9, 1.624682400075806E8] partial results
        // [4.808465218E9, 2.2187682763821125E8] partial results
        // [4.865385843E9, 2.0782224450917628E8] partial results

        // AMD cored 6, threads 12

    }
}
