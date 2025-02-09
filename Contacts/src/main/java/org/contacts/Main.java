package org.contacts;

import org.contacts.IO.ConsoleManager;
import org.contacts.IO.IOManager;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Consumer;

public final class Main {

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Consumer<String> output = System.out::print;
        IOManager ioManager = new ConsoleManager(scanner, output);
        String argument = args.length > 0 ? args[0] : "";
        new ContactsApp(ioManager, argument).start();
    }
}
