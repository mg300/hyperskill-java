import java.util.Scanner;
import java.util.function.Consumer;

public class CoffeeMachine implements Machine{

    final private Scanner scanner;
    final private Consumer<String> output;
    private CoffeeMachineStorage coffeeMachineStorage;
    public CoffeeMachine(Scanner scanner, Consumer<String> output){
        this.scanner = scanner;
        this.output=output;
        powerOn();
    }
    public void powerOn(){
        coffeeMachineStorage = new CoffeeMachineStorage();
        remaining();
        while (action()) {}
    }
    public void remaining(){
        output.accept(coffeeMachineStorage.getAmount());
    }
    public void fill(){
        output.accept("Write how many ml of water you want to add: ");
        int water = scanner.nextInt();
        output.accept("Write how many ml of milk you want to add: ");
        int milk = scanner.nextInt();
        output.accept("Write how many grams of coffee beans you want to add: ");
        int coffee = scanner.nextInt();
        output.accept("Write how many disposable cups you want to add: ");
        int cups = scanner.nextInt();
        coffeeMachineStorage.addAmount(water,milk,coffee,cups);



    }
    public void take(){
        output.accept("I gave you $"+ coffeeMachineStorage.money);
        coffeeMachineStorage.takeMoney();
    }
    public void buy(){
        output.accept("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        String coffee = switch (scanner.nextInt()) {
            case 1 -> "ESPRESSO";
            case 2 -> "LATTE";
            case 3 -> "CAPPUCCINO";
            default -> "";
        };
        String coffeeMess = coffeeMachineStorage.makeCoffee(Product.valueOf(coffee));
//        Map<String, Integer> notEnoughIngredients = coffeeMachineStorage.takeIngredient(Product.valueOf(coffee));
//        if(notEnoughIngredients.isEmpty()){
//
//            output.accept("I have enough resources, making you a coffee!");
//        }else {
//            notEnoughIngredients.forEach((key, value) -> System.out.println("Sorry, not enough " + key ));
//        }
        output.accept(coffeeMess);
    }
    public boolean action() {
        output.accept("Write action (buy, fill, take, remaining, exit): ");
        switch (scanner.next()) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                remaining();
                break;
            case "exit":
                return false;
        }
        return true;
    }
}
