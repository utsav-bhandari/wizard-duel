package io.github.utsav_bhandari.Scripts;

import io.github.utsav_bhandari.Engine.KeyboardInputHandler;
import io.github.utsav_bhandari.Lib.StdDrawBridge;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Function;

public class KeyHandlerAndGameLockDemo extends Thread {
    public static void main(String[] args) {
        StdDrawProvider.init();

        var kh = new KeyboardInputHandler();

        var ref = new Object() {
            String out = "";
            boolean quit = false;
        };

        var ref2 = new Object() {
            String out = "";
        };

        // Keys section

        var keys = new ArrayList<Function<KeyEvent, Boolean>>();

        keys.add(KeyboardInputHandler.createKeyHandler('a', () -> {
            synchronized (ref) {
                ref.out += "A";
            }
        }));

        keys.add(KeyboardInputHandler.createKeyHandler('q', () -> {
            ref.quit = true;
        }));

        kh.addKeymap("MAIN", keys);

        kh.useKeymap("MAIN");

        StdDrawBridge.frame.addKeyListener(kh);

        var font = new Font("Arial", Font.PLAIN, 50);

        StdDrawProvider.run(g -> {
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(ref.out, 100, 100);
            g.drawString(ref2.out, 100, 200);
        });

        // Game section

        while (!ref.quit) {
            // Wait for a key press
            kh.waitKeyEvent(KeyboardInputHandler.KEY_PRESSED);
            synchronized (ref) {
                ref.out += "!";
            }
            ref2.out += ".";
        }
    }
}
