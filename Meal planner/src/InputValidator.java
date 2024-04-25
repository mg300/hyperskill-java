import java.io.IOException;
import java.util.Scanner;
import java.util.function.Consumer;

public class InputValidator {
    Scanner scanner;
    Consumer<String> output;
    String errorMessage;
    String inputMessage;
    int minValue;
    int maxValue;

    public InputValidator(Scanner scanner, Consumer<String> output) {
        this.scanner = scanner;
        this.output = output;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
    public void clearOutput(){
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException e) {}
    }
    public int getInput(){
        int choice;
        while (true) {
            output.accept(inputMessage);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if(choice>=minValue && choice<=maxValue){
                    return choice;
                }
                else{
                    output.accept(errorMessage);
                }
            }
            else{
                scanner.next();
                output.accept(errorMessage);
            }
        }
    }
    public String getString(){
        String input;
        while(true){
            output.accept(inputMessage);
            input = scanner.nextLine();
            if(input!=""){
                return input;
            }
        }
    }
    public String[] getStrings(){
        while(true){
            output.accept(inputMessage);
            String inputString = scanner.nextLine();
            if(inputString!=""){
                return inputString.split(",");
            }
        }
    }

}
