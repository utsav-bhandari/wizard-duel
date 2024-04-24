package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.GameHookEvent;
import io.github.utsav_bhandari.Engine.ICard;
import io.github.utsav_bhandari.Render.IRenderable;

public interface ITextEffectCard extends ICard, IRenderable {
    /**
     * Return true if card wishes to be removed
     */
    boolean hook(GameHookEvent event);
}
