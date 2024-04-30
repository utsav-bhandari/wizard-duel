package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ISpellCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ITextEffectCard;
import io.github.utsav_bhandari.Lib.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Round {
    public final List<Turn> turns = new ArrayList<>();
    public final World world;

    private final HashMap<Player, Selection> playerSelections = new HashMap<>();

    public Round(World world) {
        this.world = world;

        for (var player : world.players) {
            var textEffectCardChoices = new ArrayList<ITextEffectCard>();

            for (int i = 0; i < 3; i++) {
                ITextEffectCard t = player.drawTextEffectCard();
                if (t == null) break;
                textEffectCardChoices.add(t);
            }

            var spellCardChoices = new ArrayList<ISpellCard>();

            for (int i = 0; i < 2; i++) {
                ISpellCard s = player.drawSpellCard();
                if (s == null) break;
                spellCardChoices.add(s);
            }

            playerSelections.put(player, new Selection(textEffectCardChoices, spellCardChoices));
        }
    }

    public void run() {
        world.setWorldState(World.WORLD_STATE_ON_ROUND);

        System.out.println("Giving player choices");

        world.splashScreenText("Choose your cards!");

        while (!allPlayersSelected()) {
            world.setWorldState(World.WORLD_STATE_SELECTION_STARTED);
            world.waitForUi();
        }

        // add back to player1 pool
        for (ISpellCard c : getPlayerSelection(world.players[0]).getRemainingSpellCards()) {
            world.players[0].spellCardPile.add(c);
        }
        for (ITextEffectCard c : getPlayerSelection(world.players[0]).getRemainingTextEffectCards()) {
            world.players[0].textEffectCardPile.add(c);
        }

        // add back to player2 pool
        for (ISpellCard c : getPlayerSelection(world.players[1]).getRemainingSpellCards()) {
            world.players[1].spellCardPile.add(c);
        }
        for (ITextEffectCard c : getPlayerSelection(world.players[1]).getRemainingTextEffectCards()) {
            world.players[1].textEffectCardPile.add(c);
        }

        for (int i = 0; i < 2; i++) {
            var attacker = world.players[i];
            var defender = world.players[1 - i];

            var textEffectCard = this.getPlayerSelection(attacker).getTextEffectCard();
            if (textEffectCard != null) {
                textEffectCard.setOwner(attacker);
                attacker.textEffectCards.add(textEffectCard);
            }
            var spellCard = this.getPlayerSelection(attacker).getSpellCard();
            if (spellCard != null) {
                spellCard.setOwner(attacker);
                spellCard.setTarget(defender);
            }
            attacker.setCurrentSpellCard(spellCard);
        }

        int goesFirst = (int) (Math.random() * 2);

        for (int i = 0; i < 2; i++) {
            int attackerIdx = (goesFirst + i) % 2;

            var attacker = world.players[attackerIdx];
            var defender = world.players[1 - attackerIdx];

            var turn = new Turn(this, attacker, defender);

            world.splashScreenText("Player " + (attackerIdx + 1) + " is attacking");

            turns.add(turn);

            turn.run();

            world.setWorldState(World.WORLD_STATE_TURN_ENDED);
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
