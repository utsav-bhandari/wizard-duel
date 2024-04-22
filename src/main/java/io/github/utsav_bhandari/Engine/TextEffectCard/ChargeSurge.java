package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ITextEffectCard;
import io.github.utsav_bhandari.Engine.Player;

import java.awt.*;

public class ChargeSurge extends ATextEffectCard implements ITextEffectCard {
    @Override
    public void render(Graphics2D g) {
        g.drawString("Charge Surge", 100, 100);
    }

    @Override
    public String getName() {
        return "Charge Surge";
    }

    @Override
    public String getDescription() {
        return "A surge of energy revitalizes the caster. When below half health, charges are maxed.\n" +
                "If already below half health, instantly gain max charges.\n";
    }
}
