package io.github.utsav_bhandari.Engine.SpellCard;
import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TempestReversal extends ASpellCard implements ISpellCard{
    public static final BufferedImage thumbnail = Resource.getInstance().cardThumbnails.get("TempestReversal");

    @Override
    public String getName() {
        return "Tempest Reversal";
    }

    @Override
    public String getDescription() {
        return "Conjures a tempest to disrupt the enemy's charges.\n" +
                "Decreases enemy charge by 2. If \"Eye of the Storm\" is in play,\n" +
                "wipe all enemy charges.\n";
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
        return Resource.getInstance().getAnimatedSprite("TempestReversal");
    }
}
