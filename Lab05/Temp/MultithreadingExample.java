package Lab05.Temp;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultithreadingExample {

    private static final int WIDTH = 128;
    private static final int HEIGHT = 8;
    private static final int THREADS = 8;

    private static final double STARTX = -1.5;
    private static final double ENDX = 0.5;
    private static final double STARTY = -1;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Instant start = Instant.now();
        double width = ENDX - STARTX;
        double height = width * ((double)HEIGHT/WIDTH) * 2.0;

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);

        List<Future<List<List<Double>>>> futures = new ArrayList<>();

        for (int thread = 0; thread < THREADS; thread++) {
            double threadStartY = STARTY + thread * height;
            double threadEndY = STARTY + (thread+1) * height;
            futures.add(executorService.submit(new Mandelbrot(WIDTH, HEIGHT, STARTX, ENDX, threadStartY, threadEndY)));
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        List<List<List<Double>>> allMandelbrots = new ArrayList<>();
        for (Future<List<List<Double>>> future : futures) {
            allMandelbrots.add(future.get());
        }
        drawMandelbrots(allMandelbrots);
        System.out.println("Took " + start.until(Instant.now(), ChronoUnit.MILLIS) + "ms");
    }

    private static void drawMandelbrots(List<List<List<Double>>> allMandelbrots) {
        double minIntensity = calcMin(allMandelbrots);
        double maxIntensity = calcMax(allMandelbrots);

        final String[] palette = " .:*|VFNM".split("");
        for (List<List<Double>> pixels : allMandelbrots) {
            for (int y = 0; y < pixels.size(); y++) {
                StringBuilder output = new StringBuilder();
                List<Double> xs = pixels.get(y);
                for (int x = 0; x < xs.size(); x++) {
                    double intensity = (xs.get(x) - minIntensity) / (maxIntensity - minIntensity);
                    int idx = Math.min(palette.length - 1, (int) (palette.length * intensity));
                    output.append(palette[idx]);
                }
                System.out.println(output.toString());
            }
        }
    }

    private static double calcMin(List<List<List<Double>>> allMandelbrots) {
        double minIntensity = 1;
        for (List<List<Double>> pixels : allMandelbrots) {
            for (List<Double> row : pixels) {
                for (double col : row) {
                    minIntensity = Math.min(minIntensity, col);
                }
            }
        }
        return minIntensity;
    }

    private static double calcMax(List<List<List<Double>>> allMandelbrots) {
        double maxIntensity = 1;
        for (List<List<Double>> pixels : allMandelbrots) {
            for (List<Double> row : pixels) {
                for (double col : row) {
                    maxIntensity = Math.max(maxIntensity, col);
                }
            }
        }
        return maxIntensity;
    }
}
