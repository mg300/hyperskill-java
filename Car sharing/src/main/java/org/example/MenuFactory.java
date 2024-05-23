package org.example;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Consumer;

public class MenuFactory {
    private static Scanner scanner;
    private static Consumer<String> output;
    private static DBObj db;

    public MenuFactory(Scanner scanner, Consumer<String> output, DBObj db) throws SQLException {
        MenuFactory.scanner = scanner;
        MenuFactory.output = output;
        MenuFactory.db = db;
        createMenu();
    }

    public static void createMenu() throws SQLException {
        new Menu(scanner, output, db).openMenu();
    }

    public static void createManagerMenu() throws SQLException {
        new ManagerMenu(scanner, output, db).openMenu();
    }

    public static void createCompanyMenu(String companyName) throws SQLException {
        new CompanyMenu(scanner, output, db, companyName).openMenu();
    }
}
