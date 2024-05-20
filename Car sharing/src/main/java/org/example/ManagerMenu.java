package org.example;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Consumer;

public class ManagerMenu extends Menu{


    public ManagerMenu(Scanner scanner, Consumer<String> output, DBObj db) throws SQLException {
        super(scanner, output, db);
    }

    @Override
    public void openMenu() throws SQLException {
        while (true){
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
            }}
    }

    private void createCompany() throws SQLException {
     output.accept("Enter the company name");
     scanner.nextLine();
     String name = scanner.nextLine();
     boolean res = db.addCompany(name);
     if(res){
     output.accept("The company was created!");
     }
     else{
     output.accept("The company was on the list!");
     }
    }
    private void showList() throws SQLException {
        String[] companies = db.getCompanies();
        int i = 1;
        output.accept("");
        for(String company : companies){
            System.out.println(i+". "+company);
            i++;
        }
        output.accept("");


    }
}
