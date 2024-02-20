import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int pencilsQuantity = scanner.nextInt();
        System.out.println("Who will be the first (John, Jack):");
        scanner.nextLine();
        String winner = scanner.nextLine();
        System.out.println("|".repeat(pencilsQuantity));
        System.out.println(winner+" is going first!");
    }
}
