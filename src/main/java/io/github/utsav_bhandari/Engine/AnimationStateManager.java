package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.util.HashMap;
import java.util.function.Function;

public class AnimationStateManager {
    private final Function<String, AnimatedSprite> spriteProvider;
    private final String defaultState;
    private AnimatedSprite _sprite;
    private final HashMap<String, String> transitionMap = new HashMap<>();
    private final HashMap<String, AnimatedSprite> _spriteCache = new HashMap<>();
    private String state;

    public AnimationStateManager(Function<String, AnimatedSprite> spriteProvider, String defaultState) {
        this.spriteProvider = spriteProvider;
        this.defaultState = defaultState;
        this.state = defaultState;
        this._sprite = spriteProvider.apply(defaultState);
    }

    public AnimatedSprite getSprite() {
        // Inspect for update
        if (this._sprite.isDone()) {
            String target = transitionMap.get(state);

            this._sprite = this._spriteCache.get(target != null ? target : defaultState);
        }

        return this._sprite;
    }

    public void setState(String state) {
        String s = this.state;
        this.state = state;

        if (!s.equals(state)) {
            this._sprite = _spriteCache.get(this.state);
            this._sprite.reset();
        }
    }

    private void registerCacheIfNotExist(String id) {
        if (_spriteCache.containsKey(id)) return;

        _spriteCache.put(id, spriteProvider.apply(id));
    }

    public void registerTransition(String start, String dest) {
        registerCacheIfNotExist(start);
        registerCacheIfNotExist(dest);

        transitionMap.put(start, dest);
    }
}
