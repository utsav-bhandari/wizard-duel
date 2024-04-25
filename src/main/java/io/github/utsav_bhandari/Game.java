package io.github.utsav_bhandari;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.UI.GameUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class Game {

    public final JFrame frame;
    private final Thread worldThread;
    private GameState currentGameState = GameState.TITLE_SCREEN;
    private final List<Consumer<GameState>> gameStateUpdateChannels = new ArrayList<>();

    public void update() {
        world.update();
    }

    public enum GameState {
        TITLE_SCREEN,
        GAME_RUNNING,
        GAME_OVER
    }

    public final io.github.utsav_bhandari.Engine.World world;
    private final GameUI ui;
    public final Resource resource;

    public Game(JFrame frame) {
        this.frame = frame;

        resource = Resource.getInstance();
        world = new io.github.utsav_bhandari.Engine.World(this);
        ui = new GameUI(this);

        worldThread = new Thread(() -> {
            setGameState(GameState.GAME_RUNNING);
            try {
                world.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Game ended unexpectedly");
            }
            setGameState(GameState.GAME_OVER);
        });
    }

    public void startGame() {
        if (currentGameState != GameState.TITLE_SCREEN) {
            throw new IllegalStateException("Game can only be started from title screen");
        }
        worldThread.start();
    }

    public void render(Graphics2D g) {
        // UI should handle world rendering
        ui.render(g);
    }

    public GameState getGameState() {
        return currentGameState;
    }

    private void setGameState(GameState gameState) {
        System.out.println("Game state changed to " + gameState);
        gameStateUpdateChannels.forEach(c -> c.accept(gameState));
        currentGameState = gameState;
    }

    public void onGameStateUpdate(Consumer<GameState> callback) {
        gameStateUpdateChannels.add(callback);
    }

    public Thread getWorldThread() {
        return worldThread;
    }
}
