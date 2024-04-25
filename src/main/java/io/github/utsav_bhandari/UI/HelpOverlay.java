package io.github.utsav_bhandari.UI;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Lib.StdDrawBridge;
import io.github.utsav_bhandari.Render.AnimatedSprite;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public final class HelpOverlay implements IRenderable {
    private final int side;
    private final Color helpBackgroundColorRight;
    private final Color helpBackgroundColorLeft;
    private final BufferedImage scroll;
    private ArrayList<AnimatedSprite> animatedSprites;
    private AlphaComposite alphaComposite;

    /* side of 0 represents the left and side of 1 represents the right side of the screen */
    public HelpOverlay(int side) {
        alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);

        this.side = side;
        this.helpBackgroundColorRight = new Color(1f, 0f, 0f);
        this.helpBackgroundColorLeft = new Color(0f, 0f, 1f);

        var r = Resource.getInstance();
        scroll = r.scroll;
        var spriteIDs = new ArrayList<String>();

        spriteIDs.add("Charge Cascade");
        spriteIDs.add("Nullifying Glyphs");
        spriteIDs.add("Arcane Flurry");
        spriteIDs.add("Virulent Eruption");
        spriteIDs.add("Ethereal Cyclone");
        spriteIDs.add("Infernal Circle");
        spriteIDs.add("Tempest Reversal");
        spriteIDs.add("Arcane Quota");

        this.animatedSprites = new ArrayList<>();

        for (String spriteName : spriteIDs) {
            var sprite = r.getAnimatedSprite(spriteName);
            animatedSprites.add(sprite);
        }
    }

    @Override
    public void render(Graphics2D g) {
        var t = g.getComposite();
        g.setComposite(alphaComposite);
        int screenWidth = StdDrawBridge.width;
        int screenHeight = StdDrawBridge.height;
        int helpScreenWidth = screenWidth/2;

        int xPosition;
        if (side == 0) { // Left side
            xPosition = 0;
            g.setColor(helpBackgroundColorLeft);
        } else { // Right side
            xPosition = screenWidth - helpScreenWidth;
            g.setColor(helpBackgroundColorRight);
        }
        g.drawImage(scroll, null, xPosition + 10, 10);
        g.setComposite(t);

        int spriteStartX;
        // Adjusted start position for right side
        spriteStartX = xPosition + 40; // Start position for left side

        for (int i = 0; i < animatedSprites.size(); i++) {
            var sprite = animatedSprites.get(i);
            sprite.x = spriteStartX + i * 100; // Adjusted x position
            sprite.y = 230;
            sprite.render(g);
        }

    }
}
