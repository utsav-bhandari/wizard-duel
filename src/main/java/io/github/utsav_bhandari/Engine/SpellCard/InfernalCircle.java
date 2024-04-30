package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InfernalCircle extends ASpellCard implements ISpellCard {
    {
  thumbnail = Resource.getInstance().cardThumbnails.get("InfernalCircle");
}
    public String getName() {
        return "Infernal Circle";
    }
    public String getDescription() {
        return "Conjures a ring of searing flames. 40% chance to amplify damage by 1.5x.\n " +
                "However, there's a 20% chance of being singed, dealing half the damage to yourself.";
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, (int) x, (int) y, 192, 192, null);
    }

    @Override
    public int getCharge() {
        return 1;
    }

    @Override
    public void renderSpell(Graphics2D g) {

    }
    @Override
    public void setDamage(float damage) {
        super.setDamage(damage);
    }
    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("InfernalCircle");
    }
}
