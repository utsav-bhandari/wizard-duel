package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Engine.WorldEventHook;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;

public class Judgment extends ATextEffectCard implements IRenderable, ITextEffectCard {
    {
        thumbnail = Resource.getInstance().getCardThumbnailArt("Judgment");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, null, (int) this.x, (int) this.y);
    }

    @Override
    public String getName() {
        return "Judgment";
    }

    @Override
    public String getDescription() {
        return "Enforces balance upon the duel. Reduces both players' charges to the lowest current charge.\n" +
                "If you had the lower charge, gain a damage buff;\n" +
                "otherwise, wipe the enemy's charges.";
    }

    @Override
    public boolean hook(WorldEventHook event) {
        return false;
    }
}
