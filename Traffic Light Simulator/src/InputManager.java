import java.util.Scanner;
import java.util.function.Consumer;

public class InputReader {
    final Scanner scanner;

    final Consumer<String> output;
    final InputValidator validator;

    public InputReader(Scanner scanner, Consumer<String> output) {
        this.scanner = scanner;
        this.output = output;
        validator = new InputValidator(scanner,output);

    }
    public int readRoads(){
        output.accept("Welcome to the traffic management system!");
        validator.setErrorMessage("Error! Incorrect Input. Try again: ");
        validator.setInputMessage("Input the number of roads: ");
        validator.setMinValue(1);
        validator.setMaxValue(10);
        return validator.getInput();
    }
    public int readIntervals(){
        validator.setInputMessage("Input the interval: ");
        validator.setMaxValue(1000);
        return validator.getInput();
    }
    public int displayReadMenu(){
        validator.clearOutput();
        validator.setErrorMessage("Incorrect option");
        validator.setInputMessage("Menu:\n"+
                "1. Add road\n" +
                "2. Delete road\n" +
                "3. Open system\n" +
                "0. Quit");
        validator.setMinValue(0);
        validator.setMaxValue(4);
        return validator.getInput();
    }
}
