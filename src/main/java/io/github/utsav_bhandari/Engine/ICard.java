package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.image.BufferedImage;

// Leaving this here for documentation purposes
// This is not a great way to go about
public interface ICard extends IEntity, IRenderable {
    BufferedImage getThumbnail();
    Player getOwner();
    void setOwner(Player owner);
    String getName();
    String getDescription();
}
