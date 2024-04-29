package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ICard;
import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealingIncantation extends ATextEffectCard implements IRenderable, ICard {
    public static final BufferedImage thumbnail = Resource.getInstance().cardThumbnails.get("HealingIncantation");

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, null, (int)this.x, (int)this.y);
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
}
