package io.github.utsav_bhandari.Engine.SpellCard;

import java.awt.*;

public class VirulentEruption extends ASpellCard implements ISpellCard {
    public String getName() {
        return "Virulent Eruption";
    }
    public String getDescription() {
        return "Unleashes a corrosive blast of acidic energy.\n" +
                "Damage scales based on the health lost by the opponent."; // every 10 health lost +1 dmg for now
    }

    @Override
    public void render(Graphics2D g) {
    }

    @Override
    public int getMinCharge() {
        return 0;
    }

    @Override
    public int getMaxCharge() {
        return 0;
    }

    @Override
    public void renderSpell(Graphics2D g) {

    }
}