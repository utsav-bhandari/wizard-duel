package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;

public class ArcaneQuota extends ASpellCard implements ISpellCard {
    public String getName() {
        return "Arcane Quota";
    }

    public String getDescription() {
        return " Unleashes arcane energy in a structured pattern.\n" +
                "Damage scales based on charges spent so far."; // +1/2/3 for now
    }

    @Override
    public void render(Graphics2D g) {
    }

    @Override
    public int getCharge() {
        return 2;
    }

    @Override
    public void renderSpell(Graphics2D g) {

    }
    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("ArcaneQuota");
    }

}
