import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class InputValidator implements Validator {

    private ArrayList inputList;
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
    private void reset() {
        inputList.clear();
        inputMessage = null;
        errorMessage = null;
        minValue = Integer.MIN_VALUE;
        maxValue = Integer.MAX_VALUE;
    }
    public <T> void setPossibleInputs(T[] elements) {
        this.inputList = new ArrayList<T>();
        inputList.addAll(Arrays.asList(elements));
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
    public int getInt(){
        this.clearOutput();
        int choice;
        while (true) {
            output.accept(inputMessage);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if(choice>=minValue && choice<=maxValue){
                    this.reset();
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
        this.clearOutput();
        String input;
        while(true){
            output.accept(inputMessage);
            input = scanner.nextLine();
            if(!containsOnlyLetters(input)){
                output.accept("Wrong format. Use letters only!");
                continue;
            }
            if(inputList.isEmpty() || inputList.contains(input)){
                this.reset();
                return input;
            }
            output.accept(errorMessage);
        }
    }
    public String[] getStrings(){
        this.clearOutput();
        while(true){
            output.accept(inputMessage);
            String input = scanner.nextLine();
            if(!containsOnlyLetters(input)){
                output.accept("Wrong format. Use letters only!");
                continue;
            }
            if(inputList.isEmpty() || inputList.contains(input)){
                this.reset();
                return input.split(", ");
            }
            output.accept(errorMessage);

        }
    }
    public void displayMeal(Meal meal){
        output.accept("Category: "+meal.getCategory());
        output.accept("Name: "+meal.getName());
        for(String ingredient : meal.getIngredients()){
        output.accept(ingredient);
        }
        scanner.nextLine();
    }
    private boolean containsOnlyLetters(String str) {
        return str.matches("[a-zA-Z,]+");
    }

}
