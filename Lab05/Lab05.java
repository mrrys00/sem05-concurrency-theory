package Lab05;

import Lab05.Zad1.Mandelbrot;

public class Lab05 {
    public static void main(String[] args) {
        if (args[0].equals("mand")) {
            mandelbrot();
        }
    }

    private static void mandelbrot() {
        new Mandelbrot().setVisible(true);
    }

    private static void tasksEqualThreads() {
        // https://www.intel.com/content/www/us/en/products/sku/208921/intel-core-i71165g7-processor-12m-cache-up-to-4-70-ghz-with-ipu/specifications.html
        int coresNumber = 4;
        int threadsNumber = 8;

    }

    private static void tenTimesThreadsTasks() {
        int coresNumber = 4;
        int threadsNumber = 8;

        // 10 * threadsNumber
    }

    private static void taskPerPixel() {
        int width = 800;
        int height = 600;
    }

    private static void statistics() {
        double[] tasksEqualThreadsArr = new double[10];
        double[] tenTimesThreadsTasksArr = new double[10];
        double[] taskPerPixelArr = new double[10];
        // może tutaj metodki na pomiar czasu władować?
    }
}
