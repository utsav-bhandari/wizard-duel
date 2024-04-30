package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VirulentEruption extends ASpellCard implements ISpellCard {
    public static final BufferedImage thumbnail = Resource.getInstance().cardThumbnails.get("VirulentEruption");
    public String getName() {
        return "Virulent Eruption";
    }
    public String getDescription() {
        return "Unleashes a corrosive blast of acidic energy.\n" +
                "Damage scales based on the health lost by the opponent."; // every 10 health lost +1 dmg for now
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
        return Resource.getInstance().getAnimatedSprite("VirulentEruption");
    }

}
