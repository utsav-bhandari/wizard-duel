package io.github.utsav_bhandari.Engine.SpellCard;
import io.github.utsav_bhandari.Engine.ISpellCard;
import java.awt.*;

public class TempestReversal extends ASpellCard implements ISpellCard{

    @Override
    public String getName() {
        return "Tempest Reversal";
    }

    @Override
    public String getDescription() {
        return "Conjures a tempest to disrupt the enemy's charges.\n" +
                "Decreases enemy charge by 2. If \"Eye of the Storm\" is in play,\n" +
                "wipe all enemy charges.\n";
    }
    @Override
    public void render(Graphics2D g) {
    }
}
