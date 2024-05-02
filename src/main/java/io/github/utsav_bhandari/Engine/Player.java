package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ISpellCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ITextEffectCard;
import io.github.utsav_bhandari.Render.AnimatedSprite;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

public class Player extends AEntity implements IEntity, IPlayerControl, IRenderable {
    private final int id;
    private AnimationStateManager animationManager;
    private final BufferedImageOp transformOp;
    private final AffineTransform flipTransform;

    private float health = 80;
    private float maxHealth = 80;

    private int charge = 0;
    private int maxCharge = 3;

    /**
     * TODO comment
     */
    public final java.util.List<ISpellCard> spellCardPile = new ArrayList<>();
    /**
     * TODO comment
     */
    public final List<ITextEffectCard> textEffectCardPile = new ArrayList<>();
    // Their hand
    private ISpellCard currentSpellCard;
    private ITextEffectCard currentTextEffectCard;

    private PlayerKeymap keymap;

    public ArrayList<ITextEffectCard> textEffectCards = new ArrayList<>();

    private boolean viewingHelp = false;

    public Player(int id) {
        this.id = id;

        var r = Resource.getInstance();

        animationManager = new AnimationStateManager(r::getAnimatedSprite, "Wizard Idle");
        animationManager.registerTransition("WizardAttack1", "Wizard Idle");
        animationManager.registerTransition("WizardAttack2", "Wizard Idle");
        animationManager.registerTransition("TakeHit", "Wizard Idle");

        flipTransform = new AffineTransform();

        flipTransform.scale(id == 0 ? 1 : -1, 1);
        flipTransform.scale(2, 2);
        transformOp = new AffineTransformOp(flipTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    }


    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getMaxHealth() {
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

    public int getId() {
        return id;
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

        g.setColor(Color.BLACK);
        g.drawString("Player " + (id + 1), 0, 0);

        getSprite().x = (float) (x - flipTransform.getScaleX() * getSprite().getWidth() / 2);
        getSprite().y = (float) (y - flipTransform.getScaleY() * getSprite().getHeight() / 2);
        getSprite().op = transformOp;
        getSprite().render(g);
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

    public ISpellCard getCurrentSpellCard() {
        return currentSpellCard;
    }

    public void setCurrentSpellCard(ISpellCard currentSpellCard) {
        this.currentSpellCard = currentSpellCard;
    }


    /**
     * Take the last spell card from the pile and remove it
     */
    public ISpellCard drawSpellCard() {
        if (spellCardPile.isEmpty()) {
            return null;
        }

        int lastIndex = spellCardPile.size() - 1;

        return spellCardPile.remove(lastIndex);
    }

    /**
     * Take the last text effect card from the pile and remove it
     */
    public ITextEffectCard drawTextEffectCard() {
        if (textEffectCardPile.isEmpty()) {
            return null;
        }

        int lastIndex = textEffectCardPile.size() - 1;

        return textEffectCardPile.remove(lastIndex);
    }

    public AnimatedSprite getSprite() {
        return animationManager.getSprite();
    }

    public void setAnimationState(String state) {
        animationManager.setState(state);
    }
}
