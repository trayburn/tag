package org.improving.tag.commands;

import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class InventoryCommand extends BaseEmoteCommand {
    public InventoryCommand(InputOutput io) {
        super("inventory", "You're carrying nothing.", io);
    }
}
