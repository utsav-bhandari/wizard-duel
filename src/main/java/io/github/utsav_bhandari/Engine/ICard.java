package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Render.IRenderable;

// Leaving this here for documentation purposes
// This is not a great way to go about
public interface ICard extends IEntity, IRenderable {
    Player getOwner();
    void setOwner(Player owner);
    String getName();
    String getDescription();
}
