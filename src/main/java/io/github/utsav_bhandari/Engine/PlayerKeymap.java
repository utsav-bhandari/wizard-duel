package io.github.utsav_bhandari.Engine;

import java.util.HashMap;

public class PlayerKeymap {
    private final HashMap<Action, Integer> map = new HashMap<>();

    public enum Action {
        MOVE_LEFT,
        MOVE_RIGHT,
        SELECT,
        HELP,
    }

    public PlayerKeymap setKey(Action action, int keyCode) {
        map.put(action, keyCode);
        return this;
    }

    /**
     * Be careful using this because for special characters and modifier keys it might not be what you expect
     * <br>
     * Make sure to test it using KeyHandlerDemo first
     */
    public PlayerKeymap setKey(Action action, char keyChar) {
        map.put(action, (int) keyChar);
        return this;
    }
}
