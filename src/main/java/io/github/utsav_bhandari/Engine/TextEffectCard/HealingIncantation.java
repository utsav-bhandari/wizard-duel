package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Engine.WorldEventHook;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;

public class HealingIncantation extends ATextEffectCard implements IRenderable, ITextEffectCard {
    {
        thumbnail = Resource.getInstance().getCardThumbnailArt("HealingIncantation");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, null, (int) this.x, (int) this.y);
    }

    @Override
    public String getName() {
        return "Healing Incantation";
    }

    @Override
    public String getDescription() {
        return "Channels the magic of healing.\n" +
                "Heal double the number of charges used until this point in amount.";
    }

    @Override
    public boolean hook(WorldEventHook event) {
        return false;
    }
}
