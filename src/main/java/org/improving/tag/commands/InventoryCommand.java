package org.improving.tag.commands;

import org.improving.tag.Game;
import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class InventoryCommand extends BaseAliasedCommand {
    public InventoryCommand(InputOutput io) {
        super(io, "inventory", "i", "in", "inv", "inve", "inven", "invent", "invento", "inventor");
    }

    @Override
    public void childExecute(String input, Game game) {
        if (game.getPlayer().getInventory().isEmpty()) {
            io.displayText("You're carrying nothing.");
        } else {
            io.displayText(game.getPlayer().getInventory().getInventoryDisplay());
        }
    }
}
