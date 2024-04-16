package io.github.utsav_bhandari;

import io.github.utsav_bhandari.Lib.StdDrawBridge;

public class Main {
    public static void main(String[] args) {
        StdDrawBridge.init(1920, 1080);

        var game = new Game(StdDrawBridge.frame);

        StdDrawBridge.addCallback(g -> {
            // rendering and updating at the same time
            game.update();
            game.render(g);
        });

        StdDrawBridge.run();
    }
}