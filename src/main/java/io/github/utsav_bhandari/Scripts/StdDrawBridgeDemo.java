package io.github.utsav_bhandari.Scripts;

import edu.princeton.cs.algs4.StdDraw;
import io.github.utsav_bhandari.Lib.StdDrawBridge;

import java.awt.*;

public class StdDrawBridgeDemo {
    public static void main(String[] args) {
        StdDrawBridge.init(1920, 1080);

        final double[] angle = {0};

        StdDrawBridge.addCallback((g) -> {
            drawRotatingCylinder(angle[0], g);

            angle[0] += 0.05;
        });

        StdDrawBridge.run();
    }

    private static void drawRotatingCylinder(double angle, Graphics2D g) {
        int numCylinders = 500;
        double angleIncrement = 2 * Math.PI / numCylinders;


        g.setColor(Color.PINK);
        for (int i = 0; i < 1920; i += 100) {
            for (int j = 0; j < 1080; j += 100) {
                g.drawString("(" + i + ", " + j + ")", i, j);
            }
        }
        g.fillRect(0, 0, 10, 10);

        g.setColor(Color.CYAN);
        for (int i = 0; i < 1920; i += 100) {
            for (int j = 0; j < 1080; j += 100) {
                StdDraw.text(i, j, "(" + i + ", " + j + ")");
            }
        }

        StdDraw.filledRectangle(0, 0, 5, 5);

        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(5));


//        g.drawRect(0, 0, 960, 540);
        g.setColor(Color.getHSBColor((float) angle / 4.0f, 1f, 0.5f));
//        StdDraw.rectangle(960, 540, 480, 270);

        for (int i = 0; i < numCylinders; i++) {
            double x = 960 + Math.cos(0.5 * angle + i * angleIncrement) * (270 + (i % 10 * 20));
            double y = 540 + Math.sin(0.5 * angle + i * angleIncrement) * (270 + (i % 10 * 20));
//            g.drawOval((int) x - 50, (int) y - 50, 100, 100);
//            StdDraw.circle((int) x, (int) y, 50);
            x = 960 + Math.cos(-angle + i * angleIncrement) * (250 + -(i % 10 * 20));
            y = 540 + Math.sin(-angle + i * angleIncrement) * (250 + -(i % 10 * 20));
//            g.drawOval((int) x - 50, (int) y - 50, 100, 100);
            StdDraw.circle((int) x, (int) y, 50);
        }
    }
}
