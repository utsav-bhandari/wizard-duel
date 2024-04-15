package io.github.utsav_bhandari.Scripts;

import io.github.utsav_bhandari.Render.AnimatedSprite;
import io.github.utsav_bhandari.Render.AnimatedSpriteRoot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AnimatedSpriteDemo {
    public static void main(String[] args) {
        BufferedImage image = null;

        try (var stream = AnimatedSpriteDemo.class.getResourceAsStream("/sprites/pixel-spell-effect/spells-0.png")) {
            image = ImageIO.read(stream);

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
                    true
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        var sprite = AnimatedSpriteRoot.getAnimatedSprite("WHIRLWIND");

        var transform = new AffineTransform();

        transform.scale(3, 3);

        var op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        StdDrawProvider.run(g -> {
            sprite.render(g, 960, 540, op);
        });
    }
}
