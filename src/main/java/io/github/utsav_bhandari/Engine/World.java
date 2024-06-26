package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.*;
import io.github.utsav_bhandari.Engine.TextEffectCard.*;
import io.github.utsav_bhandari.Game;
import io.github.utsav_bhandari.Lib.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static io.github.utsav_bhandari.Lib.Util.cloneArray;

public class World {
    public final Game game;

    public final List<Round> rounds = new ArrayList<>();

    public final Player[] players = new Player[2];

    public String debugStatus = "";

    public String splashScreenText = null;

    /*
     * Internal docs:
     *
     * format should be WORLD_STATE_ON_..._<VERB> or WORLD_STATE_..._<VERB PAST> or WORLD_STATE_..._ENDED
     * WORLD_STATE_ON_..._<VERB> is before the action is performed
     *
     * Not the cleanest nor my proudest implementation, but any additional complexity will make this project
     * akin to building on top of a game engine, so I have spared the sanity for some spaghetti code
     *
     * Event is cancellable
     *
     * WORLD_STATE_..._<VERB PAST> is after the action is performed
     *
     * Event is not cancellable. Used to observe the result of an event/consume itself.
     *
     * WORLD_STATE_..._ENDED is after the event is done. Similar to VERB PAST but for events that contain more events
     * such as round ending
     *
     * Make sure the indices are fired in order. i.e. game state always increases
     */

    // cancellable flag
    private static final int _WORLD_STATE_MODIFIER_CANCELLABLE = 1;

    // number of internal modifiers
    private static final int _WORLD_STATE_MODIFIER_WIDTH = 1;
    private int worldState;
    private Player winner;
    private boolean gameEnded = false;
    private final Object updateLock = new Object();
    private final Object uiLock = new Object();

    private static int worldStateOn(int idx) {
        return idx << _WORLD_STATE_MODIFIER_WIDTH | _WORLD_STATE_MODIFIER_CANCELLABLE;
    }

    private static int worldStateDoneHook(int idx) {
        return idx << _WORLD_STATE_MODIFIER_WIDTH;
    }

    public static boolean isWorldStateCancellable(int gameState) {
        return (gameState & _WORLD_STATE_MODIFIER_CANCELLABLE) != 0;
    }

    public static final int WORLD_STATE_ON_ROUND = worldStateOn(0);
    public static final int WORLD_STATE_SELECTION_STARTED = worldStateOn(50);

    public static final int WORLD_STATE_ON_CHARGE_ADD = worldStateOn(70);
    public static final int WORLD_STATE_CHARGE_ADDED = worldStateDoneHook(100);
    public static final int WORLD_STATE_ON_TURN = worldStateOn(200);

    // TODO add more i guess
    public static final int WORLD_STATE_ON_SPELL_PRIME = worldStateOn(300);
    public static final int WORLD_STATE_SPELL_PRIMED = worldStateOn(350);
    public static final int WORLD_STATE_ON_CHARGE_PROMPT = worldStateDoneHook(400);
    public static final int WORLD_STATE_ON_CAST = worldStateOn(500);

    public static final int WORLD_STATE_TURN_ENDED = worldStateOn(1000);
    public static final int WORLD_STATE_ROUND_ENDED = worldStateOn(2000);
    public static final int WORLD_STATE_WORLD_ENDED = worldStateOn(5000);

    public boolean shouldRestart = false;

    public void setWorldState(int worldState) {
        int tmp = this.worldState;
        this.worldState = worldState;

        System.out.println("World state changed from " + tmp + " to " + worldState);
    }

    public int getWorldState() {
        return worldState;
    }

