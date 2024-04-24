package io.github.utsav_bhandari.UI;

import edu.princeton.cs.algs4.StdDraw;
import io.github.utsav_bhandari.Engine.KeyboardInputHandler;
import io.github.utsav_bhandari.Engine.Player;
import io.github.utsav_bhandari.Engine.PlayerKeymap;
import io.github.utsav_bhandari.Engine.World;
import io.github.utsav_bhandari.Game;
import io.github.utsav_bhandari.Lib.StdDrawBridge;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Function;

import static io.github.utsav_bhandari.Engine.PlayerKeymap.Action;
import static io.github.utsav_bhandari.Lib.Util.*;

public class GameUI implements IRenderable {
    private final Game game;

    private final DebugOverlay debugOverlay;

    private String visibleText = "";

    public GameUI(Game game) {
        this.game = game;
        this.debugOverlay = new DebugOverlay(game);

        var titleScreenHandlers = new ArrayList<Function<KeyEvent, Boolean>>();

        Runnable enterPress = () -> {
            visibleText = "Loading...";

            unsafeWait(100);

            game.startGame();
        };

        titleScreenHandlers.add(KeyboardInputHandler.createKeyHandler('a', () -> {
            runSyncThread(enterPress);
        }));


        var gameScreenHandlers = getGameScreenHandlers(game);

        // Configure keyboard input handler
        var keyboardInputHandler = new KeyboardInputHandler();

        keyboardInputHandler.addKeymap(
                "TITLE_SCREEN",
                titleScreenHandlers
        );
        keyboardInputHandler.addKeymap(
                "GAME_SCREEN",
                gameScreenHandlers
        );

        keyboardInputHandler.useKeymap("TITLE_SCREEN");

        game.frame.addKeyListener(keyboardInputHandler);

        game.onGameStateUpdate(gameState -> {
            if (gameState == Game.GameState.TITLE_SCREEN) {
                keyboardInputHandler.useKeymap("TITLE_SCREEN");
            } else if (gameState == Game.GameState.GAME_RUNNING) {
                keyboardInputHandler.useKeymap("GAME_SCREEN");
            }
        });

        resetText();
    }

    private static ArrayList<Function<KeyEvent, Boolean>> getGameScreenHandlers(Game game) {
        var gameScreenHandlers = new ArrayList<Function<KeyEvent, Boolean>>();

        gameScreenHandlers.add(e -> {
            if (game.world.getWorldState() >= World.WORLD_STATE_SELECTION_STARTED
                    && game.world.getWorldState() < World.WORLD_STATE_ON_TURN
            ) {
                var round = game.world.getCurrentRound();

                for (int i = 0; i < game.world.players.length; i++) {
                    var p = game.world.players[i];

                    var km = p.getKeymap();

                    var action = km.getAction(e.getKeyCode());

                    if (action == null) continue;

                    switch (action) {
                        case Action.MOVE_LEFT -> p.moveChoice(-1);
                        case Action.MOVE_RIGHT -> p.moveChoice(1);
                        case Action.SELECT -> p.confirmChoice();
                        case Action.HELP -> p.toggleHelp();
                        default -> {}
                    }

                    game.world.notifyUi();
                }
            }

            return false;
        });
        return gameScreenHandlers;
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
            g.drawImage(r.gameBackground, 0, 0, 1920, 1080, null);

            if (game.world.getWorldState() >= World.WORLD_STATE_SELECTION_STARTED
                    && game.world.getWorldState() < World.WORLD_STATE_ON_TURN) {
                var round = game.world.getCurrentRound();

                Player[] players = game.world.players;
                for (int i = 0; i < players.length; i++) {
                    var p = players[i];

                    int off = i * StdDrawBridge.width / 2;

                    g.translate(off, 0);

                    var selection = round.getPlayerSelection(p);

                    if (selection == null) continue;

                    selection.render(g);

                    g.translate(-off, 0);
                }
            }
        } else if (game.getGameState() == Game.GameState.GAME_OVER) {
            g.drawImage(r.titleScreen, 0, 0, 1920, 200, null);

            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setFont(new Font("Arial", Font.PLAIN, 50));

            StdDraw.text(960, 100, "GAME OVER");
        }

        debugOverlay.render(g);
    }
}
