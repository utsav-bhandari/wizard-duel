package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.ITextEffectCard;
import io.github.utsav_bhandari.Engine.Player;

import java.awt.*;

public class ThePowerOfYapping extends ATextEffectCard implements ITextEffectCard {
    @Override
    public void render(Graphics2D g) {
        g.drawString("The Power of Yapping", 100, 100);
    }

    @Override
    public String getName() {
        return "The Power of Yapping";
    }

    @Override
    public String getDescription() {
        return "Yap Yap Yap";
    }
}
