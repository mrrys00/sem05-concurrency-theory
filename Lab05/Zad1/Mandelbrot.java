package Lab05.Zad1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Mandelbrot extends JFrame {

    private final int MAX_ITER = 570;
    private final double ZOOM = 150;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;

    public Mandelbrot(int... b) {
        super("Mandelbrot Set");

        int boundX = 100;
        int boundY = 100;
        int width = 800;
        int height = 600;

        if (b.length == 4) {
            boundX = b[0];
            boundY = b[1];
            width = b[2];
            height = b[3];
        }
        
        setBounds(boundX, boundY, width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = zy = 0;
                cX = (x - getWidth()/2) / ZOOM;
                cY = (y - getHeight()/2) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 16 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y, iter | (iter << 8));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

}
