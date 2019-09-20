package org.improving.tag.commands;

import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class TeleportCommand extends BaseEmoteCommand {
    public TeleportCommand(InputOutput io) {
        super("teleport", "You phase out of existance.", io);
    }
}
