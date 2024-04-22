package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ITextEffectCard;
import io.github.utsav_bhandari.Engine.Player;

import java.awt.*;

public class ArcaneDrainage extends ATextEffectCard implements ITextEffectCard {
    @Override
    public void render(Graphics2D g) {
        g.drawString("Arcane Drainage", 100, 100);
    }

    @Override
    public String getName() {
        return "Arcane Drainage";
    }

    @Override
    public String getDescription() {
        return "Voids 2 charges from the enemy when played.\n" +
                "If the enemy's charge becomes 0 as a result,\n" +
                "gain a 20% damage buff for this attack."; // 20% for now
    }
}
