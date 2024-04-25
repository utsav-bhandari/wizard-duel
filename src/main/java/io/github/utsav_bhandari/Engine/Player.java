package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ISpellCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ITextEffectCard;
import io.github.utsav_bhandari.Lib.StdDrawBridge;
import io.github.utsav_bhandari.Render.AnimatedSprite;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImageOp;
import java.util.ArrayList;

public class Player extends AEntity implements IEntity, IPlayerControl, IRenderable {
    private final int id;
    private final AnimatedSprite idleAnimation;
    private final BufferedImageOp flipTransformOp;

    private int health = 80;
    private int maxHealth = 90;

    private int charge = 0;
    private int maxCharge = 5;

    // Their hand
    private ISpellCard currentSpellCard;
    private ITextEffectCard currentTextEffectCard;

    private PlayerKeymap keymap;


    public ArrayList<ITextEffectCard> textEffectCards = new ArrayList<>();

    private boolean viewingHelp = false;

    public Player(int id) {
        this.id = id;

        var r = Resource.getInstance();

        idleAnimation = r.getAnimatedSprite("Wizard Idle");

        var flipTransform = new AffineTransform();

        flipTransform.scale(id == 0 ? 1 : -1, 1);

        flipTransformOp = new AffineTransformOp(flipTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
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
        setViewingHelp(!isViewingHelp());
    }

    @Override
    public void render(Graphics2D g) {
        idleAnimation.x = x;
        idleAnimation.y = y;

        g.translate(x, y);
        g.setColor(Color.BLACK);
        g.drawString("Player " + id, 10, 10);
        g.translate(-x, -y);

        idleAnimation.op = flipTransformOp;
        idleAnimation.render(g);
    }

    public PlayerKeymap getKeymap() {
        return keymap;
    }

    public void setKeymap(PlayerKeymap keymap) {
        this.keymap = keymap;
    }

    /**
     * If true, display help page on their screen
     */
    public boolean isViewingHelp() {
        return viewingHelp;
    }

    public void setViewingHelp(boolean viewingHelp) {
        this.viewingHelp = viewingHelp;
    }
}
