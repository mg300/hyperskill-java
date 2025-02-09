package org.contacts.IO;

import java.util.Scanner;
import java.util.function.Consumer;

public final class ConsoleManager implements IOManager {
    private final Consumer<String> output;
    private final Scanner scanner;

    public ConsoleManager(final Scanner scn, final Consumer<String> out) {
        scanner = scn;
        output = out;
    }

    @Override
    public void printText(final String text) {
        output.accept(text);
    }


    @Override
    public int readIntInput() {
        String input = scanner.nextLine();
        while (!input.matches("^[1-9]\\d*")) {
            System.out.print("Error! Incorrect input. Try again: ");
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);

    }


    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
