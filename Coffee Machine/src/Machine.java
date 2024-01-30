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
        output.accept("Write how many cups of coffee you will need: ");
        cups = scanner.nextInt();
        output.accept("For 25 cups of coffee you will need: \n"+cups*200 +" ml of water\n"+cups*50 +" ml of milk\n"+cups*15 +" g of coffe beans\n");

    }

}
