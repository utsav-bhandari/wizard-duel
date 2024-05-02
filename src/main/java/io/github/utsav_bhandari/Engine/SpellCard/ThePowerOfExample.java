package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Lib.Util;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ThePowerOfExample extends ASpellCard implements ISpellCard {
    private final ArrayList<AnimatedSprite> sprites = new ArrayList<>();
    private int radius;
    private float angle = 0;


    {
        thumbnail = Resource.getInstance().cardThumbnails.get("ChargeCascade");
    }

    public ThePowerOfExample() {
        // trigger update
        setDamage(40);
        scale = 4;

        radius = 100;

        var trans = new AffineTransform();

        trans.scale(scale, scale);

        op = new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    }

    public String getName() {
        return "The Power of Example";
    }

    public String getDescription() {
        return "Harness the power of showing";
    }

    private final AffineTransformOp op;

    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("ChargeCascade");
    }

    @Override
    public void prime() {
        super.prime();

        spellX = owner.x;
        spellY = owner.y;

        Util.unsafeWait(300);

        float dmg = getDamage();

        setDamage(0);
        for (int i = 0; i < dmg; i++) {
            setDamage(getDamage() + 1);
            radius = 30 + i * 10;
            Util.unsafeWait(100);
        }

        setDamage(dmg);

        Util.unsafeWait(1000);

        System.out.println("The Power of Example is primed" + dmg);
    }

    @Override
    public void cast() {
        super.cast();

        float niter = 100.0f;

        // incredible
        for (int i = 0; i < niter; i++) {
            world.waitUpdate();
            spellX = Util.lerp(owner.x, getTarget().x, i / niter);
            spellY = Util.lerp(owner.y, getTarget().y, i / niter);
        }

        int r = radius;

        for (int i = 0; i < 100; i++) {
            world.waitUpdate();
            radius = (int) Util.lerp(r, 10, i / 100.0f);
        }
    }

    @Override
    public void setDamage(float damage) {
        super.setDamage(damage);

        if (isNeutral()) return;

        int numOfSprites = calculateReqNumSprite(damage);

        if (numOfSprites > sprites.size()) {
            for (int i = sprites.size(); i < numOfSprites; i++) {
                sprites.add(getNewSprite());
            }

            for (int i = 0; i < sprites.size(); i++) {
                var sprite = sprites.get(i);
                // Sync
                sprite.reset();
                sprite.frameCounter.setValue(i * 8);
            }
        } else if (numOfSprites < sprites.size()) {
            sprites.subList(numOfSprites, sprites.size()).clear();
            assert sprites.size() == numOfSprites;
        }
    }

    @Override
    public void renderSpell(Graphics2D g) {
        if (!(isCasting() || isPrimed())) return;

        angle += 0.04f;

        for (int i = 0; i < sprites.size(); i++) {
            float angleOffset = (float) Math.PI * 2 / sprites.size() * i;

            var sprite = sprites.get(i);

//            float r = (float) Math.sin(angle / 4) * RADIUS;
            float r = radius;

            sprite.x = (int) (spellX + Math.cos(angle + angleOffset) * r) - scale * sprite.getWidth() / 2;
            sprite.y = (int) (spellY + Math.sin(angle + angleOffset) * r) - scale * sprite.getHeight() / 2;

            sprite.op = op;

            sprite.render(g);
        }
    }

    private static int calculateReqNumSprite(float damage) {
        float SPRITE_PER_DAMAGE = 1;

        return (int) (damage / SPRITE_PER_DAMAGE);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, (int) x, (int) y, 192, 192, null);
    }

    @Override
    public int getCharge() {
        return 1;
    }
}
