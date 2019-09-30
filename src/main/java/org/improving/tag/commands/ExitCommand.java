package org.improving.tag.commands;

import org.improving.tag.Game;
import org.improving.tag.GameExitException;
import org.improving.tag.InputOutput;
import org.improving.tag.SaveGameFactory;
import org.springframework.stereotype.Component;

@Component
public class ExitCommand extends BaseAliasedCommand {
    private final SaveGameFactory sgf;

    public ExitCommand(SaveGameFactory sgf, InputOutput io) {
        super(io, "exit");
        this.sgf = sgf;
    }

    @Override
    public void childExecute(String input, Game game) {
        sgf.save(game);
        io.displayText("Goodbye");
        throw new GameExitException();
    }
}
