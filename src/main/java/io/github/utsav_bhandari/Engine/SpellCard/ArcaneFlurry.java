package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.ISpellCard;

import java.awt.*;

public class ArcaneFlurry extends ASpellCard implements ISpellCard {
    public String getName() {
        return "Arcane Flurry";
    }
    public String getDescription() {
        return "Unleashes a flurry of mystical energy. 33% chance to inflict double damage.";
    }

    @Override
    public void render(Graphics2D g) {
    }
}
