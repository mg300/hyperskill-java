package org.example;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Consumer;

public class CompanyMenu extends ManagerMenu {
    private final String companyName;

    public CompanyMenu(Scanner scanner, Consumer<String> output, DBObj db, String companyName) throws SQLException {
        super(scanner, output, db);
        this.companyName = companyName;
    }

    @Override
    public void openMenu() throws SQLException {
        while (true) {
            output.accept(companyName + " company:\n" +
                    "1. Car list\n" +
                    "2. Create a car\n" +
                    "0. Back");
            switch (scanner.nextInt()) {
                case 1:
                    showCarList();
                    break;
                case 2:
                    addCar();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void showCarList() throws SQLException {
        String[] cars = db.getCarsByCompany(companyName);
        int i = 0;
        output.accept("");
        if (cars.length == 0) {
            output.accept("The cars list is empty");
        }
        for (String company : cars) {
            i++;
            System.out.println(i + ". " + company);
        }
        output.accept("0. Back");
        output.accept("");
        scanner.nextInt();

    }

    private void addCar() throws SQLException {
        output.accept("Enter the car name");
        scanner.nextLine();
        String name = scanner.nextLine();
        boolean res = db.addCar(companyName, name);
        if (res) {
            output.accept("The car was created!");
        } else {
            output.accept("The car was on the list!");
        }
    }
}
