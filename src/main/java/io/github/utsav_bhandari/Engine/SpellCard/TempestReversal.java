package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Lib.Util;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TempestReversal extends ASpellCard implements ISpellCard {
    {
        thumbnail = Resource.getInstance().cardThumbnails.get("TempestReversal");
        spell = Resource.getInstance().getAnimatedSprite("TempestReversal");
    }

    private final AnimatedSprite spellPath;
    private final AnimatedSprite spell;
    private AffineTransformOp op;

    public void cast() {
        super.cast();

        var trans = new AffineTransform();
        trans.scale(scale, scale);

        op = new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        spell.op = op;

        if (owner.getId() == 0) {
            trans.scale(-1, 1);
        }

        spell.x = getTarget().x + (float) -spell.getWidth() * scale / 2;
        spell.y = getTarget().y + (float) -spell.getHeight() * scale / 2;

        float spellPathOffX = (float) -spellPath.getWidth() * (float) trans.getScaleX() / 2;
        float spellPathOffY = (float) -spellPath.getHeight() * (float) trans.getScaleY() / 2;

        op = new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        spellPath.op = op;


        for (int i = 0; i < 100; i++) {
            spellPath.x =  spellPathOffX + Util.lerp(owner.x, getTarget().x, i / 100.f);
            spellPath.y =  spellPathOffY + Util.lerp(owner.y, getTarget().y, i / 100.f);
            world.waitUpdate();
        }

        Util.unsafeWait(50);
    }
    public void done() {
        super.done();
        world.waitUpdate();
        Util.unsafeWait(700);
    }

    public TempestReversal() {
        scale = 3;
        Random randSpellPath = new Random();
        spellPath = Resource.getInstance().getAnimatedSprite("SpellPath" + randSpellPath.nextInt(1, 6));
    }
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
        if (isCasting()) {
            spellPath.render(g);
        }
        if (isDone()) {
            spell.render(g);
        }
    }

    @Override
    public void setDamage(float damage) {
        super.setDamage(damage);
    }

    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("TempestReversal");
    }
}
