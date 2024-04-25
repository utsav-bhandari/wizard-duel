package io.github.utsav_bhandari.Engine;

public class TurnEventHook {
    public final AttackEvent attackEvent;
    public final World world;

    private boolean isCancelled = false;

    public TurnEventHook(AttackEvent attackEvent, World world) {
        this.attackEvent = attackEvent;
        this.world = world;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        isCancelled = true;
    }
}
