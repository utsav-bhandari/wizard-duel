package io.github.utsav_bhandari.Scripts;

import java.awt.*;

import static io.github.utsav_bhandari.Lib.StdDrawBridge.screen;

public class ShapeRenderQualityDemo {
    public static void main(String[] args) {
        StdDrawProvider.run(g -> {
            screen.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            screen.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.setColor(Color.BLACK);
            g.drawOval(500, 500, 500, 500);
        });
    }
}
