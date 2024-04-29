import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class MealPlanner {
    private final Consumer<String> output;
    private final Scanner scanner ;
    private InputOutputManager manager;
    private List<Meal> meals;

    private DBconnection connection;

    public MealPlanner(Scanner scanner, Consumer<String> output,DBconnection connection) {
        this.scanner = scanner;
        this.output = output;
        meals = new ArrayList<>();
        this.connection = connection;
        start();
    }

    private void start(){
        manager = new InputOutputManager(new InputValidator(scanner,output));
        while(true){
            String option = manager.showMenuGetOption();
            switch (option){
                case "add":{
                    String category = manager.getMealType();
                    String name = manager.getMealName();
                    String[] ingredients = manager.getIngredients();
                    Meal meal = new Meal(name,category,ingredients);
                    meals.add(meal);
                    connection.saveMeal(meal);
                    break;
                }
                case "show":{
//                    manager.showMeals(meals);
                    output.accept(connection.getFormattedMeals());
                    break;
                }
                case "exit":{
                    return;
                }
            }
        }

    }
}
