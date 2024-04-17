package io.github.utsav_bhandari.Engine;

public abstract class AEntity {
    public float x = 0;
    public float y = 0;
    public float dx = 0;
    public float dy = 0;

    public void updatePosition() {
        x += dx;
        y += dy;
    }
}
