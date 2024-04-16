package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Render.AnimatedSpriteRoot;

import java.util.HashMap;

/**
 * Singleton. Simple resource manager.
 */
public final class Resource {
    private static Resource instance;

    /**
     * Simple alias
     */
    public final Class<AnimatedSpriteRoot> sprites = AnimatedSpriteRoot.class;

    private Resource() {
        // TODO: init stuff here
    }

    public static Resource getInstance() {
        if (instance == null) {
            instance = new Resource();
        }
        return instance;
    }
}
