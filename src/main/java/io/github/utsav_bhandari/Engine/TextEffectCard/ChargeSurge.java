package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ICard;
import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Engine.TurnEventHook;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ChargeSurge extends ATextEffectCard implements IRenderable, ITextEffectCard {
    public static final BufferedImage thumbnail = Resource.getInstance().cardThumbnails.get("ChargeSurge");

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, null, (int)this.x, (int)this.y);
    }
    @Override
    public String getName() {
        return "Charge Surge";
    }

    @Override
    public String getDescription() {
        return "A surge of energy revitalizes the caster. When below half health, charges are maxed.\n" +
                "If already below half health, instantly gain max charges.\n";
    }

    @Override
    public boolean hook(TurnEventHook event) {
        return false;
    }
}
