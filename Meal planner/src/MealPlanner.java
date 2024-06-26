import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class MealPlanner {
    private final Consumer<String> output;
    private final Scanner scanner ;
    private InputOutputManager manager;

    private DBconnection connection;

    public MealPlanner(Scanner scanner, Consumer<String> output,DBconnection connection) {
        this.scanner = scanner;
        this.output = output;
        this.connection = connection;
        this.start();
    }

    private void start() {
        manager = new InputOutputManager(new InputValidator(scanner,output));
        while(true){
            String option = manager.showMenuGetOption();
            switch (option){
                case "add":{
                    String category = manager.getMealType();
                    String name = manager.getMealName();
                    String[] ingredients = manager.getIngredients();
                    Meal meal = new Meal(name,category,ingredients);
                    connection.saveMeal(meal);
                    break;
                }
                case "plan":{
                    String[] days = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
                    String[] categories = new String[]{"breakfast","lunch","dinner"};
                    for (String day : days){
                        for (String category : categories){
                            String[] meals = connection.getMealArrByCategory(category);
                            String meal = manager.getMealForCategory(day, category ,meals);
                            connection.savePlanMeal(day, category, meal);

                        }
                    }
                    break;
                }
                case "show":{
                    String category = manager.getMealType();
                    output.accept(connection.getMealsByCategory(category));
                    break;
                }
                case "save":{
                    String[] ingredients = connection.getPlanIngredients();
                    Map<String,Integer> shoppingList = new LinkedHashMap<>();
                    for (String ing : ingredients){
                        String[] ingredientArr = ing.split(",");
                        for (String ingr : ingredientArr){
                            if(shoppingList.containsKey(ingr)){
                                shoppingList.put(ingr,shoppingList.get(ingr)+1);
                            }else{
                                shoppingList.put(ingr,1);
                            }
                        }
                    }
                    try {
                        File file = new File("./shoppingList.txt");
                        FileWriter writer = new FileWriter(file);
                        BufferedWriter bufferedWriter = new BufferedWriter(writer);
                        for (Map.Entry<String, Integer> entry : shoppingList.entrySet()) {
                            bufferedWriter.write(entry.getKey() + " x" + entry.getValue());
                            bufferedWriter.newLine();
                        }
                        bufferedWriter.close();
                        output.accept("Shopping list created!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;
                }

                case "exit":{
                    return;
                }
            }
        }

    }
}
