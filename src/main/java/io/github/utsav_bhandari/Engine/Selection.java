package io.github.utsav_bhandari.Engine;

import java.util.List;

/**
 * Class representing the cards that player has to select
 * <br>
 * There will be some text effect cards and player chooses one
 * <br>
 * and also some spell cards and player chooses one
 */
public class Selection {
    private final List<ITextEffectCard> textEffectCardChoices;
    private final List<ISpellCard> spellCardChoices;

    private int spellCardChoice = 0;
    private int textEffectCardChoice = 0;

    private int currentChoice = 0;

    private int state = 0;

    public Selection(List<ISpellCard> spellCardChoices, List<ITextEffectCard> textEffectCardChoices) {
        this.spellCardChoices = spellCardChoices;
        this.textEffectCardChoices = textEffectCardChoices;
    }

    /**
     * 0 right 180 left
     */
    public void moveSelection(int direction) {
        if (state == 0) {
            currentChoice = (currentChoice + direction + textEffectCardChoices.size()) % textEffectCardChoices.size();
        } else if (state == 1) {
            currentChoice = (currentChoice + direction + spellCardChoices.size()) % spellCardChoices.size();
        } else {
            throw new IllegalStateException("Selection already confirmed");
        }
    }

    public void confirmSelection() {
        if (state == 0) {
            textEffectCardChoice = currentChoice;
            state = 1;
        } else if (state == 1) {
            spellCardChoice = currentChoice;
            state = 2;
        } else {
            throw new IllegalStateException("Selection already confirmed");
        }
        currentChoice = 0;
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
}
