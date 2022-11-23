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
        int tasksNumber = threadsNumber;

        TaskExecutor taskExecutor = new TaskExecutor(100, 100, width, height, threadsNumber);

        taskExecutor.taskPartitioner(tasksNumber);
        if (print) taskExecutor.setVisible(true);
    }

    private static void tenTimesThreadsTasks(boolean print) throws InterruptedException, ExecutionException {
        int tasksNumber = 10 * threadsNumber;

        TaskExecutor taskExecutor = new TaskExecutor(100, 100, width, height, threadsNumber);

        taskExecutor.taskPartitioner(tasksNumber);
        if (print) taskExecutor.setVisible(true);
    }

    private static void taskPerPixel(boolean print) throws InterruptedException, ExecutionException {
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
    }
}
