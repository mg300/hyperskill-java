import java.util.HashMap;
import java.util.Map;

public class CoffeeMachineStorage implements Storage {


    private Map<String, Integer> ingredients;

    public int money;
    public int cups;

    public CoffeeMachineStorage() {
        ingredients = new HashMap<>();
        ingredients.put("WATER", 400);
        ingredients.put("MILK", 40);
        ingredients.put("BEANS", 120);
        money = 550;
        cups = 9;
    }

    public void addAmount(int water, int milk, int coffeeBeans, int cups) {
        if(water<0 || milk<0 || coffeeBeans<0 || cups<0) return;
        ingredients.merge("WATER", water, Integer::sum);
        ingredients.merge("MILK", milk, Integer::sum);
        ingredients.merge("COFFEE_BEANS", coffeeBeans, Integer::sum);
        this.cups += cups;
    }
    public void takeMoney (){
            this.money =0;
    }
    public String getAmount(){
        return ("The coffee machine has:\n" +
                ingredients.get("WATER")+" ml of water\n" +
                ingredients.get("MILK")+" ml of milk\n" +
                ingredients.get("COFFEE_BEANS")+" g of coffee beans\n" +
                cups+" disposable cups\n" +
                "$"+money+" of money\n");
    }

    private Map<String, Integer> getIngredientsIsNotEnough(Product product){
        Map<String, Integer> ingredientsNeeded = product.getProperties();
        Map<String, Integer> notEnoughMap = new HashMap<>();
        if(ingredientsNeeded.get("WATER")>ingredients.get("WATER")){
            notEnoughMap.put("WATER", ingredientsNeeded.get("WATER"));
        }
        if (ingredientsNeeded.get("MILK")>ingredients.get("MILK")){
            notEnoughMap.put("MILK", ingredientsNeeded.get("MILK"));
        }
        if (ingredientsNeeded.get("BEANS")>ingredients.get("BEANS")){
            notEnoughMap.put("BEANS", ingredientsNeeded.get("BEANS"));
        }
        if (1>cups){
            notEnoughMap.put("CUPS", ingredientsNeeded.get("CUPS"));
        }
        return notEnoughMap;
    }
    public String makeCoffee(Product product){
        Map<String, Integer> ingredientsNeeded = product.getProperties();
        Map<String,Integer> notEnoughIngredients = getIngredientsIsNotEnough(product);
        if(!notEnoughIngredients.isEmpty()){
            return "Sorry, not enough: "+ String.join(", ", notEnoughIngredients.keySet());
        }
        ingredients.merge("WATER", -ingredientsNeeded.get("WATER"), Integer::sum);
        ingredients.merge("MILK", -ingredientsNeeded.get("MILK"), Integer::sum);
        ingredients.merge("BEANS", -ingredientsNeeded.get("BEANS"), Integer::sum);
        money+=ingredientsNeeded.get("COST");
        cups--;
        return "Your "+product+" is ready";
    }
}
