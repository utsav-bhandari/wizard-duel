package io.github.utsav_bhandari.Engine;

import java.util.ArrayList;
import java.util.List;

public class Round {
    public final List<Turn> turns = new ArrayList<>();
    private final World world;

    public Round(World world) {
        this.world = world;
    }

    public void run() {
        world.setWorldState(World.WORLD_STATE_ON_ROUND);

        // Do stuff

        world.setWorldState(World.WORLD_STATE_ROUND_ENDED);
    }

    public Turn getCurrentTurn() {
        return turns.getLast();
    }

    public void update() {
        // TODO Implement
    }
}
