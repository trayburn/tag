package org.improving.tag.commands;

import org.improving.tag.Game;
import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class LookCommand extends BaseAliasedCommand {
    private InputOutput io;

    public LookCommand(InputOutput io) {
        super(io, "l", "look", "whereami", "where am i", "?donde estoy?");
        this.io = io;
        //super("look", "You look around.",io);
    }

    @Override
    public void execute(String input, Game game) {
        var location = game.getPlayer().getLocation();
        io.displayText(location.getName());
        io.displayText(location.getDescription());
        io.displayText(location.getTreasureChest());
        io.displayNewLine();
        io.displayText("Exits:");

        for (var exit : location.getExits()) {
            io.displayText("  " + exit);
        }
    }
}
