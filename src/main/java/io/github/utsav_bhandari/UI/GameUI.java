package io.github.utsav_bhandari.UI;

import edu.princeton.cs.algs4.StdDraw;
import io.github.utsav_bhandari.Engine.KeyboardInputHandler;
import io.github.utsav_bhandari.Game;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Function;

import static io.github.utsav_bhandari.Lib.Util.*;

public class GameUI implements IRenderable {
    private final Game game;

    private String visibleText = "";

    public GameUI(Game game) {
        this.game = game;

        var titleScreenHandlers = new ArrayList<Function<KeyEvent, Boolean>>();

        Runnable enterPress = () -> {
            visibleText = "Loading...";

            unsafeWait(1000);

            game.setGameState(Game.GameState.GAME_RUNNING);
        };

        titleScreenHandlers.add(KeyboardInputHandler.createKeyHandler('a', () -> {
            runSyncThread(enterPress);
        }));

        // Configure keyboard input handler
        var keyboardInputHandler = new KeyboardInputHandler();
        keyboardInputHandler.addKeymap(
                "TITLE_SCREEN",
                titleScreenHandlers
        );

        keyboardInputHandler.useKeymap("TITLE_SCREEN");

        game.frame.addKeyListener(keyboardInputHandler);

        resetText();
    }

    private void resetText() {
        visibleText = "WizardDuel";
    }

    public void render(Graphics2D g) {
        var r = game.resource;

        if (game.getGameState() == Game.GameState.TITLE_SCREEN) {
            g.drawImage(r.titleScreen, 0, 0, 1920, 200, null);

            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setFont(new Font("Arial", Font.PLAIN, 50));
            StdDraw.text(960, 100, visibleText);
        } else if (game.getGameState() == Game.GameState.GAME_RUNNING) {
            // Draw game
        } else if (game.getGameState() == Game.GameState.GAME_OVER) {
            // Draw game over
        }
    }
}
