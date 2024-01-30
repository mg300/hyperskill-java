import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> output = System.out::println;
        Scanner scanner = new Scanner(System.in);
        new Machine(scanner, output);

    }
}