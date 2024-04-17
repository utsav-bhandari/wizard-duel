package io.github.utsav_bhandari.Engine;

public class Player {
    private int health;
    private int maxHealth;

    private ISpellCard currentSpellCard;
    private ITextEffectCard currentTextEffectCard;

    final private TextEffectList textEffectList = new TextEffectList();

    /**
     * If true, display help page on their screen
     */
    public boolean isViewingHelp = false;

    public Player(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }
}
