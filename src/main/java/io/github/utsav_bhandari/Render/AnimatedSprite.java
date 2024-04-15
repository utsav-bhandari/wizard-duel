package io.github.utsav_bhandari.Render;

import io.github.utsav_bhandari.Lib.CyclicCounter;
import io.github.utsav_bhandari.Lib.FrameMixer;
import io.github.utsav_bhandari.Lib.ICounter;
import io.github.utsav_bhandari.Lib.ZigzagCounter;

import java.awt.*;
import java.awt.image.BufferedImageOp;

public class AnimatedSprite implements IRenderable {
    public final AnimatedSpriteRoot root;

    protected final ICounter frameCounter;
    protected final FrameMixer frameMixer;

    public BufferedImageOp op;
    public int x;
    public int y;

    protected AnimatedSprite(AnimatedSpriteRoot r) {
        root = r;
        if (r.zigzag) {
            frameCounter = new ZigzagCounter(0, r.frameCount - 1, 0);
        } else {
            frameCounter = new CyclicCounter(r.frameCount);
        }
        frameMixer = new FrameMixer(r.sourceTargetFps, r.destRenderFps);
    }

    /**
     * Rotation is applied first. Rotation is in radians
     */
    public void render(Graphics2D g) {
        if (frameMixer.tick() > 0) {
            frameCounter.increment();
        }

        int frameIdx = frameCounter.getValue();

        var frame = root.frames.get(frameIdx);

        g.drawImage(frame, op, x, y);
    }
}
