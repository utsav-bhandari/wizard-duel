package io.github.utsav_bhandari.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class KeyboardInputHandler implements KeyListener {
    protected final HashMap<String, Iterable<Function<KeyEvent, Boolean>>> keyMap = new HashMap<>();

    protected String currentKeymap = null;

    // Implement the keyPressed method
    @Override
    public void keyPressed(KeyEvent e) {
        if (currentKeymap == null) {
            return;
        }

        var keyHandlers = keyMap.get(currentKeymap);

        if (keyHandlers == null) {
            return;
        }

        for (var handler : keyHandlers) {
            if (handler.apply(e)) break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void addKeymap(String keymap, Iterable<Function<KeyEvent, Boolean>> handlers) {
        keyMap.put(keymap, handlers);
    }

    public void useKeymap(String keymap) {
        currentKeymap = keymap;
    }

    /**
     * Utility method
     */
    public static Function<KeyEvent, Boolean> createKeyHandler(char key, Runnable action) {
        return (e) -> {
            if (e.getKeyChar() == key) {
                action.run();
            }

            return false;
        };
    }
}
