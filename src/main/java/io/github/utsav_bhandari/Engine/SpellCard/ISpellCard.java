package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.ICard;
import io.github.utsav_bhandari.Engine.Player;

import java.awt.*;
import java.awt.image.AffineTransformOp;

public interface ISpellCard extends ICard {

    Player getTarget();

    void setTarget(Player target);

    /**
     * Maximum charges you can spend on this card
     */
    int getCharge();

    float getDamage();
    void setDamage(float damage);

    /**
     * Prepare to cast the spell
     * <br>
     * Can block but should continue to be in the "primed" state
     * <br>
     * Check example for more information
     */
    void prime();

    /**
     * Cast the spell
     * <br>
     * Should be a blocking call (i.e. should do proper animations etc.)
     */
    void cast();

    boolean isNeutral();
    boolean isPrimed();
    boolean isCasting();

    /**
     * Camera shake
     */
    AffineTransformOp shake();

    /**
     * Render the spell, not the card. Handled by caller
     * @param g
     */
    void renderSpell(Graphics2D g);

    void confirmChargeUse();

    boolean isChargeUseConfirmed();
    boolean isChargeUse();

    void toggleChargeUse();

}
