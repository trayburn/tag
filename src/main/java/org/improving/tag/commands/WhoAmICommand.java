package org.improving.tag.commands;

import org.improving.tag.Game;
import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class WhoAmICommand implements Command {
    private final InputOutput io;

    public WhoAmICommand(InputOutput io) {
        this.io = io;
    }

    @Override
    public boolean isValid(String input, Game game) {
        return input.trim().equalsIgnoreCase("whoami");
    }

    @Override
    public void execute(String input, Game game) {
        io.displayText("My name is " + game.getPlayer().getName());
    }
}
