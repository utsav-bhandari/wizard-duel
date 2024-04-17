package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.ISpellCard;
import io.github.utsav_bhandari.Engine.Resource;
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
        setDamage(5);
    }

    private static final AffineTransformOp op ;

    static {
        var trans = new AffineTransform();

        trans.scale(4, 4);

        op = new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    }


    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("WHIRLWIND");
    }

    @Override
    public void setDamage(float damage) {
        int numOfSprites = calculateReqNumSprite((int) damage);

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
        }

        super.setDamage(damage);
    }

    private static int calculateReqNumSprite(int damage) {
        int SPRITE_PER_DAMAGE = 2;

        return damage / SPRITE_PER_DAMAGE;
    }

    @Override
    public void render(Graphics2D g) {
        if (isPrimed() || isCasting()) {
            float RADIUS = 300;

            angle += 0.04f;

            for (int i = 0; i < sprites.size(); i++) {
                float angleOffset = (float) Math.PI * 2 / sprites.size() * i;

                var sprite = sprites.get(i);

                float r = (float) Math.sin(angle / 4) * RADIUS;

                sprite.x = (int) (x + Math.cos(angle + angleOffset) * r);
                sprite.y = (int) (y + Math.sin(angle + angleOffset) * r);

                sprite.op = op;

                sprite.render(g);
            }
        } else {
            g.setColor(Color.RED);
            g.drawString("The Power of Example, ready to cast", x, y);
        }
    }
}
