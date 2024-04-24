package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ISpellCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ITextEffectCard;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends AEntity implements IEntity, IPlayerControl, IRenderable {
    private final int id;

    private int health = 80;
    private int maxHealth = 90;

    private int charge = 0;
    private int maxCharge = 5;

    // Their hand
    private ISpellCard currentSpellCard;
    private ITextEffectCard currentTextEffectCard;

    private PlayerKeymap keymap;


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

    @Override
    public void toggleChargeUse() {
        var turn = world.getCurrentTurn();
        if (turn == null) return;
        if (turn.attacker != this) return;

        if (world.getWorldState() >= World.WORLD_STATE_CHARGE_ADDED && world.getWorldState() < World.WORLD_STATE_ON_SPELL_PRIME) {
            turn.setUsingCharge(!turn.isUsingCharge());
        }
    }

    private Selection getPlayerSelection() {
        var round = world.getCurrentRound();

        if (round == null) return null;

        return round.getPlayerSelection(this);
    }

    @Override
    public void moveChoice(int direction) {
        var selection = getPlayerSelection();

        if (selection == null) return;

        selection.moveSelection(direction);
    }

    @Override
    public void confirmChoice() {
        var selection = getPlayerSelection();

        if (selection == null) return;

        selection.confirmSelection();
    }

    @Override
    public void toggleHelp() {
        isViewingHelp = !isViewingHelp;
    }

    @Override
    public void render(Graphics2D g) {
        g.translate(x, y);

        g.setColor(Color.BLACK);
        g.drawString("Player " + id, 10, 10);

        g.translate(-x, -y);
    }

    public PlayerKeymap getKeymap() {
        return keymap;
    }

    public void setKeymap(PlayerKeymap keymap) {
        this.keymap = keymap;
    }
}
