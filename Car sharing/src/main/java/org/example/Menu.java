package org.example;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Consumer;

public class Menu {
    final public Scanner scanner;
    final public Consumer<String> output;
    final public DBObj db;

    public Menu(Scanner scanner, Consumer<String> output, DBObj db) throws SQLException {
        this.scanner = scanner;
        this.output = output;
        this.db = db;
    }

    public void openMenu() throws SQLException {
        while (true) {
            output.accept("1. Log in as a manager\n" +
                    "0. Exit");
            switch (scanner.nextInt()) {
                case 1:
                    MenuFactory.createManagerMenu();
                    break;

                case 0:
                    output.accept("Bye");
                    return;
            }
        }
    }

}


