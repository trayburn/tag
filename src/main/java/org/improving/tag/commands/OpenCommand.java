package org.improving.tag.commands;

import org.improving.tag.Game;
import org.improving.tag.InputOutput;
import org.improving.tag.items.Item;
import org.improving.tag.items.UniqueItems;
import org.springframework.stereotype.Component;

@Component
public class OpenCommand extends BaseAliasedCommand {

    public OpenCommand(InputOutput io) {
        super(io, "open", "loot chest", "o", "lc");
    }

    @Override
    public void childExecute(String input, Game game) {
        Item item = game.getPlayer().getLocation().openTreasureChest();
        io.displayText("You found: " + item);
        game.getPlayer().getInventory().addItem(item);
    }

    @Override
    public String getErrorMessage() {
        return "Are you seeing things? There is no treasure chest to open here";
    }
}
