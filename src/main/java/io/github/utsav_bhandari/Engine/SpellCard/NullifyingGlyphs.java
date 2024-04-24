package io.github.utsav_bhandari.Engine.SpellCard;

import java.awt.*;

public class NullifyingGlyphs extends ASpellCard implements ISpellCard {
    public String getName() {
        return "Nullifying Glyphs";
    }
    public String getDescription() {
        return "Conjures glyphs of nullification.\n" +
                "Removes a random text effect card from the enemy's side.";
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
