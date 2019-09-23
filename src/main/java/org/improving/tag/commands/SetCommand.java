package org.improving.tag.commands;

import org.improving.tag.Game;
import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class SetCommand implements Command {
    private final InputOutput io;

    public SetCommand(InputOutput io) {
        this.io = io;
    }

    @Override
    public boolean isValid(String input, Game game) {
        if (input.contains("=") == false) return false;
        var cmd = input.split("=")[0].trim();
        return cmd.equalsIgnoreCase("@set name");
    }

    @Override
    public void execute(String input, Game game) {
        var name = input.split("=")[1].trim();
        game.getPlayer().setName(name);
        io.displayText("Your name is now " + game.getPlayer().getName() + ".");
    }
}
