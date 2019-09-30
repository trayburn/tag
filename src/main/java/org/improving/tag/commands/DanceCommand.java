package org.improving.tag.commands;

import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class DanceCommand extends BaseEmoteCommand{
    public DanceCommand(InputOutput io) {
        super("You dance around in a circle.", io,
                "dance", "d","da","dan","danc");
    }
}
