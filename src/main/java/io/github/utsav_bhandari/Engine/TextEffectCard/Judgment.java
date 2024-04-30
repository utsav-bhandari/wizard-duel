package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ICard;
import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Engine.TurnEventHook;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Judgment extends ATextEffectCard implements IRenderable, ITextEffectCard {
    public static final BufferedImage thumbnail = Resource.getInstance().cardThumbnails.get("Judgment");

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, null, (int)this.x, (int)this.y);
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
    public boolean hook(TurnEventHook event) {
        return false;
    }
}
