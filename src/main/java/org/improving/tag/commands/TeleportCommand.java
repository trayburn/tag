package org.improving.tag.commands;

import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class TeleportCommand extends BaseEmoteCommand {
    public TeleportCommand(InputOutput io) {
        super("You phase out of existance.", io,
                "teleport", "t", "te", "tel", "tele", "telep", "telepo", "telepor");
    }
}
