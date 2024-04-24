package io.github.utsav_bhandari.UI;

import io.github.utsav_bhandari.Game;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.util.ArrayList;

public class DebugOverlay implements IRenderable {

    private static final Font font = new Font("Consolas", Font.PLAIN, 20);
    private static final Color bgColor = new Color(0, 0, 0, 0.5f);
    private final Game game;

    public DebugOverlay(Game game) {
        this.game = game;
    }

    @Override
    public void render(Graphics2D g) {
        var items = new ArrayList<String>();

        // line height
        int lh = 20;
        // char width
        int cw = 11;
        // line offset
        int lo = 17;
        // shadow offset
        int so = 1;

        items.add("[DEBUG]");

        items.add("World locked? " + (game.getWorldThread().getState() == Thread.State.WAITING ? "Yes" : "No"));
        items.add("World debug: " + game.world.debugStatus);

        g.setFont(font);
        for (int i = 0; i < items.size(); i++) {
            var item = items.get(i);
            g.setColor(bgColor);
            g.fillRect(0, 200 + i * lh, cw * item.length() + 5, lh);
            g.setColor(Color.BLACK);
            g.drawString(item, so + 2, 200 + lo + so + i * lh);
            g.setColor(Color.WHITE);
            g.drawString(item, 2, 200 + lo + i * lh);
        }
    }
}
