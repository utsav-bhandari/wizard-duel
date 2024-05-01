package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Lib.Util;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.Random;

public class ArcaneFlurry extends ASpellCard implements ISpellCard {
    {
        thumbnail = Resource.getInstance().cardThumbnails.get("ArcaneFlurry");
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

    public ArcaneFlurry() {
        scale = 2;
        Random randSpellPath = new Random();
        spellPath = Resource.getInstance().getAnimatedSprite("SpellPath" + randSpellPath.nextInt(1, 6));
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
        if (!isNeutral()) {
            spellPath.render(g);
        }
    }

    @Override
    public void setDamage(float damage) {
        super.setDamage(damage);
    }

    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("ArcaneFlurry");
    }

}
