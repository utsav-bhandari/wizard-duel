package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ArcaneQuota extends ASpellCard implements ISpellCard {
    public static final BufferedImage thumbnail = Resource.getInstance().cardThumbnails.get("ArcaneQuota");
    public String getName() {
        return "Arcane Quota";
    }

    public String getDescription() {
        return " Unleashes arcane energy in a structured pattern.\n" +
                "Damage scales based on charges spent so far."; // +1/2/3 for now
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, (int) x, (int) y, 192, 192, null);
    }

    @Override
    public int getCharge() {
        return 2;
    }

    @Override
    public void renderSpell(Graphics2D g) {

    }
    @Override
    public void setDamage(float damage) {
        super.setDamage(damage);
    }
    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("ArcaneQuota");
    }

}
