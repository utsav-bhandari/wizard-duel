package io.github.utsav_bhandari;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Engine.World;
import io.github.utsav_bhandari.UI.GameUI;

import javax.swing.*;
import java.awt.*;

public final class Game {

    public final JFrame frame;
    private GameState currentGameState = GameState.TITLE_SCREEN;

    public enum GameState {
        TITLE_SCREEN,
        GAME_RUNNING,
        GAME_OVER
    }

    private final World world;
    private final GameUI ui;
    public final Resource resource;

    public Game(JFrame frame) {
        this.frame = frame;

        resource = Resource.getInstance();
        world = new World(this);
        ui = new GameUI(this);
        // TODO
    }


    public void update() {
        // UI does not update.
        // Some games have mechanisms where UI is also included in update cycle but
        // for ours, it is not.
        world.update();
    }

    public void render(Graphics2D g) {
        // UI should handle world rendering
        ui.render(g);
    }

    public GameState getGameState() {
        return currentGameState;
    }

    public void setGameState(GameState gameState) {
        System.out.println("Game state changed to " + gameState);
        currentGameState = gameState;
    }
}
