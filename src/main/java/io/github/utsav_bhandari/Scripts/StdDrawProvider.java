package io.github.utsav_bhandari.Scripts;

import io.github.utsav_bhandari.Lib.StdDrawBridge;

import java.awt.*;
import java.util.function.Consumer;

public class StdDrawProvider {
    public static void run(Consumer<Graphics2D> cb) {
        StdDrawBridge.init(1920, 1080);

        StdDrawBridge.addCallback(cb);

        StdDrawBridge.run();
    }
}
