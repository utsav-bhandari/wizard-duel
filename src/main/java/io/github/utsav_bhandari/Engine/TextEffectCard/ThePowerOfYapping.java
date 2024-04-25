package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.TurnEventHook;
import io.github.utsav_bhandari.Engine.World;
import io.github.utsav_bhandari.Lib.Util;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;

public class ThePowerOfYapping extends ATextEffectCard implements IRenderable, ITextEffectCard {
    @Override
    public void render(Graphics2D g) {
        g.drawString("The Power of Yapping", x, y);
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
    public boolean hook(TurnEventHook event) {
        if (event.turn.attacker != owner) return false;

        if (event.world.getWorldState() != World.WORLD_STATE_CHARGE_ADDED) return false;

        Util.unsafeWait(1000);
        System.out.println("Yap Yap Yap");

        return true;
    }
}
