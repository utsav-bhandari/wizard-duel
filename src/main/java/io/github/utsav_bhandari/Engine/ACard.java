package io.github.utsav_bhandari.Engine;

public class ACard extends AEntity {
    public Player owner;

    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
