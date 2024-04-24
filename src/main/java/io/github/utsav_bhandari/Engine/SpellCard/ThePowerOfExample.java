package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;
import io.github.utsav_bhandari.Render.AnimatedSpriteRoot;
import io.github.utsav_bhandari.Scripts.AnimatedSpriteDemo;

import javax.imageio.ImageIO;
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

    public String getName() {
        return "The Power of Example";
    }

    public String getDescription() {
        return "Harness the power of showing";
    }

    private static final AffineTransformOp op;

    static {
        var trans = new AffineTransform();

        trans.scale(4, 4);

        op = new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    }


    static {
        // TODO: move this to Resources and properly load all resources
        System.out.println("ThePowerOfExample: PLEASE REMOVE THIS");
        try (var stream = AnimatedSprite.class.getResourceAsStream("/sprites/pixel-spell-effect/spells-0.png")) {
            var image = ImageIO.read(stream);

            AnimatedSpriteRoot.registerAnimatedSprite(
                    "WHIRLWIND",
                    image,
                    64,
                    64,
                    0,
                    128,
                    64,
                    0,
                    18,
                    60,
                    10,
                    10,
                    false
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("WHIRLWIND");
    }

    @Override
    public void setDamage(float damage) {
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

        super.setDamage(damage);
    }

    @Override
    public void renderSpell(Graphics2D g) {
        float RADIUS = 100;

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
    }

    private static int calculateReqNumSprite(float damage) {
        float SPRITE_PER_DAMAGE = 2;

        return (int) (damage / SPRITE_PER_DAMAGE);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.drawString("The Power of Example", x, y);
    }

    @Override
    public int getMinCharge() {
        return 1;
    }

    @Override
    public int getMaxCharge() {
        return 1;
    }
}
