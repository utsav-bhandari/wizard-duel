package io.github.utsav_bhandari.Engine.SpellCard;

import java.awt.*;

public class ChargeCascade extends ASpellCard implements ISpellCard {
    public String getName() {
        return "Charge Cascade";
    }
    public String getDescription() {
        return "Unleashes a burst of energy.\n" +
                "Damage scales based on the number of charges your opponent possesses.";
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
