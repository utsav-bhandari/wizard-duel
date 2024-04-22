package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.ISpellCard;

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
}
