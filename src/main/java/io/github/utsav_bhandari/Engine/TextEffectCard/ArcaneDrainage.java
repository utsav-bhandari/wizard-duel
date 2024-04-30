package io.github.utsav_bhandari.Engine.TextEffectCard;

import io.github.utsav_bhandari.Engine.*;
import io.github.utsav_bhandari.Render.IRenderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ArcaneDrainage extends ATextEffectCard implements IRenderable, ITextEffectCard {
    {
        thumbnail = Resource.getInstance().cardThumbnails.get("ArcaneDrainage");
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(thumbnail, null, (int) this.x, (int) this.y);
    }

    @Override
    public String getName() {
        return "Arcane Drainage";
    }

    @Override
    public String getDescription() {
        return "Voids 2 charges from the enemy on their turn.\n" +
                "If the enemy's charge becomes 0 as a result,\n" +
                "gain a 20% damage buff for your next attack."; // 20% for now
    }

    private int state = 0;

    @Override
    public boolean hook(TurnEventHook event) {
        return false;
    }
//        if (event.attackEvent == null) {
//            return false;
//        }
//        if (state == 0) {
//            // enemy debuff
//            if (event.turn.attacker == owner) return false;
////            Player opponent = event.turn.attacker == owner ? event.turn.defender : event.turn.attacker;
//            if (event.world.getWorldState() != World.WORLD_STATE_CHARGE_ADDED) return false;
//
//            int newCharge = event.turn.attacker.getCharge() - 2;
//
//            if (newCharge <= 0) {
//                newCharge = 0;
//                state = 1;
//            }
//
//            event.turn.attacker.setCharge(newCharge);
//
//            return state == 0;
//        }
//        if (state == 1) {
//            // attacker's buff
//            if (event.turn.attacker != owner) return false;
//            if (event.world.getWorldState() != World.WORLD_STATE_SPELL_PRIMED) return false;
//
//            event.attackEvent.spell.setDamage(
//                event.attackEvent.spell.getDamage() * 1.2f
//            );
//
//            return true;
//        }
//
//        throw new RuntimeException("Cannot reach this state");
//    }
}
