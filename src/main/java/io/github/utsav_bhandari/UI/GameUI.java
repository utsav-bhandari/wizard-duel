package io.github.utsav_bhandari.UI;

import edu.princeton.cs.algs4.StdDraw;
import io.github.utsav_bhandari.Engine.KeyboardInputHandler;
import io.github.utsav_bhandari.Engine.Player;
import io.github.utsav_bhandari.Engine.SpellCard.ASpellCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ATextEffectCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ITextEffectCard;
import io.github.utsav_bhandari.Engine.World;
import io.github.utsav_bhandari.Game;
import io.github.utsav_bhandari.Lib.StdDrawBridge;
import io.github.utsav_bhandari.Render.IRenderable;
import io.github.utsav_bhandari.Scripts.StdDrawProvider;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Function;

import static io.github.utsav_bhandari.Engine.PlayerKeymap.Action;
import static io.github.utsav_bhandari.Lib.Util.*;

public class GameUI implements IRenderable {
    private final Game game;

    private final DebugOverlay debugOverlay;
    private final HelpOverlay[] helpOverlays;

    private final Font splashScreenFont = new Font("Arial", Font.PLAIN, 100);

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

        helpOverlays = new HelpOverlay[game.world.players.length];
        for (int i = 0; i < game.world.players.length; i++) {
            helpOverlays[i] = new HelpOverlay(i);
        }
    }

    private static ArrayList<Function<KeyEvent, Boolean>> getGameScreenHandlers(Game game) {
        var gameScreenHandlers = new ArrayList<Function<KeyEvent, Boolean>>();

        gameScreenHandlers.add(e -> {
            for (int i = 0; i < game.world.players.length; i++) {
                var p = game.world.players[i];

                var km = p.getKeymap();

                var action = km.getAction(e.getKeyCode());

                if (action == null) continue;

                if (action == Action.HELP) p.toggleHelp();

                if (game.world.getWorldState() >= io.github.utsav_bhandari.Engine.World.WORLD_STATE_SELECTION_STARTED
                        && game.world.getWorldState() < io.github.utsav_bhandari.Engine.World.WORLD_STATE_ON_TURN
                ) {
                    switch (action) {
                        case Action.MOVE_LEFT -> p.moveChoice(-1);
                        case Action.MOVE_RIGHT -> p.moveChoice(1);
                        case Action.SELECT -> p.confirmChoice();
                        default -> {}
                    }

                    game.world.notifyUi();
                }

                if (game.world.getWorldState() >= io.github.utsav_bhandari.Engine.World.WORLD_STATE_ON_CHARGE_PROMPT
                        && game.world.getWorldState() < io.github.utsav_bhandari.Engine.World.WORLD_STATE_ON_TURN) {
                    //
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

            for (var p : game.world.players) {
                p.render(g);
            }


            Player[] players = game.world.players;
            for (int i = 0; i < players.length; i++) {
                Player p = players[i];
                int sxPosition = 0;
                int txPosition = 0;
                var spellCard = p.getCurrentSpellCard();
                if (i == 0) {
                    sxPosition = 50;
                    txPosition = 64;
                } else if (i == 1) {
                    sxPosition = StdDrawBridge.width - 242;
                txPosition = (StdDrawBridge.width / 2);
                }
                if (spellCard != null) {
                    if (spellCard instanceof ASpellCard aSpellCard) {
                        aSpellCard.x = sxPosition;
                        aSpellCard.y = 850;
                        spellCard.render(g);
                    } else {
                        throw new RuntimeException();
                    }
                }
                var textEffectCard = p.textEffectCards;
                for (int j = 0; j < textEffectCard.size(); j++) {
                    int off = j * (64 + 192);
                    ITextEffectCard tec = textEffectCard.get(j);
                    if (tec instanceof ATextEffectCard aTextEffectCard) {
                        aTextEffectCard.x = txPosition + off;
                        aTextEffectCard.y = 200;
                        tec.render(g);
                    } else {
                        throw new RuntimeException();
                    }
                }

            }

            if (game.world.getWorldState() >= World.WORLD_STATE_SELECTION_STARTED
                    && game.world.getWorldState() < World.WORLD_STATE_ON_TURN) {
                var round = game.world.getCurrentRound();
                for (int i = 0; i < players.length; i++) {
                    var p = players[i];

                    int off = i * StdDrawBridge.width / 2;


                    var selection = round.getPlayerSelection(p);

                    if (selection == null || p.isViewingHelp()) continue;

                    g.translate(off, 0);
                    selection.render(g);
                    g.translate(-off, 0);
                }
            }
            if (game.world.getWorldState() >= World.WORLD_STATE_ON_SPELL_PRIME
                    && game.world.getWorldState() < World.WORLD_STATE_TURN_ENDED) {
                // render spell
                var turn = game.world.getCurrentTurn();
                if (turn != null) {
                    turn.attacker.getCurrentSpellCard().renderSpell(g);
                }
            }
            for (int i = 0; i < players.length; i++) {
                var p = players[i];
                if (p.isViewingHelp()) {
                    helpOverlays[i].render(g);
                }
            }

            if (game.world.splashScreenText != null) {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.setFont(splashScreenFont);
                StdDraw.text((double) StdDrawBridge.width / 2, (double) StdDrawBridge.height / 2, game.world.splashScreenText);
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
