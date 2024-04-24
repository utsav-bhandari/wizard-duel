package io.github.utsav_bhandari.Engine.SpellCard;

import java.awt.*;

public class ArcaneQuota extends ASpellCard implements ISpellCard {
    public String getName() {
        return "Arcane Quota";
    }

    public String getDescription() {
        return " Unleashes arcane energy in a structured pattern.\n" +
                "Damage scales based on charges spent."; // +1/2/3 for now
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
