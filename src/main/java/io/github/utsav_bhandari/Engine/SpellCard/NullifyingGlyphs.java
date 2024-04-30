package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NullifyingGlyphs extends ASpellCard implements ISpellCard {
    {
        thumbnail = Resource.getInstance().cardThumbnails.get("NullifyingGlyphs");
    }

    public String getName() {
        return "Nullifying Glyphs";
    }

    public String getDescription() {
        return "Conjures glyphs of nullification.\n" +
                "Removes a random text effect card from the enemy's side.";
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
        return Resource.getInstance().getAnimatedSprite("NullifyingGlyphs");
    }
}
