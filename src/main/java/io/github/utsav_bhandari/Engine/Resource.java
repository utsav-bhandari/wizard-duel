package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Render.AnimatedSprite;
import io.github.utsav_bhandari.Render.AnimatedSpriteRoot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Singleton. Simple resource manager.
 */
public final class Resource {
    private static Resource instance;

    private static final BufferedImage NULL = loadResourceImage("/NULL.png");
    public final BufferedImage titleScreen;
    public final BufferedImage gameBackground;

    private Resource() {
        // TODO: init stuff here
        titleScreen = loadResourceImage("/backgrounds/title_screen.png");
        gameBackground = loadResourceImage("/backgrounds/game_background.png");

        var spells1 = loadResourceImage("/sprites/pixel-spell-effect/spells-0.png");
        var spells2 = loadResourceImage("/sprites/pixel-spell-effect/spells-1.png");

        AnimatedSpriteRoot.registerAnimatedSprite(
                "Charge Cascade",
                spells1,
                64,
                64,
                0,
                0,
                64,
                0,
                17,
                60,
                10,
                -1,
                false
        );

        AnimatedSpriteRoot.registerAnimatedSprite(
                "Nullifying Glyphs",
                spells1,
                64,
                64,
                0,
                64,
                64,
                0,
                15,
                60,
                10,
                -1,
                false
        );

        AnimatedSpriteRoot.registerAnimatedSprite(
                "Arcane Flurry",
                spells1,
                64,
                64,
                0,
                64 * 2,
                64,
                0,
                18,
                60,
                10,
                -1,
                false
        );

        AnimatedSpriteRoot.registerAnimatedSprite(
                "Virulent Eruption",
                spells2,
                64,
                64,
                0,
                64,
                64,
                0,
                7,
                60,
                10,
                -1,
                false
        );

        AnimatedSpriteRoot.registerAnimatedSprite(
                "Ethereal Cyclone",
                spells2,
                64,
                64,
                0,
                64 * 4,
                64,
                0,
                9,
                60,
                10,
                -1,
                false
        );

        AnimatedSpriteRoot.registerAnimatedSprite(
                "Infernal Circle",
                spells2,
                64,
                64,
                0,
                64 * 5,
                64,
                0,
                9,
                60,
                10,
                -1,
                false
        );

        AnimatedSpriteRoot.registerAnimatedSprite(
                "Tempest Reversal",
                spells2,
                64,
                64,
                0,
                64 * 6,
                64,
                0,
                9,
                60,
                10,
                -1,
                false
        );

        AnimatedSpriteRoot.registerAnimatedSprite(
                "Arcane Quota",
                spells2,
                64,
                64,
                0,
                64 * 7,
                64,
                0,
                10,
                60,
                10,
                -1,
                false
        );

        spells1.flush();
        spells2.flush();
    }

    /**
     * Make sure to dispose if ephemeral used!!!
     */
    private static BufferedImage loadResourceImage(String path) {
        BufferedImage image;

        try (var stream = Resource.class.getResourceAsStream(path)) {
            if (stream == null) {
                System.out.println("Resource not found: " + path);
                if (NULL == null) {
                    throw new RuntimeException("NULL.png not found");
                }

                return NULL;
            }
            image = ImageIO.read(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return image;
    }

    public static Resource getInstance() {
        if (instance == null) {
            instance = new Resource();
        }
        return instance;
    }

    public AnimatedSprite getAnimatedSprite(String id) {
        return AnimatedSpriteRoot.getAnimatedSprite(id);
    }
}
