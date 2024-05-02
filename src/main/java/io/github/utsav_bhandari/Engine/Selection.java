package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ISpellCard;
import io.github.utsav_bhandari.Engine.TextEffectCard.ITextEffectCard;
import io.github.utsav_bhandari.Lib.StdDrawBridge;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.util.ArrayList;
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
    private final Color selctionBoxColor = new Color(0f, 0f, 0f, 0.5f);

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
        if (state == 0) {
            if (textEffectCardChoices.isEmpty()) return;
            setCurrentChoice((getCurrentChoice() + direction + textEffectCardChoices.size()) % textEffectCardChoices.size());
        } else if (state == 1) {
            if (spellCardChoices.isEmpty()) return;
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
        int targetWidth = StdDrawBridge.width / 2 - 40;
        int padding = 100;
        int innerWidth = targetWidth - 2 * padding;
        int targetHeight = StdDrawBridge.height - 200;
        int selectionBoxX = 20;
        int selectionBoxY = 160;

        g.drawImage(Resource.getInstance().selection, selectionBoxX, selectionBoxY, null);
        g.setColor(Color.BLACK);
        g.drawString("Choose a text effect card", selectionBoxX + 10, selectionBoxY + 20);
        g.drawString("Text effect " + textEffectCardChoice, selectionBoxX + 10, selectionBoxY + 40);
        g.drawString("Spell effect " + spellCardChoice, selectionBoxX + 10, selectionBoxY + 60);
        g.drawString("State " + state, selectionBoxX + 10, selectionBoxY + 80);

        var r = Resource.getInstance();
        int teGap = (innerWidth - textEffectCardChoices.size() * 192) / (textEffectCardChoices.size() - 1);
        for (int i = 0; i < textEffectCardChoices.size(); i++) {
            ITextEffectCard te = textEffectCardChoices.get(i);
            int teCardX = selectionBoxX + padding + (192 + teGap) * i;
            g.drawImage(te.getThumbnail(),
                    teCardX,
                    selectionBoxY + padding,
                    192,
                    192,
                    null);
            g.drawImage(r.borders.get(i),
                    teCardX - 10,
                    selectionBoxY + padding - 10,
                    212,
                    212,
                    null);
            if (i != textEffectCardChoice) {
                g.setColor(selctionBoxColor);
                g.fillRect(teCardX,selectionBoxY + padding, 192, 192);
             }
        }
        for (int i = 0; i < spellCardChoices.size(); i++) {
            ISpellCard sc = spellCardChoices.get(i);
            int off =  (targetWidth - (192 * spellCardChoices.size())) / (spellCardChoices.size() + 1);
            int spellCardX = selectionBoxX + (i + 1) * off + i * 192;
            g.drawImage(sc.getThumbnail(),
                    spellCardX,
                    (selectionBoxY + (targetHeight / 2) + off / 3),
                    192,
                    192,
                    null);
            g.drawImage(r.borders.get(i + 3),
                    spellCardX - 10,
                    ((selectionBoxY + (targetHeight / 2) + off / 3) - 10),
                    212,
                    212,
                    null);
            if (i != spellCardChoice) {
                g.setColor(selctionBoxColor);
                g.fillRect(spellCardX, (selectionBoxY + (targetHeight / 2) + off / 3), 192, 192);
            }
        }

//        g.setComposite(t);
    }
    public ArrayList<ISpellCard> getRemainingSpellCards() {
        var n = new ArrayList<>(spellCardChoices);
        if (spellCardChoices.isEmpty()) {
            return n;
        }
        n.remove(spellCardChoice);

        return n;
    }
    public ArrayList<ITextEffectCard> getRemainingTextEffectCards() {
        var n = new ArrayList<>(textEffectCardChoices);
        if (textEffectCardChoices.isEmpty()) {
            return n;
        }
        n.remove(textEffectCardChoice);

        return n;
    }

    public ITextEffectCard getTextEffectCard() {
        if (textEffectCardChoices.isEmpty()) {
            return null;
        }
        return textEffectCardChoices.get(textEffectCardChoice);
    }

    public ISpellCard getSpellCard() {
        if (spellCardChoices.isEmpty()) {
            return null;
        }
        return spellCardChoices.get(spellCardChoice);
    }
}
