package io.github.utsav_bhandari.Engine;

public abstract class AEntity {
    protected World world;
    public float x = 0;
    public float y = 0;
    public float vx = 0;
    public float vy = 0;

    public void updatePosition() {
        x += vx;
        y += vy;
    }

    public void update() {

    }

    public void dispose() {

    }
}
