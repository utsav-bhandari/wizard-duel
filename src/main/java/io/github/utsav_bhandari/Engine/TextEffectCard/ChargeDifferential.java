package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ITextEffectCard;
import io.github.utsav_bhandari.Engine.Player;

import java.awt.*;

public class ChargeDifferential extends ATextEffectCard implements ITextEffectCard {
    @Override
    public void render(Graphics2D g) {
        g.drawString("Charge Differential", 100, 100);
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
}
