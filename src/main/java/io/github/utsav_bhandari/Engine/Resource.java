package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Render.AnimatedSprite;
import io.github.utsav_bhandari.Render.AnimatedSpriteRoot;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


/**
 * Singleton. Simple resource manager.
 */
public final class Resource {
    private static Resource instance;
    private static final BufferedImage NULL = loadResourceImage("/NULL.png");
    public final BufferedImage titleScreen;
    public final BufferedImage gameBackground;
    public final BufferedImage scroll;

    public final BufferedImage charge;
    public final HashMap<String, BufferedImage> cardThumbnails;

    private Resource() {
        // TODO: init stuff here
        titleScreen = loadResourceImage("/backgrounds/title_screen.png");
        gameBackground = loadResourceImage("/backgrounds/game_background.png");
        scroll = loadResourceImage("/backgrounds/scroll.png");
        charge = loadResourceImage("/icons/charge.png");

        var spells1 = loadResourceImage("/sprites/pixel-spell-effect/spells-0.png");
        var spells2 = loadResourceImage("/sprites/pixel-spell-effect/spells-1.png");
        var wizardIdle = loadResourceImage("/wizards/Idle.png");

        AnimatedSpriteRoot.registerAnimatedSprite(
                "ChargeCascade",
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
                "NullifyingGlyphs",
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
                "ArcaneFlurry",
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
                "VirulentEruption",
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
                "EtherealCyclone",
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
                "InfernalCircle",
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
                "TempestReversal",
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
                "ArcaneQuota",
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

        cardThumbnails = new HashMap<>();
        for (File file : getDirFiles("card-thumbnails")) {
            cardThumbnails.put(file.getName().replace(".png", ""), loadResourceImage(file));
        }
        for (String id : AnimatedSpriteRoot.getAnimatedSpriteIds()) {
            cardThumbnails.put(id, AnimatedSpriteRoot.getAnimatedSpriteRoot(id).frames.get(4));
        }
        int wizbord = 50;
        AnimatedSpriteRoot.registerAnimatedSprite(
                "Wizard Idle",
                wizardIdle,
                250 - 2 * wizbord,
                250 - 2 * wizbord,
                wizbord,
                wizbord,
                250,
                0,
                8,
                60,
                10,
                -1,
                false
        );

        spells1.flush();
        spells2.flush();
        wizardIdle.flush();
    }

    private static BufferedImage loadResourceImage(String path) {
        return loadResourceImage(Resource.class.getResourceAsStream(path), path);
    }
    private static BufferedImage loadResourceImage(File file) {
        try {
            return loadResourceImage(new FileInputStream(file), file.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static BufferedImage loadResourceImage(InputStream stream, String name) {
        BufferedImage image;

        if (stream == null) {
            System.out.println("Resource not found: " + name);
            if (NULL == null) {
                throw new RuntimeException("NULL.png not found");
            }

            return NULL;
        }

        try {
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

    public static File[] getDirFiles(String name) {
        // Get the class loader
        ClassLoader classLoader = Resource.class.getClassLoader();

        // Get the resource directory path
        String resourceDirectoryPath = classLoader.getResource(name).getPath();
        try {
            resourceDirectoryPath = URLDecoder.decode(resourceDirectoryPath, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Create a File object for the directory
        File resourceDirectory = new File(resourceDirectoryPath);
        // List files in the directory
        return resourceDirectory.listFiles();
    }
}
