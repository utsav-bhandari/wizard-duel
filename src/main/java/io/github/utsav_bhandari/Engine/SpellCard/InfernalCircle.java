package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.ISpellCard;

import java.awt.*;

public class InfernalCircle extends ASpellCard implements ISpellCard {
    public String getName() {
        return "Infernal Circle";
    }
    public String getDescription() {
        return " Conjures a ring of searing flames. 40% chance to amplify damage by 1.5x.\n " +
                "However, there's a 20% chance of being singed, dealing half the damage to yourself.";
    }

    @Override
    public void render(Graphics2D g) {
    }
}
