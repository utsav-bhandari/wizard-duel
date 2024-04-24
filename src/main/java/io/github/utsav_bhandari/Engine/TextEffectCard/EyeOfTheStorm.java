package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ICard;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;

public class EyeOfTheStorm extends ATextEffectCard implements IRenderable, ICard {
    @Override
    public void render(Graphics2D g) {
        g.drawString("Eye Of The Storm", 100, 100);
    }

    @Override
    public String getName() {
        return "Eye Of The Storm";
    }

    @Override
    public String getDescription() {
        return "A tranquil aura envelops the battlefield. If you have zero charge,\n" +
                "gain 1 charge and 5 health, else gain 1 charge only."; // 5 health for now
    }
}
