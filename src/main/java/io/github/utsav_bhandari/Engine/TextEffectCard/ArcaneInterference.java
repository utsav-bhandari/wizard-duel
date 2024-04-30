package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ICard;
import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Engine.TurnEventHook;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ArcaneInterference extends ATextEffectCard implements IRenderable, ITextEffectCard {
    {
        thumbnail = Resource.getInstance().getCardThumbnailArt("ArcaneInterference");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, null, (int) this.x, (int) this.y);
    }

    @Override
    public String getName() {
        return "Arcane Interference";
    }

    @Override
    public String getDescription() {
        return "Causes interference in the enemy's incantation.\n" +
                "1/3 chance the enemy wizard's spell fails, dealing zero damage.";
    }

    @Override
    public boolean hook(TurnEventHook event) {
        return false;
    }
}
