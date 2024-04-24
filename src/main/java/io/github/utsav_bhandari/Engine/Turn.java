package io.github.utsav_bhandari.Engine;

public class Turn {
    private boolean isUsingCharge = false;

    public final Player attacker;
    public final Player defender;

    public Turn(Player attacker, Player defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public void run() {
        System.out.println("Running turn");

        System.out.println("Turn ended");
    }

    public boolean isUsingCharge() {
        return isUsingCharge;
    }

    public void setUsingCharge(boolean usingCharge) {
        isUsingCharge = usingCharge;
    }
}
