package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ITextEffectCard;
import io.github.utsav_bhandari.Engine.Player;

import java.awt.*;

public class SiphoningEnchantment extends ATextEffectCard implements ITextEffectCard {
    @Override
    public void render(Graphics2D g) {
        g.drawString("Siphoning Enchantment", 100, 100);
    }

    @Override
    public String getName() {
        return "Siphoning Enchantment";
    }

    @Override
    public String getDescription() {
        return "Empowers the caster with draining magic.\n" +
                "When dealing damage, gain half the damage dealt as health.";
    }
}
