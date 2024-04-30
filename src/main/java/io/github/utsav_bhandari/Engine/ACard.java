package io.github.utsav_bhandari.Engine;

import java.awt.image.BufferedImage;

public class ACard extends AEntity {
    public Player owner;

    public BufferedImage thumbnail = null;
    public BufferedImage getThumbnail() {
        if (thumbnail == null) {
            throw new RuntimeException();
        }

        return this.thumbnail;
    }

    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
