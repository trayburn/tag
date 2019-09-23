package org.improving.tag.commands;

import org.improving.tag.InputOutput;
import org.springframework.stereotype.Component;

@Component
public class DemoCommand implements Command {
    private final InputOutput io;
    private int ingCount = 0;

    public DemoCommand(InputOutput io) {
        this.io = io;
    }

    @Override
    public boolean isValid(String input) {
        return input.trim().endsWith("ing");
    }

    @Override
    public void execute(String input) {
        ingCount = ingCount + 1;
        io.displayText("We have 'ing'ed " + ingCount + " times.");
    }
}
