package io.github.utsav_bhandari.Scripts;

import io.github.utsav_bhandari.UI.HelpDisplay;

public class HelpScreenRenderDemo {
    public static void main(String[] args) {
        var player1HelpScreen = new HelpDisplay(0);
        var player2HelpScreen = new HelpDisplay(1);
        StdDrawProvider.run(graphics2D -> {
            player1HelpScreen.render(graphics2D);
            player2HelpScreen.render(graphics2D);
        });
    }
}
