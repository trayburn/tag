package org.improving.tag;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInputOutput implements InputOutput {
    private final Scanner scanner;

    public ConsoleInputOutput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String receiveInput() {
        return scanner.nextLine();
    }

    @Override
    public void displayText(String text) {
        System.out.println(text);
    }

    @Override
    public void displayPrompt(String prompt) {
        System.out.print(prompt);
    }

}
