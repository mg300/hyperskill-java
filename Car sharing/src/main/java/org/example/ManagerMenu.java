package org.example;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Consumer;

public class ManagerMenu extends Menu {


    public ManagerMenu(Scanner scanner, Consumer<String> output, DBObj db) throws SQLException {
        super(scanner, output, db);
    }

    @Override
    public void openMenu() throws SQLException {
        while (true) {
            output.accept("1. Company list\n" +
                    "2. Create a company\n" +
                    "0. Back");
            switch (scanner.nextInt()) {
                case 1:
                    showList();
                    break;
                case 2:
                    createCompany();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void createCompany() throws SQLException {
        output.accept("Enter the company name");
        scanner.nextLine();
        String name = scanner.nextLine();
        boolean res = db.addCompany(name);
        if (res) {
            output.accept("The company was created!");
        } else {
            output.accept("The company was on the list!");
        }
    }

    private void showList() throws SQLException {
        while (true) {
            output.accept("Choose a company:");
            String[] companies = db.getCompanies();
            int i = 0;
            output.accept("");
            if (companies.length == 0) {
                output.accept("The company list is empty");
            }
            for (String company : companies) {
                i++;
                System.out.println(i + ". " + company);
            }
            output.accept("0. Back");
            output.accept("");
            int companyNumber = scanner.nextInt();
            if (companyNumber > i || companyNumber < 0) {
                output.accept("Wrong company number");
            } else if (companyNumber == 0) {
                return;
            } else {
                output.accept("");
                MenuFactory.createCompanyMenu(companies[companyNumber - 1]);
            }
        }
    }
}
