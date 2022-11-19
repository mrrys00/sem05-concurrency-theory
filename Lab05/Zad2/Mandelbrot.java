package Lab05.Zad2;

import java.util.concurrent.Callable;

public class Mandelbrot implements Callable {

    private double zx, zy, cX, cY, tmp;
    private int MAX_ITER;
    private double ZOOM;

    private int width;
    private int height;
    private int x;
    private int y;

    public Mandelbrot(int width, int height, int x, int y, int maxIter, double zoom) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.MAX_ITER = maxIter;
        this.ZOOM = zoom;
    }

    @Override
    public Integer call() throws Exception {
        zx = zy = 0;
        cX = (this.x - this.width/2) / ZOOM;
        cY = (this.y - this.height/2) / ZOOM;
        int iter = MAX_ITER;
        while (zx * zx + zy * zy < 16 && iter > 0) {
            tmp = zx * zx - zy * zy + cX;
            zy = 2.0 * zx * zy + cY;
            zx = tmp;
            iter--;
        }
        return iter;
    }

}
