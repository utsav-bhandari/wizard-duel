package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.awt.*;

public class VirulentEruption extends ASpellCard implements ISpellCard {
    public String getName() {
        return "Virulent Eruption";
    }
    public String getDescription() {
        return "Unleashes a corrosive blast of acidic energy.\n" +
                "Damage scales based on the health lost by the opponent."; // every 10 health lost +1 dmg for now
    }

    @Override
    public void render(Graphics2D g) {
    }

    @Override
    public int getCharge() {
        return 1;
    }

    @Override
    public void renderSpell(Graphics2D g) {

    }
    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("VirulentEruption");
    }

}
