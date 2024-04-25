package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ISpellCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ITextEffectCard;
import io.github.utsav_bhandari.Lib.StdDrawBridge;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.util.List;

/**
 * Class representing the cards that player has to select
 * <br>
 * There will be some text effect cards and player chooses one
 * <br>
 * and also some spell cards and player chooses one
 */
public class Selection implements IRenderable {
    private final List<ITextEffectCard> textEffectCardChoices;
    private final List<ISpellCard> spellCardChoices;
    private final AlphaComposite alphaComposite;

    private int textEffectCardChoice = 0;
    private int spellCardChoice = 0;

    private int state = 0;

    public Selection(List<ITextEffectCard> textEffectCardChoices, List<ISpellCard> spellCardChoices) {
        alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        this.textEffectCardChoices = textEffectCardChoices;
        this.spellCardChoices = spellCardChoices;
    }

    /**
     * 1 for right, -1 for left
     */
    public void moveSelection(int direction) {
        int d = direction == 0 ? 1 : -1;

        if (state == 0) {
            setCurrentChoice((getCurrentChoice() + direction + textEffectCardChoices.size()) % textEffectCardChoices.size());
        } else if (state == 1) {
            setCurrentChoice((getCurrentChoice() + direction + spellCardChoices.size()) % spellCardChoices.size());
        }
    }

    public void confirmSelection() {
        if (state == 0) {
            state = 1;
        } else if (state == 1) {
            state = 2;
        }
    }

    private void setCurrentChoice(int choice) {
        if (state == 0) {
            textEffectCardChoice = choice;
        } else if (state == 1) {
            spellCardChoice = choice;
        }
    }

    /**
     * Get the current choice. Returns last choice if not able to change choice
     */
    private int getCurrentChoice() {
        if (state == 0) {
            return textEffectCardChoice;
        } else if (state == 1) {
            return spellCardChoice;
        }

        return spellCardChoice;
    }

    public boolean isUnconfirmed() {
        return state == 0;
    }

    public boolean textEffectCardSelected() {
        return state >= 1;
    }

    public boolean spellCardSelected() {
        return state >= 2;
    }

    public int getSpellCardChoice() {
        return spellCardChoice;
    }

    public int getTextEffectCardChoice() {
        return textEffectCardChoice;
    }

    /**
     * Origin should be top left of whatever player's owner screen is
     */
    @Override
    public void render(Graphics2D g) {
        var t = g.getComposite();
        g.setComposite(alphaComposite);
        int targetWidth = StdDrawBridge.width / 2 - 40;
        int targetHeight = StdDrawBridge.height - 400;

        g.setColor(Color.BLUE);
        g.fillRect(20, 200, targetWidth, targetHeight);
        g.setColor(Color.BLACK);
        g.drawString("Choose a text effect card", 30, 220);
        g.drawString("Text effect " + textEffectCardChoice, 30, 240);
        g.drawString("Spell effect " + spellCardChoice, 30, 260);
        g.drawString("State " + state, 30, 280);

        g.setComposite(t);
    }

    public ITextEffectCard getTextEffectCard() {
        return textEffectCardChoices.get(textEffectCardChoice);
    }

    public ISpellCard getSpellCard() {
        return spellCardChoices.get(spellCardChoice);
    }
}
