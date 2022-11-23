package Lab05.Zad2;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class TaskExecutor extends JFrame {
    private ExecutorService threadsPool;

    private final int MAX_ITER = 2137;
    private final double ZOOM = 412;
    private BufferedImage I;

    private int width;
    private int height;

    public TaskExecutor(int boundX, int boundY, int width, int height, int threadsNumber) {

        super("Mandelbrot Set");

        this.width = width;
        this.height = height;

        setBounds(boundX, boundY, width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        this.threadsPool = Executors.newFixedThreadPool(threadsNumber);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    public void taskPartitioner(int tasksNumber) throws InterruptedException, ExecutionException {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                Callable<Object> callable = new Mandelbrot(this.width, this.height, x, y, MAX_ITER, ZOOM);
                Future<Object> future = threadsPool.submit(callable);
                int iter = (int) future.get();
                I.setRGB(x, y, iter | (iter << 8));
            }
        }

        threadsPool.shutdown();
    }
}
