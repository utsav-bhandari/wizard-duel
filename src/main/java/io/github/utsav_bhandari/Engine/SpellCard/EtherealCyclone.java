package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EtherealCyclone extends ASpellCard implements ISpellCard {
    public static final BufferedImage thumbnail = Resource.getInstance().cardThumbnails.get("EtherealCyclone");
    public String getName() {
        return "Ethereal Cyclone";
    }
    public String getDescription() {
        return "Unleashes a swirling vortex of energy. If your charges exceed your opponent's,\n" +
                "inflict 1.5x damage. Otherwise, gain 2 charges.";
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, (int) x, (int) y, 192, 192, null);
    }

    @Override
    public int getCharge() {
        return 3;
    }

    @Override
    public void renderSpell(Graphics2D g) {

    }
    @Override
    public void setDamage(float damage) {
        super.setDamage(damage);
    }
    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("EtherealCyclone");
    }
}
