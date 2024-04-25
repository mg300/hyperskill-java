import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> output = System.out::println;
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator(scanner,output);
        inputValidator.setInputMessage("Which meal do you want to add (breakfast, lunch, dinner)?");
        String mealCategory = inputValidator.getString();
        inputValidator.setInputMessage("Input the meal's name:");
        String mealName = inputValidator.getString();
        inputValidator.setInputMessage("Input the ingredients:");
        String[] ingredients = inputValidator.getStrings();

        output.accept("Category: "+mealCategory);
        output.accept("Name: "+mealName);
        output.accept("Ingredients: ");
        for(String ingredient : ingredients){
            output.accept(ingredient);
        }
    }
}