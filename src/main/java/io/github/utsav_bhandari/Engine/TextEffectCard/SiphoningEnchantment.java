package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Engine.WorldEventHook;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;

public class SiphoningEnchantment extends ATextEffectCard implements IRenderable, ITextEffectCard {
    {
        thumbnail = Resource.getInstance().getCardThumbnailArt("SiphoningEnchantment");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, null, (int) this.x, (int) this.y);
    }

    @Override
    public String getName() {
        return "Siphoning Enchantment";
    }

    @Override
    public String getDescription() {
        return "Empowers the caster with draining magic.\n" +
                "When dealing damage, gain half the damage dealt as health.";
    }

    @Override
    public boolean hook(WorldEventHook event) {
        return false;
    }
}
