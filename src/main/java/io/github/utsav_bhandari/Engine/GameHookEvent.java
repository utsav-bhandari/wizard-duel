package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Game;

public class GameHookEvent {
    public final AttackEvent attackEvent;
    public final Game game;

    private boolean isCancelled = false;

    public GameHookEvent(AttackEvent attackEvent, Game game) {
        this.attackEvent = attackEvent;
        this.game = game;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        isCancelled = true;
    }
}
