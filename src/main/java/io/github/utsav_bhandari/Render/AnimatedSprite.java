package io.github.utsav_bhandari.Render;

import io.github.utsav_bhandari.Lib.CyclicCounter;
import io.github.utsav_bhandari.Lib.FrameMixer;
import io.github.utsav_bhandari.Lib.ICounter;
import io.github.utsav_bhandari.Lib.ZigzagCounter;

import java.awt.*;
import java.awt.image.BufferedImageOp;

public class AnimatedSprite implements IRenderable {
    public final AnimatedSpriteRoot root;

    public final ICounter frameCounter;
    protected final FrameMixer frameMixer;

    public BufferedImageOp op;
    public float x;
    public float y;

    protected int repeatCount = 0;

    protected AnimatedSprite(AnimatedSpriteRoot r) {
        root = r;
        if (r.zigzag) {
            frameCounter = new ZigzagCounter(0, r.frameCount - 1, 0);
        } else {
            frameCounter = new CyclicCounter(r.frameCount);
        }
        frameMixer = new FrameMixer(r.sourceTargetFps, r.destRenderFps);
    }

    public int getWidth() {
        return root.width;
    }

    public int getHeight() {
        return root.height;
    }

    public boolean isDone() {
        return repeatCount >= root.repeat;
    }

    public void render(Graphics2D g) {
        // TODO fix tick return value
        if (frameMixer.tick() > 0 && (repeatCount < root.repeat || root.repeat < 0)) {
            frameCounter.increment();
            if (frameCounter.isMax()) {
                repeatCount++;
            }
        }

        int frameIdx = frameCounter.getValue();

        var frame = root.frames.get(frameIdx);

        g.drawImage(frame, op, (int) x, (int) y);
    }

    public void reset() {
        frameCounter.reset();
        frameMixer.reset();
        repeatCount = 0;
    }
}
