package io.github.utsav_bhandari.Render;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Terrible implementation. Should be <code>SpriteManager -> AnimatedSpriteRoot -> AnimatedSprite</code>
 * <br>
 * But then this is already very complicated
 */
public class AnimatedSpriteRoot {

    // Cache
    static final HashMap<String, AnimatedSpriteRoot> animatedSpriteRoots = new HashMap<>();
    public final String id;
    public final ArrayList<BufferedImage> frames = new ArrayList<>();
    public final int width;
    public final int height;
    public final int frameCount;
    public final int sourceTargetFps;
    public final int destRenderFps;
    public final boolean zigzag;

    public final int repeat;

    protected AnimatedSpriteRoot(String ID, BufferedImage image, int width, int height, int x, int y, int dx, int dy, int frameCount, int sourceTargetFps, int destRenderFps, int repeat, boolean zigzag) {
        id = ID;
        this.width = width;
        this.height = height;
        this.frameCount = frameCount;
        this.sourceTargetFps = sourceTargetFps;
        this.destRenderFps = destRenderFps;
        this.repeat = repeat;
        this.zigzag = zigzag;

        // construct frames here
        int sx = x;
        int sy = y;

        for (int i = 0; i < frameCount; i++) {
            var subImage = image.getSubimage(sx, sy, width, height);
            frames.add(subImage);

            sx += dx;
            sy += dy;
        }
    }

    public static AnimatedSprite getAnimatedSprite(String id) {
        var root = animatedSpriteRoots.get(id);

        if (root == null) {
            throw new IllegalArgumentException("ID '" + id + "' does not exist");
        }

        return new AnimatedSprite(root);
    }

    /**
     * Register a new animated sprite
     * @param id The unique identifier for this sprite
     * @param image The image to use. MAKE SURE TO DISPOSE THIS IMAGE AFTER REGISTERING
     * @param x The x coordinate of the first frame
     * @param y The y coordinate of the first frame
     * @param dx The x coordinate difference between frames
     * @param dy The y coordinate difference between frames
     * @param frameCount The number of frames
     * @param sourceTargetFps Screen fps
     * @param destRenderFps Render fps (e.g. if 6 and sourceTargetFps is 60, then will render every 10th frame)
     * @param zigzag Whether to go back and forth instead of looping in one direction
     */
    public static AnimatedSpriteRoot registerAnimatedSprite(String id, BufferedImage image, int width, int height, int x, int y, int dx, int dy, int frameCount, int sourceTargetFps, int destRenderFps, int repeat, boolean zigzag) {
        if (animatedSpriteRoots.containsKey(id)) {
            throw new IllegalArgumentException("ID already exists");
        }

        var root = new AnimatedSpriteRoot(id, image, width, height, x, y, dx, dy, frameCount, sourceTargetFps, destRenderFps, repeat, zigzag);

        animatedSpriteRoots.put(id, root);

        return root;
    }
}
