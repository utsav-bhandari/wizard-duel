package io.github.utsav_bhandari.Engine.SpellCard;

import io.github.utsav_bhandari.Engine.ACard;

import java.awt.image.AffineTransformOp;

public abstract class ASpellCard extends ACard {
    private float damage = 0;

    protected int state = 0;

    public void reset() {
        state = 0;
    }

    public void prime() {
        state = 1;
    }

    public void cast() {
        state = 2;
    }

    public AffineTransformOp shake() {
        return null;
    }

    public boolean isNeutral() {
        return state == 0;
    }

    public boolean isPrimed() {
        return state == 1;
    }

    public boolean isCasting() {
        return state == 2;
    }


    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}