    public World(Game game) {
        this.game = game;

        players[0] = new Player(0);
        players[1] = new Player(1);

        players[0].world = this;
        players[1].world = this;

        players[0].x = 200;
        players[0].y = 720; // was 520

        players[1].x = 1720;
        players[1].y = 720; // was 520

        var km = new PlayerKeymap();

        km.setKey('A', PlayerKeymap.Action.MOVE_LEFT);
        km.setKey('D', PlayerKeymap.Action.MOVE_RIGHT);
        km.setKey('Z', PlayerKeymap.Action.HELP);
        km.setKey('E', PlayerKeymap.Action.SELECT);

        players[0].setKeymap(km);

        km = new PlayerKeymap();
        km.setKey('J', PlayerKeymap.Action.MOVE_LEFT);
        km.setKey('L', PlayerKeymap.Action.MOVE_RIGHT);
        km.setKey('H', PlayerKeymap.Action.HELP);
        km.setKey('M', PlayerKeymap.Action.SELECT);

        players[1].setKeymap(km);

        var spellCardFactory = new ArrayList<Supplier<ISpellCard>>();

//        spellCardFactory.add(ThePowerOfExample::new);

        spellCardFactory.add(ArcaneFlurry::new);
        spellCardFactory.add(ArcaneQuota::new);
        spellCardFactory.add(ChargeCascade::new);
        spellCardFactory.add(EtherealCyclone::new);
        spellCardFactory.add(InfernalCircle::new);
        spellCardFactory.add(NullifyingGlyphs::new);
        spellCardFactory.add(TempestReversal::new);
        spellCardFactory.add(VirulentEruption::new);

        var textEffectCardFactory = new ArrayList<Supplier<ITextEffectCard>>();
        textEffectCardFactory.add(ArcaneDrainage::new);
        textEffectCardFactory.add(ArcaneInterference::new);
        textEffectCardFactory.add(ChargeDifferential::new);
        textEffectCardFactory.add(ChargeSurge::new);
        textEffectCardFactory.add(EyeOfTheStorm::new);
        textEffectCardFactory.add(HealingIncantation::new);
        textEffectCardFactory.add(Judgment::new);
        textEffectCardFactory.add(SiphoningEnchantment::new);

        for (var player : players) {
            Collections.shuffle(spellCardFactory);
            Collections.shuffle(textEffectCardFactory);
            for (int i = 0; i < 8; i++) {
                var sc = spellCardFactory.get(i % spellCardFactory.size()).get();
                var te = textEffectCardFactory.get(i % textEffectCardFactory.size()).get();
                sc.setWorld(this);
                te.setWorld(this);

                player.spellCardPile.add(sc);
                player.textEffectCardPile.add(te);
            }
        }
    }

    /**
     * Blocking
     */
    public void run() throws InterruptedException {
        while (!gameEnded) {
            var round = new Round(this);
            rounds.add(round);

            int roundIdx = rounds.size();

            System.out.println("Round " + rounds.size() + " started");

            splashScreenText("Round " + roundIdx + " started");

            round.run();

            System.out.println("Round " + rounds.size() + " ended");
        }

        setWorldState(WORLD_STATE_WORLD_ENDED);

        Util.unsafeWait(2000);

        if (winner == null) {
            splashScreenText("Game ended in a draw");
        } else {
            splashScreenText("Player " + (winner.getId() + 1) + " wins");
        }

        splashScreenText = "Press A to play again";

        while (!shouldRestart) {
            waitForUi();
        }
    }

    /**
     * Stops until the UI "frees" the world to run again
     */
    public void waitForUi() {
        try {
            synchronized (uiLock) {
                uiLock.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitUpdate() {
        try {
            synchronized (updateLock) {
                updateLock.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void notifyUi() {
        synchronized (uiLock) {
            uiLock.notify();
        }
    }

    public void splashScreenText(String text) {
        splashScreenText = text;

        Util.unsafeWait(1000);

        splashScreenText = null;
    }

    public Round getCurrentRound() {
        if (rounds.isEmpty()) {
            return null;
        }

        return rounds.getLast();
    }

    public Turn getCurrentTurn() {
        var currentRound = getCurrentRound();

        if (currentRound == null) {
            return null;
        }

        return currentRound.getCurrentTurn();
    }

    public boolean processNewWorldState(int newState, Turn turn, Player attacker, Player defender) {
        this.setWorldState(newState);

        return processHook(attacker, turn) || processHook(defender, turn);
    }


    // move to World
    public boolean processHook(Player player, Turn turn) {
        var e = new WorldEventHook(null, this, turn);

        if (player != null) {
            for (var t : cloneArray(player.textEffectCards)) {
                boolean r = t.hook(e);

                if (r) {
                    player.textEffectCards.remove(t);
                }

                if (e.isCancelled()) {
                    if (!World.isWorldStateCancellable(getWorldState())) {
                        System.out.println("Attempted to cancel non-cancellable state");
                        return false;
                    }
                    System.out.println("Turn cancelled");
                    return true;
                }
            }
        }

        return false;
    }

    public void markGameEnd(Player winner) {
        gameEnded = true;
        this.winner = winner;
    }

    public void update() {
        synchronized (updateLock) {
            updateLock.notify();
        }

        var currentRound = getCurrentRound();

        if (currentRound == null) {
            return;
        }

        currentRound.update();
    }
}
