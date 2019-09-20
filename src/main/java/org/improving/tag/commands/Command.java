package org.improving.tag.commands;

public interface Command {
    boolean isValid(String input);
    void execute(String input);
}
