package io.github.utsav_bhandari.Engine;

import java.util.HashMap;

public class PlayerKeymap {
    private final HashMap<Integer, Action> map = new HashMap<>();

    public Object getAction(int keyCode) {
        if (map.containsKey(keyCode)) {
            return map.get(keyCode);
        }

        return null;
    }

    public enum Action {
        MOVE_LEFT,
        MOVE_RIGHT,
        SELECT,
        HELP,
    }

    public PlayerKeymap setKey(int keyCode, Action action) {
        map.put(keyCode, action);
        return this;
    }

    /**
     * Be careful using this because for special characters and modifier keys it might not be what you expect
     * <br>
     * Make sure to test it using KeyHandlerDemo first
     */
    public PlayerKeymap setKey(char keyChar, Action action) {
        map.put((int) keyChar, action);
        return this;
    }
}
