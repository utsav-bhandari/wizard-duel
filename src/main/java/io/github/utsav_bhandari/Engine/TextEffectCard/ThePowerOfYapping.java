package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.GameHookEvent;
import io.github.utsav_bhandari.Engine.ICard;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;

public class ThePowerOfYapping extends ATextEffectCard implements IRenderable, ITextEffectCard {
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

    @Override
    public boolean hook(GameHookEvent event) {
        System.out.println("Yap Yap Yap");
        return true;
    }
}