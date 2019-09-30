package org.improving.tag.commands;

import org.improving.tag.Game;
import org.improving.tag.InputOutput;

public abstract class BaseEmoteCommand extends BaseAliasedCommand {
    private String cmdResponse;
    private InputOutput io;

    public BaseEmoteCommand(String cmdResponse, InputOutput io, String...aliases) {
        super(aliases);
        this.cmdResponse = cmdResponse;
        this.io = io;
    }

    @Override
    public void execute(String input, Game game) {
        io.displayText(cmdResponse);
    }
}
