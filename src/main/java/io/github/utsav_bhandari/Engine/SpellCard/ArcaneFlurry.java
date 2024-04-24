package io.github.utsav_bhandari.Engine.SpellCard;

import java.awt.*;

public class ArcaneFlurry extends ASpellCard implements ISpellCard {
    public String getName() {
        return "Arcane Flurry";
    }
    public String getDescription() {
        return "Unleashes a flurry of mystical energy. 1/3 chance to inflict double damage.";
    }

    @Override
    public void render(Graphics2D g) {
    }

    @Override
    public int getMinCharge() {
        return 0;
    }

    @Override
    public int getMaxCharge() {
        return 0;
    }

    @Override
    public void renderSpell(Graphics2D g) {

    }
}
