package io.github.utsav_bhandari.Engine;

import java.util.ArrayList;
import java.util.List;

public class Player extends AEntity implements IEntity{
    private final int id;

    private int health = 80;
    private int maxHealth = 90;

    private int charge = 0;
    private int maxCharge = 5;

    // Their hand
    private ISpellCard currentSpellCard;
    private ITextEffectCard currentTextEffectCard;

    /**
     * Be careful using this variable (frequently null)
     */
    private Selection selection;

    final private List<ITextEffectCard> textEffectCards = new ArrayList<>();

    /**
     * If true, display help page on their screen
     */
    public boolean isViewingHelp = false;

    public Player(int id) {
        this.id = id;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getMaxCharge() {
        return maxCharge;
    }

    public void setMaxCharge(int maxCharge) {
        this.maxCharge = maxCharge;
    }

    /**
     * May be null
     */
    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }
}
