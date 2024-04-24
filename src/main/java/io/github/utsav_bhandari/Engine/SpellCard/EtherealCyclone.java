package io.github.utsav_bhandari.Engine.SpellCard;

import java.awt.*;

public class EtherealCyclone extends ASpellCard implements ISpellCard {
    public String getName() {
        return "Ethereal Cyclone";
    }
    public String getDescription() {
        return "Unleashes a swirling vortex of energy. If your charges exceed your opponent's,\n" +
                "inflict 1.5x damage. Otherwise, gain 2 charges.";
    }

    @Override
    public void render(Graphics2D g) {
    }

    @Override
    public int getCharge() {
        return 3;
    }

    @Override
    public void renderSpell(Graphics2D g) {

    }
}
