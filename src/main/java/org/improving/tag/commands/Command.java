package org.improving.tag.commands;

import org.improving.tag.Game;

public interface Command {
    boolean isValid(String input, Game game);
    void execute(String input, Game game);
}
