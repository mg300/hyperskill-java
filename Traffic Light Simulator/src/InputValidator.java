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
                System.out.println(inputMessage);
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if(choice>=minValue && choice<=maxValue){
                        return choice;
                    }
                    else{
                        System.out.println(errorMessage);
                    }
                }
                else{
                    scanner.next();
                    System.out.println(errorMessage);
                }
            }
    }
    public void subscribeEnterButton(Runnable function){
        Thread thread = new Thread(() -> {
            while(true){
                if (scanner.nextLine().isEmpty()) {
                    function.run();
                    break;
                }
            }
        });
        thread.start();
    }
}
