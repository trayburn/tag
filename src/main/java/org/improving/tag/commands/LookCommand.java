package org.improving.tag.commands;

public class LookCommand {
    public boolean isValid(String input) {
        return input.equals("look");
    }
    public void execute(String input) {
        System.out.println("You look around.");
    }
}
