package org.improving.tag.commands;

import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class JumpCommand extends BaseEmoteCommand {
    public JumpCommand(InputOutput io) {
        super("You jump in the air.", io,
                "jump", "j", "ju", "jum");
    }
}
