package io.github.utsav_bhandari.Engine.SpellCard;

public interface ISpellCard {
    /**
     * Minimum required charges
     */
    int getMinCharge();

    /**
     * Maximum charges you can spend on this card
     */
    int getMaxCharge();
}
