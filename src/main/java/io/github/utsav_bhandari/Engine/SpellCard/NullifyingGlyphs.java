package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Lib.Util;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NullifyingGlyphs extends ASpellCard implements ISpellCard {
    {
        thumbnail = Resource.getInstance().cardThumbnails.get("NullifyingGlyphs");
    }

    private final AnimatedSprite spellPath;
    private AffineTransformOp op;

    public void cast() {
        spellPath.x = owner.x - (float) spellPath.getWidth() / 2;
        spellPath.y = owner.y - (float) spellPath.getHeight() / 2;
        var trans = new AffineTransform();
        trans.scale(scale, scale);

        if (owner.getId() == 0) {
            trans.scale(-1, 1);
        }

        op = new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        spellPath.op = op;

        for (int i = 0; i < 100; i++) {
            spellPath.x = Util.lerp(owner.x, getTarget().x, i / 100.f);
            world.waitUpdate();
        }
    }

    public NullifyingGlyphs() {
        scale = 2;
        Random randSpellPath = new Random();
        spellPath = Resource.getInstance().getAnimatedSprite("SpellPath" + randSpellPath.nextInt(1, 6));
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
        if (!isNeutral()) {
            spellPath.render(g);
        }
    }

    @Override
    public void setDamage(float damage) {
        super.setDamage(damage);
    }

    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("NullifyingGlyphs");
    }
}
