package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ISpellCard;

public class AttackEvent {
    public final ISpellCard spell;

    public AttackEvent(ISpellCard spell) {
        this.spell = spell;
    }
}
