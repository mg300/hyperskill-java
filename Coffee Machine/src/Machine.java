import java.util.Scanner;
import java.util.function.Consumer;

public class Machine {
    final private Scanner scanner;
    final private Consumer<String> output;
    private int cups;
    public Machine(Scanner scanner, Consumer<String> output){
        this.scanner = scanner;
        this.output=output;
        powerOn();
    }
    public void powerOn(){
        output.accept("Write how many ml of water the coffee machine has: ");
        int water = scanner.nextInt();
        output.accept("Write how many ml of milk the coffee machine has: ");
        int milk = scanner.nextInt();
        output.accept("Write how many grams of coffee beans the coffee machine has: ");
        int beans= scanner.nextInt();
        output.accept("Write how many cups of coffee you will need: ");
        cups = scanner.nextInt();
        Ingredients ingredients = new Ingredients(water,milk,beans);
        int canMade = ingredients.calcCups();
        if(canMade<cups){
            output.accept("No, I can make only "+canMade+" cup(s) of coffee");
        } else if (canMade==cups) {
            output.accept("Yes, I can make that amount of coffee");
        }
        else {
            output.accept("Yes, I can make that amount of coffee (and even "+(canMade-cups)+" more than that)");
        }

    }

}
