package io.github.utsav_bhandari.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.function.Function;

public class KeyboardInputHandler implements KeyListener {
    protected final HashMap<String, Iterable<Function<KeyEvent, Boolean>>> keyMap = new HashMap<>();

    protected String currentKeymap = null;

    public static final int KEY_PRESSED = 1 << 0;
    public static final int KEY_RELEASED = 1 << 1;
    public static final int KEY_TYPED = 1 << 2;

    public static final int KEY_ANY = KEY_PRESSED | KEY_RELEASED | KEY_TYPED;

    protected final Object keyLock = new Object();
    protected int keyEventPending = 0;
    private KeyEvent lastKey;

    /**
     * May return null in case it was INTERRUPTED
     */
    public KeyEvent waitKeyEvent() {
        waitKeyEvent(KEY_PRESSED);
        var t = lastKey;
        lastKey = null;
        return t;
    }

    public void interruptAnyWaitKey() {
        synchronized (keyLock) {
            keyLock.notify();
        }
    }
    // To future me
    // I am not smart enough to understand whether it is better to do the keyEvent checking
    // before the notify call or right here.
    // I am no-where near capable of understanding the JVM internals on how this is implemented and what
    // the overhead is.
    // All I know is that this event will be triggered 3 times regardless of the key event type
    // Just don't get confused later on
    public void waitKeyEvent(int keyEventType) {
        keyEventPending = 0;
        synchronized (keyLock) {
            while ((keyEventPending & keyEventType) == 0) {
                try {
                    keyLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        keyEventPending = 0;
    }

    protected void notifyLock(int keyEventType, KeyEvent e) {
        keyEventPending |= keyEventType;
        lastKey = e;
        synchronized (keyLock) {
            keyLock.notify();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (currentKeymap == null) {
            return;
        }

        var keyHandlers = keyMap.get(currentKeymap);

        if (keyHandlers != null) {
            for (var handler : keyHandlers) {
                if (handler.apply(e)) return;
            }
        }

        notifyLock(KEY_PRESSED, e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        notifyLock(KEY_RELEASED, e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        notifyLock(KEY_TYPED, e);
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
