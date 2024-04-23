package io.github.utsav_bhandari.Engine;

import io.github.utsav_bhandari.Engine.SpellCard.ThePowerOfExample;
import io.github.utsav_bhandari.Engine.TextEffectCard.ThePowerOfYapping;
import io.github.utsav_bhandari.Game;

import java.util.ArrayList;
import java.util.List;

public class World {
    public final Game game;

    /**
     * TODO comment
     */
    private final List<ISpellCard> spellCardPile = new ArrayList<>();
    /**
     * TODO comment
     */
    private final List<ITextEffectCard> textEffectCardPile = new ArrayList<>();

    public final Player[] players = new Player[2];

    public World(Game game) {
        this.game = game;

        players[0] = new Player(0);
        players[1] = new Player(1);


        for (int i = 0; i < 5; i++) {
            spellCardPile.add(new ThePowerOfExample());
            textEffectCardPile.add(new ThePowerOfYapping());
        }
    }

    public void update() {
    }
}
