package io.github.utsav_bhandari.UI;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Lib.StdDrawBridge;
import io.github.utsav_bhandari.Render.AnimatedSprite;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.util.ArrayList;

public final class HelpOverlay implements IRenderable {
    private final int side;
    private final Color helpBackgroundColorRight;
    private final Color helpBackgroundColorLeft;
    private ArrayList<AnimatedSprite> animatedSprites;

    /* side of 0 represents the left and side of 1 represents the right side of the screen */
    public HelpOverlay(int side) {
        this.side = side;
        this.helpBackgroundColorRight = new Color(1f, 0f, 0f, 0.2f);
        this.helpBackgroundColorLeft = new Color(0f, 0f, 1f, 0.2f);

        var r = Resource.getInstance();
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
        g.fillRect(xPosition + 10, 10, helpScreenWidth - 20 , screenHeight - 20);

        int spriteStartX;
        if (side == 0) {
            spriteStartX = xPosition + 20; // Start position for left side
        } else {
            spriteStartX = xPosition + 10; // Adjusted start position for right side
        }

        int i = 0;
        for (var sprite : animatedSprites) {
            sprite.x = spriteStartX + i * 50; // Adjusted x position
            sprite.y = 50;
            sprite.render(g);
            i += 2;
        }
    }
}
