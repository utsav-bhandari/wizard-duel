package io.github.utsav_bhandari.Scripts;

import io.github.utsav_bhandari.Engine.KeyboardInputHandler;
import io.github.utsav_bhandari.Engine.SpellCard.ThePowerOfExample;
import io.github.utsav_bhandari.Render.AnimatedSpriteRoot;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

public class SpellCardDemo {
    public static void main(String[] args) {
        BufferedImage image;

        try (var stream = AnimatedSpriteDemo.class.getResourceAsStream("/sprites/pixel-spell-effect/spells-0.png")) {
            image = ImageIO.read(stream);

            AnimatedSpriteRoot.registerAnimatedSprite(
                    "WHIRLWIND",
                    image,
                    250,
                    250,
                    0,
                    0,
                    250,
                    0,
                    8,
                    60,
                    10,
                    -1,
                    true
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var spell = new ThePowerOfExample();

        spell.x = 0;
        spell.y = 0;

        var kh = new KeyboardInputHandler();

        var keys = new ArrayList<Function<KeyEvent, Boolean>>();

        keys.add(e -> {
            // If space is pressed, cast the spell
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (spell.isNeutral()) {
                    spell.prime();
                    return true;
                }

                spell.cast();
                spell.vx = 10;

                return true;
            }

            if (spell.isPrimed()) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    spell.setDamage(spell.getDamage() + 3);
                    System.out.println("Damage: " + spell.getDamage());
                    return true;
                }
            }

            return false;
        });

        kh.addKeymap("MAIN", keys);

        kh.useKeymap("MAIN");


        StdDrawProvider.run(g -> {
            spell.updatePosition();

            if (spell.x > 1920) {
                spell.x = 0;
            }

            spell.render(g);
        });
        StdDrawProvider.frame.addKeyListener(kh);
    }
}
