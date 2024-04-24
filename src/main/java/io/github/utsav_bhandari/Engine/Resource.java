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