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

        int chargeToAdd = 2;

        if (!processNewWorldState(World.WORLD_STATE_ON_CHARGE_ADD)) {
            attacker.setCharge(attacker.getCharge() + chargeToAdd);
        } else {
            System.out.println("Charge add cancelled");
        }

        if (processNewWorldState(World.WORLD_STATE_CHARGE_ADDED)) return;

        var spellCard = round.getPlayerSelection(attacker).getSpellCard();

        // player can decide whether to charge spell or not
        if (!processNewWorldState(World.WORLD_STATE_ON_CHARGE_PROMPT)) {
            round.world.splashScreenText = "Charge spell card?";
            while (!spellCard.isChargeUseConfirmed()) {
                round.world.waitForUi();
            }
            round.world.splashScreenText = null;
        } else {
            System.out.println("Charge prompt cancelled");
        }

        if (spellCard != null) {
            System.out.println("Charge added");
            System.out.println("Priming spell card");

            if (processNewWorldState(World.WORLD_STATE_ON_SPELL_PRIME)) return;
            spellCard.prime();

            if (processNewWorldState(World.WORLD_STATE_SPELL_PRIMED)) return;

            if (processNewWorldState(World.WORLD_STATE_ON_CAST)) return;

            spellCard.cast();

            Util.unsafeWait(500);

            attacker.setCurrentSpellCard(null);
        } else {
            System.out.println("Spell card is null");
        }

        System.out.println("Turn ended");
    }

    public boolean processNewWorldState(int newState) {
        round.world.setWorldState(newState);

        return processHook(attacker) || processHook(defender);
    }

    private boolean processHook(Player player) {
        var e = new TurnEventHook(null, round.world, this);

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
