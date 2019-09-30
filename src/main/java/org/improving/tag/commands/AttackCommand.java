package org.improving.tag.commands;

import org.improving.tag.Game;
import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AttackCommand extends BaseAliasedCommand {
    private final InputOutput io;
    private final Random r;

    public AttackCommand(InputOutput io, Random r) {
        super(io, "attack", "a", "at", "att", "atta", "attac","swing", "fight", "stab", "mock", "smack");
        this.io = io;
        this.r = r;
    }

    @Override
    public void execute(String input, Game game) {
        if (game.getPlayer().getLocation().getAdversary() == null) {
            io.displayText("Attack what?");
            return;
        }
        var adv = game.getPlayer().getLocation().getAdversary();
        int roll = r.nextInt(100) + 1;
        if (roll <= 20) {
            adv.setDamageTaken(adv.getDamageTaken() + 10);
            io.displayText("You hit " + adv.getName() + "! " + (adv.getHitPoints() - adv.getDamageTaken()) + " remaining of " + adv.getHitPoints() + ".");
        } else {
            io.displayText("You miss " + adv.getName() + ".");
        }
    }
}
