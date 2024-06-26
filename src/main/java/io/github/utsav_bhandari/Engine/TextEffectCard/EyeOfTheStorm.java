package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Engine.WorldEventHook;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;

public class EyeOfTheStorm extends ATextEffectCard implements IRenderable, ITextEffectCard {
    {
        thumbnail = Resource.getInstance().getCardThumbnailArt("EyeOfTheStorm");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, null, (int) this.x, (int) this.y);
    }

    @Override
    public String getName() {
        return "Eye Of The Storm";
    }

    @Override
    public String getDescription() {
        return "A tranquil aura envelops the battlefield.\n" +
                "If you have zero charge,\n" +
                "gain 1 charge and 5 health, else gain 1 charge only."; // 5 health for now
    }

    @Override
    public boolean hook(WorldEventHook event) {
        return false;
    }
}
