package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Lib.Util;

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
        var r = Resource.getInstance();


        var spellCard = round.getPlayerSelection(attacker).getSpellCard();

        // player can decide whether to charge spell or not
        if (!processNewWorldState(World.WORLD_STATE_ON_CHARGE_PROMPT)) {
            if (attacker.getCharge() >= spellCard.getCharge()) {
                round.world.splashScreenText = "Charge spell card?";
                while (!spellCard.isChargeUseConfirmed()) {
                    round.world.waitForUi();
                    round.world.splashScreenText = spellCard.isChargeUse() ? "Using charge (press select to confirm)" : "Not using charge (press select to confirm)";
                }
                round.world.splashScreenText = null;
            } else {
                round.world.splashScreenText("Not enough charge to use spell card");
            }

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
            attacker.setAnimationState("WizardAttack1");
            if (attacker.getId() == 1) {
                attacker.setAnimationState("WizardAttack2");
            }
            spellCard.cast();
            defender.setHealth(
                    Math.max(0, (int) defender.getHealth() - spellCard.getDamage())
            );
            defender.setAnimationState("TakeHit");
            spellCard.done();
            Util.unsafeWait(300);
            attacker.setCurrentSpellCard(null);
        } else {
            System.out.println("Spell card is null");
        }

        System.out.println("Turn ended");
    }

    // move to World
    public boolean processNewWorldState(int newState) {
        return round.world.processNewWorldState(newState, this, attacker, defender);
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
