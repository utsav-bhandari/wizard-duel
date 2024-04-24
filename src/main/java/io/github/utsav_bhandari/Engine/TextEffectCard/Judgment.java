package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ICard;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;

public class Judgment extends ATextEffectCard implements IRenderable, ICard {
    @Override
    public void render(Graphics2D g) {
        g.drawString("Judgment", 100, 100);
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
}
