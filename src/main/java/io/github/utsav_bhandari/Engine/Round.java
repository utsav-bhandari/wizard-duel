package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ISpellCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ITextEffectCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Round {
    public final List<Turn> turns = new ArrayList<>();
    private final World world;

    private final HashMap<Player, Selection> playerSelections = new HashMap<>();

    public Round(World world) {
        this.world = world;

        for (var player : world.players) {
            var textEffectCardChoices = new ArrayList<ITextEffectCard>();

            for (int i = 0; i < 3; i++) {
                textEffectCardChoices.add(world.drawTextEffectCard());
            }

            var spellCardChoices = new ArrayList<ISpellCard>();

            for (int i = 0; i < 2; i++) {
                spellCardChoices.add(world.drawSpellCard());
            }

            playerSelections.put(player, new Selection(textEffectCardChoices, spellCardChoices));
        }
    }

    public void run() {
        world.setWorldState(World.WORLD_STATE_ON_ROUND);

        System.out.println("Giving player choices");

        while (!allPlayersSelected()) {
            world.setWorldState(World.WORLD_STATE_SELECTION_STARTED);
            world.waitForUi();
        }

        world.setWorldState(World.WORLD_STATE_ROUND_ENDED);
    }

    private boolean allPlayersSelected() {
        for (var selection : playerSelections.values()) {
            if (!selection.spellCardSelected()) {
                return false;
            }
        }

        return true;
    }

    public Turn getCurrentTurn() {
        if (turns.isEmpty()) return null;

        return turns.getLast();
    }

    public void update() {
        var turn = getCurrentTurn();

        if (turn == null) return;

        // stuff
    }

    public Selection getPlayerSelection(Player player) {
        return playerSelections.get(player);
    }
}
