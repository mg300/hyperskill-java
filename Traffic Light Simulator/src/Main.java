import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Config config = Config.getInstance();
        Consumer<String> output = System.out::println;
        Scanner scanner = new Scanner(System.in);
        InputValidator validator = new InputValidator(scanner,output);
        InputReader reader = new InputReader(scanner,output);

        config.setRoads(reader.readRoads());
        config.setIntervals(reader.readIntervals());
        while (true){
            int option = reader.displayReadMenu();
            validator.clearOutput();
            switch (option){
                case 1:
                    System.out.println("Road added");
                    break;
                case 2:
                    System.out.println("Road deleted");
                    break;
                case 3:
                    System.out.println("System opened");
                    break;
                case 0:
                    System.out.println("Bye");
                    return;

            }
    }

}}
