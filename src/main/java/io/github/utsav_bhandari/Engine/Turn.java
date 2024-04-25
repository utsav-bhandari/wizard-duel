package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Lib.Util;

import static io.github.utsav_bhandari.Lib.Util.cloneArray;

public class Turn {
    private final Round round;
    private boolean isUsingCharge = false;

    public final Player attacker;
    public final Player defender;

    public Turn(Round round, Player attacker, Player defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.round = round;
    }

    public void run() {
        System.out.println("Running turn");

        var textEffectCard = round.getPlayerSelection(attacker).getTextEffectCard();
        if (textEffectCard != null) {
            textEffectCard.setOwner(attacker);
            attacker.textEffectCards.add(textEffectCard);
        }
        var spellCard = round.getPlayerSelection(attacker).getSpellCard();
        if (spellCard != null) {
            spellCard.setOwner(attacker);
            attacker.setCurrentSpellCard(spellCard);
            spellCard.setTarget(defender);
        }

        round.world.setWorldState(World.WORLD_STATE_ON_CHARGE_ADD);

        var e = new TurnEventHook(null, round.world);

        /*
         * I have learned something new here...
         *
         * I will become stronger next time
         */
        if (processHook(e, attacker)) return;
        if (processHook(e, defender)) return;

        int chargeToAdd = 2;

        attacker.setCharge(attacker.getCharge() + chargeToAdd);

        round.world.setWorldState(World.WORLD_STATE_CHARGE_ADDED);

        if (processHook(e, attacker)) return;
        if (processHook(e, defender)) return;

        if (spellCard != null) {
            System.out.println("Charge added");
            System.out.println("Priming spell card");

            round.world.setWorldState(World.WORLD_STATE_ON_SPELL_PRIME);
            spellCard.prime();
            round.world.setWorldState(World.WORLD_STATE_SPELL_PRIMED);

            if (processHook(e, attacker)) return;
            if (processHook(e, defender)) return;

            round.world.setWorldState(World.WORLD_STATE_ON_CAST);
            spellCard.cast();

            Util.unsafeWait(500);
        } else {
            System.out.println("Spell card is null");
        }

        System.out.println("Turn ended");
    }

    private boolean processHook(TurnEventHook e, Player player) {
        for (var t : cloneArray(player.textEffectCards)) {
            boolean r = t.hook(e);

            if (r) {
                player.textEffectCards.remove(t);
            }

            if (e.isCancelled()) {
                if (!World.isWorldStateCancellable(round.world.getWorldState())) {
                    System.out.println("Attempted to cancel non-cancellable state");
                    return false;
                }
                System.out.println("Turn cancelled");
                return true;
            }
        }
        return false;
    }

    public boolean isUsingCharge() {
        return isUsingCharge;
    }

    public void setUsingCharge(boolean usingCharge) {
        isUsingCharge = usingCharge;
    }

    public void update() {
        // do nothing
    }
}
