package io.github.utsav_bhandari.Engine;

public interface IEntity {
    /**
     * 1 update per fps
     */
    void update();

    /**
     * Renders the entity on screen
     */
    void render();
    void destroy();
}
