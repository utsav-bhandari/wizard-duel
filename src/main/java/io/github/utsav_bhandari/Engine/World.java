package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ISpellCard;
import io.github.utsav_bhandari.Engine.SpellCard.ThePowerOfExample;
import io.github.utsav_bhandari.Engine.TextEffectCard.ITextEffectCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ThePowerOfYapping;
import io.github.utsav_bhandari.Game;
import io.github.utsav_bhandari.Lib.Util;

import java.util.ArrayList;
import java.util.List;

public class World {
    public final Game game;

    private final List<Round> rounds = new ArrayList<>();

    /**
     * TODO comment
     */
    private final List<ISpellCard> spellCardPile = new ArrayList<>();
    /**
     * TODO comment
     */
    private final List<ITextEffectCard> textEffectCardPile = new ArrayList<>();

    public final Player[] players = new Player[2];

    public String debugStatus = "";

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
    public static final int WORLD_STATE_ON_TURN = worldStateOn(10);

    // TODO add more i guess
    public static final int WORLD_STATE_ON_CHARGE_ADD = worldStateOn(100);
    public static final int WORLD_STATE_CHARGE_ADDED = worldStateDoneHook(200);
    public static final int WORLD_STATE_ON_SPELL_PRIME = worldStateOn(300);

    public static final int WORLD_STATE_TURN_ENDED = worldStateOn(1000);
    public static final int WORLD_STATE_ROUND_ENDED = worldStateOn(2000);

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

        for (int i = 0; i < 8; i++) {
            spellCardPile.add(new ThePowerOfExample());
            textEffectCardPile.add(new ThePowerOfYapping());
        }
    }

    /**
     * Blocking
     */
    public void run() throws InterruptedException {
        while (true) {
            waitForUi();

            for (int i = 0; i < 10; i++) {
                debugStatus += i;
                Util.unsafeWait(100);
            }
        }
    }

    /**
     * Literally just stops until the UI "frees" the world to run again
     */
    public synchronized void waitForUi() throws InterruptedException {
        this.wait();
    }

    public synchronized void notifyUi() {
        this.notify();
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

    public void update() {
        var currentRound = getCurrentRound();

        if (currentRound == null) {
            return;
        }

        currentRound.update();
    }
}
