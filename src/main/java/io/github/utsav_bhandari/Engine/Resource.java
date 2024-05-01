package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Render.AnimatedSprite;
import io.github.utsav_bhandari.Render.AnimatedSpriteRoot;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    public final ArrayList<BufferedImage> borders;

    private Resource() {
        // TODO: init stuff here
        titleScreen = loadResourceImage("/backgrounds/title_screen.png");
        gameBackground = loadResourceImage("/backgrounds/game_background.png");
        scroll = loadResourceImage("/backgrounds/scroll.png");
        charge = loadResourceImage("/icons/charge.png");

        var borderGrid1 = loadResourceImage("/borders/Border All 1.png");
        var borderGrid2 = loadResourceImage("/borders/Border All 16.png");
        borders = new ArrayList<>();

        borders.add(borderGrid1.getSubimage(64 * 9, 64 * 6, 64, 64));
        borders.add(borderGrid1.getSubimage(64 * 3, 64 * 5, 64, 64));
        borders.add(borderGrid1.getSubimage(0, 64 * 3, 64, 64));

        borders.add(borderGrid2.getSubimage(64 * 9, 64 * 6, 64, 64));
        borders.add(borderGrid2.getSubimage(64 * 3, 64 * 5, 64, 64));
        borders.add(borderGrid2.getSubimage(0, 64 * 3, 64, 64));

        var spells1 = loadResourceImage("/sprites/pixel-spell-effect/spells-0.png");
        var spells2 = loadResourceImage("/sprites/pixel-spell-effect/spells-1.png");
        var wizardIdle = loadResourceImage("/wizards/Idle.png");

        var spellPath1 = loadResourceImage("/sprites/spell-paths/1.png");
        var spellPath2 = loadResourceImage("/sprites/spell-paths/2.png");
        var spellPath3 = loadResourceImage("/sprites/spell-paths/3.png");
        var spellPath4 = loadResourceImage("/sprites/spell-paths/4.png");
        var spellPath5 = loadResourceImage("/sprites/spell-paths/5.png");

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

        for (String id : AnimatedSpriteRoot.getAnimatedSpriteIds()) {
            cardThumbnails.put(id, AnimatedSpriteRoot.getAnimatedSpriteRoot(id).frames.get(3));
        }
        int wizard_border = 50;
        AnimatedSpriteRoot.registerAnimatedSprite(
                "Wizard Idle",
                wizardIdle,
                250 - 2 * wizard_border,
                250 - 2 * wizard_border,
                wizard_border,
                wizard_border,
                250,
                0,
                8,
                60,
                10,
                -1,
                false
        );
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
        AnimatedSpriteRoot.registerAnimatedSprite(
                "SpellPath2",
                spellPath2,
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
        AnimatedSpriteRoot.registerAnimatedSprite(
                "SpellPath3",
                spellPath3,
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
        AnimatedSpriteRoot.registerAnimatedSprite(
                "SpellPath4",
                spellPath4,
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
        AnimatedSpriteRoot.registerAnimatedSprite(
                "SpellPath5",
                spellPath5,
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


        spells1.flush();
        spells2.flush();
        wizardIdle.flush();
        borderGrid1.flush();
        borderGrid2.flush();
        spellPath1.flush();
        spellPath2.flush();
        spellPath3.flush();
        spellPath4.flush();
        spellPath5.flush();

    }

    private static BufferedImage loadResourceImage(String path) {
        return loadResourceImage(Resource.class.getResourceAsStream(path), path);
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

    public BufferedImage getCardThumbnailArt(String id) {
        if (cardThumbnails.containsKey(id)) {
            return cardThumbnails.get(id);
        }

        String p = "/card-thumbnails/" + id + ".png";
        var stream = Resource.class.getResourceAsStream(p);

        if (stream == null) {
            System.out.println("Resource not found: " + p);
            cardThumbnails.put(id, NULL);
            return NULL;
        }

        var img = loadResourceImage(stream, p);
        cardThumbnails.put(id, img);

        return img;
    }
}
