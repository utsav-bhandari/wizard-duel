package io.github.utsav_bhandari.Lib;

import edu.princeton.cs.algs4.StdDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.function.Consumer;

public class StdDrawBridge {
    public static JFrame frame;
    ;
    private static ArrayList<Consumer<Graphics2D>> callbacks = new ArrayList<>();
    public static Graphics2D screen;

    private static int frameCounter = 0;

    private static int width;
    private static int height;

    private static int frameCounterDisplay = 0;
    private static final Timer frameCounterTimer = new Timer(1000, e -> {
        frameCounterDisplay = frameCounter;
        frameCounter = 0;
    });

    private static Timer timer;

    public static void init(int width, int height) {
        if (frame != null) {
            throw new IllegalStateException(".init() can only be called once");
        }

        StdDrawBridge.width = width;
        StdDrawBridge.height = height;

        // Hack #1. Give correct dimensions to StdDraw, so it doesn't mess with the frame
        // Unfortunately, there will be a initial window flicker and this cannot be disabled
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);

        // Hack #1.5. Invert Y axis because StdDraw uses top-left as origin
        StdDraw.setYscale(height, 0);

        // Hack #2. Enable double buffering in order to better optimize draw cycle
        StdDraw.enableDoubleBuffering();

        // Hack #3. Create custom offscreen buffer
        var offscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        screen = offscreenImage.createGraphics();
        try {
            // Hack #4. Expose frame for future use
            frame = (JFrame) getField(StdDraw.class, "frame").get(null);

            // Hack #5. Dispose screens because we don't need to use this
            ((Graphics2D) getField(StdDraw.class, "onscreen").get(null)).dispose();
            var offScreenField = (Field) getField(StdDraw.class, "offscreen");
            ((Graphics2D) offScreenField.get(null)).dispose();

            // Hack #6. Stub offscreen with our custom image buffer
            offScreenField.set(null, screen);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        // Hack #7. Use non-retina Image for faster rendering (duh)
        frame.setContentPane(new JLabel(new ImageIcon(offscreenImage)));

        // Hack #8. Add rendering hints back
//        screen.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        screen.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        screen.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        // cuz text is special
        screen.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Remove menubar because it's ugly
        frame.setJMenuBar(null);

        // Trigger reflow. This is the final reflow and application will never change size
        frame.pack();

        JRootPane root = frame.getRootPane();
        root.setDoubleBuffered(true);

        Toolkit tk = root.getToolkit();

        var systemFont = new Font("Arial", Font.PLAIN, 12);

        timer = new Timer(0, e -> {
            screen.setBackground(Color.WHITE);
            screen.clearRect(0, 0, frame.getWidth(), frame.getHeight());

            for (Consumer<Graphics2D> cb : callbacks) {
                cb.accept(screen);
            }

            screen.setFont(systemFont);
            screen.setColor(Color.BLACK);
            screen.drawString("FPS: " + frameCounterDisplay, 0, 12);

            frame.repaint();
            frameCounter++;
        });

        timer.setCoalesce(true);
    }

    private static Field getField(Class<?> cls, String fieldName) {
        for (Class<?> c = cls; c != null; c = c.getSuperclass()) {
            try {
                final Field field = c.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (final NoSuchFieldException e) {
                // Try parent
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Cannot access field " + cls.getName() + "." + fieldName, e);
            }
        }
        throw new IllegalArgumentException(
                "Cannot find field " + cls.getName() + "." + fieldName);
    }

    public static void addCallback(Consumer<Graphics2D> cb) {
        callbacks.add(cb);
    }

    public static void run() {
        frameCounterTimer.start();
        timer.start();
    }

    public static void stop() {
        frameCounterTimer.stop();
        timer.stop();
    }

    public int getFramesPerSecond() {
        return frameCounterDisplay;
    }
}
