package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ICard;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;

public class HealingIncantation extends ATextEffectCard implements IRenderable, ICard {
    @Override
    public void render(Graphics2D g) {
        g.drawString("Healing Incantation", 100, 100);
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
