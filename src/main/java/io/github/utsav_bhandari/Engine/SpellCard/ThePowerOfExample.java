package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Lib.Util;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;

public class ThePowerOfExample extends ASpellCard implements ISpellCard {
    private final ArrayList<AnimatedSprite> sprites = new ArrayList<>();

    private float angle = 0;

    public ThePowerOfExample() {
        // trigger update
        setDamage(10);
        scale = 4;

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
        return Resource.getInstance().getAnimatedSprite("Charge Cascade");
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
            Util.unsafeWait(300);
        }

        setDamage(dmg);

        Util.unsafeWait(1000);

        System.out.println("The Power of Example is primed" + dmg);
    }

    @Override
    public void cast() {
        super.cast();

        float niter = 200.0f;

        // incredible
        for (int i = 0; i < niter; i++) {
            Util.unsafeWait(5);
            spellX = Util.lerp(owner.x, getTarget().x, i / niter);
            spellY = Util.lerp(owner.y, getTarget().y, i / niter);
        }
        Util.unsafeWait(1000);
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
        float RADIUS = 100;

        angle += 0.04f;

        for (int i = 0; i < sprites.size(); i++) {
            float angleOffset = (float) Math.PI * 2 / sprites.size() * i;

            var sprite = sprites.get(i);

//            float r = (float) Math.sin(angle / 4) * RADIUS;
            float r = 2.0f * RADIUS;

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
        g.setColor(Color.RED);
        g.drawString("The Power of Example", x, y);
    }

    @Override
    public int getCharge() {
        return 1;
    }
}
