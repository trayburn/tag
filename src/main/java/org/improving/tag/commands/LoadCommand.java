package org.improving.tag.commands;

import org.improving.tag.Game;
import org.improving.tag.SaveGameFactory;
import org.springframework.stereotype.Component;

@Component
public class LoadCommand implements Command {
    private SaveGameFactory saveGameFactory;

    public LoadCommand(SaveGameFactory saveGameFactory) {
        this.saveGameFactory = saveGameFactory;
    }

    @Override
    public boolean isValid(final String input, final Game game) {
        return input.startsWith("load ");
    }

    @Override
    public void execute(final String input, final Game game) {
        String path = input.replace("load ", "");
        saveGameFactory.load(path, game);
    }
}
