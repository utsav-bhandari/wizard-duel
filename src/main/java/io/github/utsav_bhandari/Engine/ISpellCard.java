package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.image.AffineTransformOp;

public interface ISpellCard extends ICard, IRenderable {
    float getDamage();
    void setDamage(float damage);

    /**
     * Prepare to cast the spell
     */
    void prime();

    /**
     * Cast the spell
     */
    void cast();

    boolean isNeutral();
    boolean isPrimed();
    boolean isCasting();

    /**
     * Camera shake
     */
    AffineTransformOp shake();
}
