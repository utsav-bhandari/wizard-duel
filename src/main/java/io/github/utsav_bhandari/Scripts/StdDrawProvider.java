package io.github.utsav_bhandari.Scripts;

import io.github.utsav_bhandari.Lib.StdDrawBridge;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public final class StdDrawProvider {
    private static boolean initialized = false;
    private static boolean running = false;
    public static JFrame frame;

    public static void init() {
        if (!initialized) {
            StdDrawBridge.init(1920, 1080);
            frame = StdDrawBridge.frame;
            initialized = true;
        }
    }

    public static void run(Consumer<Graphics2D> cb) {
        StdDrawBridge.addCallback(cb);

        if (running) {
            return;
        }

        init();


        StdDrawBridge.run();

        running = true;
    }
}
