package io.github.utsav_bhandari.Scripts;

import io.github.utsav_bhandari.Engine.Resource;
import io.github.utsav_bhandari.Render.AnimatedSprite;

import java.util.ArrayList;

public class NewAnimatedSpriteDemo {
    public static void main(String[] args) {

        var r = Resource.getInstance();
        var spriteIDs = new ArrayList<String>();

        spriteIDs.add("Charge Cascade");
        spriteIDs.add("Nullifying Glyphs");
        spriteIDs.add("Arcane Flurry");
        spriteIDs.add("Virulent Eruption");
        spriteIDs.add("Ethereal Cyclone");
        spriteIDs.add("Infernal Circle");
        spriteIDs.add("Tempest Reversal");
        spriteIDs.add("Arcane Quota");

        var animatedSprites = new ArrayList<AnimatedSprite>();

        for (String spriteName : spriteIDs) {
            var sprite = r.getAnimatedSprite(spriteName);
            animatedSprites.add(sprite);
        }
        StdDrawProvider.run(g -> {
            int i = 0;
            for (var sprite : animatedSprites) {
                sprite.x = i * 100;
                sprite.y = 100;

                sprite.render(g);
                i++;
            }
        });
    }
}
