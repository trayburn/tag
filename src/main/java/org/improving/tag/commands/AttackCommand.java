package org.improving.tag.commands;

import org.improving.tag.Adversary;
import org.improving.tag.Game;
import org.improving.tag.InputOutput;
import org.improving.tag.Player;
import org.improving.tag.items.Item;
import org.improving.tag.items.UniqueItems;
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
            int advRemainingHP = adv.getHitPoints() - adv.getDamageTaken();
            if (0 >= advRemainingHP) {
                defeatAdversary(game.getPlayer(), adv);
            } else {
                io.displayText("You hit " + adv.getName() + "! " + advRemainingHP + " remaining of " + adv.getHitPoints() + ".");
            }
        } else {
            io.displayText("You miss " + adv.getName() + ".");
        }
    }

    private void defeatAdversary(Player player, Adversary adv) {
        io.displayText("Congrats! You defeated: " + adv.getName());
        player.getLocation().setAdversary(null);
        lootDefeatedAdversary(adv, player);
    }

    private void lootDefeatedAdversary(Adversary adv, Player player) {
        Item droppedItem = adv.getItem();
        if (!UniqueItems.NOTHING.equals(droppedItem)) {
            io.displayText("And you found: " + droppedItem);
            player.getInventory().addItem(droppedItem);
        }
    }
}
