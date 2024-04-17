package io.github.utsav_bhandari.Engine;

public interface IEntity {
    /**
     * 1 update per fps
     */
    void update();

    void destroy();
}
