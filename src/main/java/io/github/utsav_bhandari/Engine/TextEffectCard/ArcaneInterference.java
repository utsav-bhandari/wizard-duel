package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ITextEffectCard;
import io.github.utsav_bhandari.Engine.Player;

import java.awt.*;

public class ArcaneInterference extends ATextEffectCard implements ITextEffectCard {
    @Override
    public void render(Graphics2D g) {
        g.drawString("Arcane Interference", 100, 100);
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
}
