package org.improving.tag.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.improving.tag.Game;

public abstract class BaseAliasedCommand implements Command {
    private List<String> aliases = new ArrayList<>();

    public BaseAliasedCommand(String...aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
    }

    @Override
    public boolean isValid(String input, Game game) {
        var trimmedInput = input.trim();
        return aliases.stream()
                .anyMatch(trimmedInput::equalsIgnoreCase);
    }
}
