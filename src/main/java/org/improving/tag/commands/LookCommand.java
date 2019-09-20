package org.improving.tag.commands;

import org.springframework.stereotype.Component;

@Component
public class LookCommand extends BaseEmoteCommand {
    public LookCommand() {
        super("look", "You look around.");
    }
}
