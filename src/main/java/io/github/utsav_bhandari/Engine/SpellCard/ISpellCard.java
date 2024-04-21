package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.ICard;

public interface ISpellCard extends ICard {
    /**
     * Minimum required charges
     */
    int getMinCharge();

    /**
     * Maximum charges you can spend on this card
     */
    int getMaxCharge();
}
