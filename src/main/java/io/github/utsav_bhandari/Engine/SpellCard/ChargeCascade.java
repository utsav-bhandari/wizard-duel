package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ChargeCascade extends ASpellCard implements ISpellCard {
    {
  thumbnail = Resource.getInstance().cardThumbnails.get("ChargeCascade");
}
    public String getName() {
        return "Charge Cascade";
    }
    public String getDescription() {
        return "Unleashes a burst of energy.\n" +
                "Damage scales based on the number of charges your opponent possesses.";
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
        return Resource.getInstance().getAnimatedSprite("ChargeCascade");
    }
}
