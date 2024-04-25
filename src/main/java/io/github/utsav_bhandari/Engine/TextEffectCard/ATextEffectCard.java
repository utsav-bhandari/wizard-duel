package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ACard;
import io.github.utsav_bhandari.Engine.Player;

import java.awt.image.BufferedImage;

public abstract class ATextEffectCard extends ACard {
    public BufferedImage getThumbnail() {
        throw new UnsupportedOperationException();
    }
}
