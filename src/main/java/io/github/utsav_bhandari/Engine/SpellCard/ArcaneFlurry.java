package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ArcaneFlurry extends ASpellCard implements ISpellCard {
    {
      thumbnail = Resource.getInstance().cardThumbnails.get("ArcaneFlurry");
    }
    public String getName() {
        return "Arcane Flurry";
    }
    public String getDescription() {
        return "Unleashes a flurry of mystical energy. 1/3 chance to inflict double damage.";
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
        return Resource.getInstance().getAnimatedSprite("ArcaneFlurry");
    }

}
