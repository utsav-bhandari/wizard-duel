package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

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
    public int getCharge() {
        return 2;
    }

    @Override
    public void renderSpell(Graphics2D g) {

    }
    private static AnimatedSprite getNewSprite() {
        return Resource.getInstance().getAnimatedSprite("ArcaneFlurry");
    }

}
