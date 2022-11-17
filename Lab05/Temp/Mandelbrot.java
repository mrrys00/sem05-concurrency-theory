package Lab05.Temp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class Mandelbrot implements Callable<List<List<Double>>> {

    private static final int MAX_ITERATIONS = 5000;

    private final double xSkip;
    private final double ySkip;
    private final double x0;
    private final double y0;
    private final int pixelWidth;
    private final int pixelHeight;

    public Mandelbrot(int pixelWidth, int pixelHeight, double startX, double endX, double startY, double endY) {
        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
        this.x0 = startX;
        this.xSkip = (endX - startX) / pixelWidth;
        this.y0 = startY;
        this.ySkip = (endY - startY) / pixelHeight;
    }

    @Override
    public List<List<Double>> call() throws Exception {
        List<List<Double>> pixels = new ArrayList<>();
        for (int y = 0; y < pixelHeight; y++) {
            List<Double> row = new ArrayList<>();
            for (int x = 0; x < pixelWidth; x++) {
                row.add(calculatePixel(x, y));
            }
            pixels.add(row);
        }
        System.out.println("Finished mandelbrot at y = " + y0);
        return pixels;
    }

    public double calculatePixel(int pixelX, int pixelY) {
        double x = x0 + pixelX * xSkip;
        double y = y0 + pixelY * ySkip;
        double ix = 0;
        double iy = 0;
        int iteration = 0;
        while (ix * ix + iy * iy < 4 && iteration < MAX_ITERATIONS) {
            double xtemp = ix * ix - iy * iy + x;
            iy = 2 * ix * iy + y;
            ix = xtemp;
            iteration++;
        }
        return Math.log(iteration) / Math.log(MAX_ITERATIONS);
    }
}
