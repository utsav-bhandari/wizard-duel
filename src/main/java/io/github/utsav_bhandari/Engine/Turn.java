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

        /*
         * I have learned something new here...
         *
         * I will become stronger next time
         */
        round.world.setWorldState(World.WORLD_STATE_ON_CHARGE_ADD);
        if (processHook(attacker)) return;
        if (processHook(defender)) return;

        int chargeToAdd = 2;

        attacker.setCharge(attacker.getCharge() + chargeToAdd);

        round.world.setWorldState(World.WORLD_STATE_CHARGE_ADDED);
        if (processHook(attacker)) return;
        if (processHook(defender)) return;

        var spellCard = round.getPlayerSelection(attacker).getSpellCard();

        if (spellCard != null) {
            System.out.println("Charge added");
            System.out.println("Priming spell card");

            round.world.setWorldState(World.WORLD_STATE_ON_SPELL_PRIME);
            if (processHook(attacker)) return;
            if (processHook(defender)) return;
            spellCard.prime();


            round.world.setWorldState(World.WORLD_STATE_SPELL_PRIMED);
            if (processHook(attacker)) return;
            if (processHook(defender)) return;

            round.world.setWorldState(World.WORLD_STATE_ON_CAST);
            if (processHook(attacker)) return;
            if (processHook(defender)) return;

            spellCard.cast();

            Util.unsafeWait(500);
        } else {
            System.out.println("Spell card is null");
        }

        System.out.println("Turn ended");
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
