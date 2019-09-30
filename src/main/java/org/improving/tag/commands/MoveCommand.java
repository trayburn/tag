package org.improving.tag.commands;

import org.improving.tag.Exit;
import org.improving.tag.Game;
import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class MoveCommand extends BaseAliasedCommand {
    private InputOutput io;

    public MoveCommand(InputOutput io) {
        super(io, "move", "m", "mo", "mov");
        this.io = io;
    }

    @Override
    public String getCommandPart(String input) {
        var parts = input.split(" ");
        //if (parts.length == 1) return false;
        return parts[0];
    }

    @Override
    public String getErrorMessage() {
        return "That route is unavailable.";
    }

    @Override
    public void childExecute(String input, Game game) {
        input = input.trim();
        var destination = input.substring(input.indexOf(" ") + 1);

        Exit exit = null;
        for (var e : game.getPlayer().getLocation().getExits()) {
            if (e.getName().equalsIgnoreCase(destination)) {
                exit = e;
            } else {
                for (var a : e.getAliases()) {
                    if (a.equalsIgnoreCase(destination)) {
                        exit = e;
                        break;
                    }
                }
            }
            if (exit != null) break;
        }
        if (exit == null) throw new UnsupportedOperationException();

        game.getPlayer().setLocation(exit.getDestination());
        io.displayText("You travel " + exit.getName() + ".");
    }
}
