package io.github.utsav_bhandari.Engine;

// Leaving this here for documentation purposes
// This is not a great way to go about
public interface ICard {
    Player getOwner();
    void setOwner(Player owner);
    String getName();
    String getDescription();
}
