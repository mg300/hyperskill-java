import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the traffic management system!");
        System.out.println("Menu");
        System.out.println("1. Add");
        System.out.println("2. Delete");
        System.out.println("3. System");
        System.out.println("0. Quit");
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                int choice;
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();}
                else{
                    scanner.next();
                    continue;
                }
                switch (choice) {
                    case 1:
                        System.out.println("1. Add");
                        break;
                    case 2:
                        System.out.println("2. Delete");
                        break;
                    case 3:
                        System.out.println("3. System");
                        break;
                    case 0:
                        System.out.println("0. Quit");
                        return; // You can use return to exit the loop and the method
                    default:
                        System.out.println("Wrong number");
                }
            }

        } catch (InputMismatchException e) {
            scanner.next(); // Consuming invalid input
            System.out.println("Error, wrong input");
        }
    }
}
