package org.improving.tag.commands;

import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class JumpCommand extends BaseEmoteCommand {
    public JumpCommand(InputOutput io) {
        super("jump", "You jump in the air.", io);
    }
}
