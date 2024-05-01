package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Engine.WorldEventHook;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;

public class ChargeDifferential extends ATextEffectCard implements IRenderable, ITextEffectCard {
    {
        thumbnail = Resource.getInstance().getCardThumbnailArt("ChargeDifferential");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, null, (int) this.x, (int) this.y);
    }

    @Override
    public String getName() {
        return "Charge Differential";
    }

    @Override
    public String getDescription() {
        return "Exploits the charge difference between foes.\n" +
                "When attacking, deal damage scaled by the absolute difference\n" +
                "between player charge and enemy charge";
    }

    @Override
    public boolean hook(WorldEventHook event) {
        return false;
    }
}
