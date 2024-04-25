package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ISpellCard;
import io.github.utsav_bhandari.Engine.SpellCard.ThePowerOfExample;
import io.github.utsav_bhandari.Engine.TextEffectCard.ITextEffectCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ThePowerOfYapping;
import io.github.utsav_bhandari.Game;

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
    private Player winner;

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
    public static final int WORLD_STATE_ON_TURN = worldStateOn(70);

    // TODO add more i guess
    public static final int WORLD_STATE_ON_CHARGE_ADD = worldStateOn(100);
    public static final int WORLD_STATE_CHARGE_ADDED = worldStateDoneHook(200);
    public static final int WORLD_STATE_ON_SPELL_PRIME = worldStateOn(300);
    public static final int WORLD_STATE_SPELL_PRIMED = worldStateOn(350);
    public static final int WORLD_STATE_ON_CHARGE_PROMPT = worldStateDoneHook(400);
    public static final int WORLD_STATE_ON_CAST = worldStateOn(500);

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

        players[0].world = this;
        players[1].world = this;

        players[0].x = 100;
        players[0].y = 520;

        players[1].x = 1520;
        players[1].y = 520;

        var km = new PlayerKeymap();

        km.setKey('A', PlayerKeymap.Action.MOVE_LEFT);
        km.setKey('D', PlayerKeymap.Action.MOVE_RIGHT);
        km.setKey('Z', PlayerKeymap.Action.HELP);
        km.setKey('X', PlayerKeymap.Action.SELECT);

        players[0].setKeymap(km);

        km = new PlayerKeymap();
        km.setKey('J', PlayerKeymap.Action.MOVE_LEFT);
        km.setKey('L', PlayerKeymap.Action.MOVE_RIGHT);
        km.setKey('/', PlayerKeymap.Action.HELP);
        km.setKey('.', PlayerKeymap.Action.SELECT);

        players[1].setKeymap(km);

        for (int i = 0; i < 8; i++) {
            spellCardPile.add(new ThePowerOfExample());
            textEffectCardPile.add(new ThePowerOfYapping());
        }
    }

    /**
     * Blocking
     */
    public void run() throws InterruptedException {
        while (winner == null) {
            var round = new Round(this);
            rounds.add(round);

            System.out.println("Round " + rounds.size() + " started");

            round.run();

            System.out.println("Round " + rounds.size() + " ended");
        }
    }

    /**
     * Literally just stops until the UI "frees" the world to run again
     */
    public synchronized void waitForUi() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    /**
     * Take the last spell card from the pile and remove it
     */
    public ISpellCard drawSpellCard() {
        if (spellCardPile.isEmpty()) {
            return null;
        }

        int lastIndex = spellCardPile.size() - 1;

        return spellCardPile.remove(lastIndex);
    }

    /**
     * Take the last text effect card from the pile and remove it
     */
    public ITextEffectCard drawTextEffectCard() {
        if (textEffectCardPile.isEmpty()) {
            return null;
        }

        int lastIndex = textEffectCardPile.size() - 1;

        return textEffectCardPile.remove(lastIndex);
    }
}
