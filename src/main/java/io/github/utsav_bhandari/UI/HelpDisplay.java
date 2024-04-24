package io.github.utsav_bhandari.UI;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import io.github.utsav_bhandari.Lib.StdDrawBridge;
import io.github.utsav_bhandari.Render.IRenderable;
import io.github.utsav_bhandari.Scripts.StdDrawProvider;

import javax.imageio.event.IIOReadProgressListener;
import java.awt.*;

import static io.github.utsav_bhandari.Lib.StdDrawBridge.frame;
import static io.github.utsav_bhandari.Lib.StdDrawBridge.screen;

public final class HelpDisplay implements IRenderable {
    private int side;
    private final Color helpBackgroundColorRight;
    private final Color helpBackgroundColorLeft;
    /* side of 0 represents the left and side of 1 represents the right side of the screen */
    public HelpDisplay(int side) {
        this.side = side;
        this.helpBackgroundColorRight = new Color(1f, 0f, 0f, 0.2f);
        this.helpBackgroundColorLeft = new Color(0f, 0f, 1f, 0.2f);
    }

    @Override
    public void render(Graphics2D g) {
//      screen.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//      screen.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // looks like this is good enough

        int screenWidth = StdDrawBridge.width;
        int screenHeight = StdDrawBridge.height;
        int helpScreenWidth = screenWidth/2;

        int xPosition;
        if (side == 0) { // Left side
            xPosition = 0;
            g.setColor(helpBackgroundColorLeft);
        } else { // Right side
            xPosition = screenWidth - helpScreenWidth;
            g.setColor(helpBackgroundColorRight);
        }
        g.fillRect(xPosition + 10, 10, helpScreenWidth - 20 , screenHeight - 20);
        g.setColor(new Color(75, 0, 130));
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("WELCOME TO THE HELP SECTION", xPosition + 15, 25);
    }
}
