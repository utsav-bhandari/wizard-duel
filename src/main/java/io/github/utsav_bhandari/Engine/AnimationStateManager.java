package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.util.HashMap;
import java.util.function.Function;

public class AnimationStateManager {
    private final Function<String, AnimatedSprite> spriteProvider;
    private AnimatedSprite _sprite;
    private final HashMap<String, String> transitionMap = new HashMap<>();
    private final HashMap<String, AnimatedSprite> _spriteCache = new HashMap<>();
    private String state;

    public AnimationStateManager(Function<String, AnimatedSprite> spriteProvider, String defaultState) {
        this.spriteProvider = spriteProvider;
        this.state = defaultState;
        this._sprite = spriteProvider.apply(defaultState);
    }

    public AnimatedSprite getSprite() {
        // Inspect for update
        if (this._sprite.isDone()) {
            String target = transitionMap.get(state);

            if (target == null) return this._sprite;

            this._sprite = this._spriteCache.get(target);
        }

        return this._sprite;
    }

    public void setState(String state) {
        String s = this.state;
        this.state = state;

        if (!s.equals(state)) {
            registerCacheIfNotExist(state);
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
