package io.github.utsav_bhandari.Scripts;

import io.github.utsav_bhandari.Render.AnimatedSprite;
import io.github.utsav_bhandari.Render.AnimatedSpriteRoot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class AnimatedSpriteDemo {
    public static void main(String[] args) {
        try (var stream = AnimatedSpriteDemo.class.getResourceAsStream("/sprites/spell-paths/1.png")) {
            var spellPath1 = ImageIO.read(stream);

//            AnimatedSpriteRoot.registerAnimatedSprite(
//                    "WHIRLWIND",
//                    image,
//                    64,
//                    64,
//                    0,
//                    128,
//                    64,
//                    0,
//                    18,
//                    60,
//                    10,
//                    10,
//                    false
//            );
//            AnimatedSpriteRoot.registerAnimatedSprite(
//                    "GEOMETRY_DASH",
//                    image,
//                    64,
//                    64,
//                    0,
//                    64,
//                    64,
//                    0,
//                    18,
//                    60,
//                    20,
//                    10,
//                    true
//            );
//            AnimatedSpriteRoot.registerAnimatedSprite(
//                    "WATER_PETALS",
//                    image,
//                    64,
//                    64,
//                    0,
//                    0,
//                    64,
//                    0,
//                    18,
//                    60,
//                    30,
//                    10,
//                    false
//            );
            AnimatedSpriteRoot.registerAnimatedSprite(
                    "SpellPath1",
                    spellPath1,
                    100,
                    100,
                    0,
                    0,
                    100,
                    100,
                    60,
                    60,
                    60,
                    -1,
                    false
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<AnimatedSprite> sprites = new ArrayList<>();

        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("SpellPath1"));

//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("WHIRLWIND"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("GEOMETRY_DASH"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("WATER_PETALS"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("WHIRLWIND"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("GEOMETRY_DASH"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("WATER_PETALS"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("WHIRLWIND"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("GEOMETRY_DASH"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("WATER_PETALS"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("WHIRLWIND"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("GEOMETRY_DASH"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("WATER_PETALS"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("WHIRLWIND"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("GEOMETRY_DASH"));
//        sprites.add(AnimatedSpriteRoot.getAnimatedSprite("WATER_PETALS"));

//        var transform = new AffineTransform();
//
//        transform.scale(3, 3);
//
//        var op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//
//        for (var sprite : sprites) {
//            sprite.op = op;
//        }

        var sprite = sprites.get(0);
        sprite.x = 1920;
        StdDrawProvider.run(g -> {
            sprite.x -= 10;
            sprite.render(g);
        });
    }
}
