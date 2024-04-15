package io.github.utsav_bhandari.Scripts;

import io.github.utsav_bhandari.Engine.KeyboardInputHandler;
import io.github.utsav_bhandari.Lib.StdDrawBridge;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Function;

public class KeyHandlerDemo {

    public static void main(String[] args) {
        StdDrawProvider.init();

        var kh = new KeyboardInputHandler();

        var ref = new Object() {
            String out = "";
        };
        
        var keys = new ArrayList<Function<KeyEvent, Boolean>>();
        
        keys.add(KeyboardInputHandler.createKeyHandler('a', () -> {
            ref.out += "<SPECIAL A>";
        }));

        keys.add(e -> {
            ref.out += "<" + e.getKeyChar() + ">";

            return false;
        });

        kh.addKeymap("MAIN", keys);

        kh.useKeymap("MAIN");

        StdDrawBridge.frame.addKeyListener(kh);

        var font = new Font("Arial", Font.PLAIN, 50);

        StdDrawProvider.run(g -> {
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(ref.out, 100, 100);
        });

    }
}
