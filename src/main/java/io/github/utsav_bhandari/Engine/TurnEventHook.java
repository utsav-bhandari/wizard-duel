package io.github.utsav_bhandari.Engine;

public class TurnEventHook {
    public final AttackEvent attackEvent;
    public final World world;

    public final Turn turn;
    private boolean isCancelled = false;

    public TurnEventHook(AttackEvent attackEvent, World world, Turn turn) {
        this.attackEvent = attackEvent;
        this.world = world;
        this.turn = turn;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        isCancelled = true;
    }
}
