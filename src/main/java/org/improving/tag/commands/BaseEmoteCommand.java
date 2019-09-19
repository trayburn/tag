package org.improving.tag.commands;

public class BaseEmoteCommand {
    private String cmdText;
    private String cmdResponse;

    public BaseEmoteCommand(String cmdText, String cmdResponse) {
        this.cmdText = cmdText;
        this.cmdResponse = cmdResponse;
    }

    public boolean isValid(String input) {
        return input.equals(cmdText);
    }
    public void execute(String input) {
        System.out.println(cmdResponse);
    }
}
