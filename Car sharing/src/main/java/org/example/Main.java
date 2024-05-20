package org.example;


import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws Exception {
        H2DAO DB = new H2DAO();
        Consumer<String> output = System.out::println;
        Scanner scanner = new Scanner(System.in);
        new Menu(scanner, output, DB);
    }
}