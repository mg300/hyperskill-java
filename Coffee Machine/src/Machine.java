import java.util.Scanner;
import java.util.function.Consumer;

public class Machine {
    final private Scanner scanner;
    final private Consumer<String> output;
    public Machine(Scanner scanner, Consumer<String> output){
        this.scanner = scanner;
        this.output=output;
        powerOn();
    }
    public void powerOn(){

        output.accept("""
                Starting to make a coffee
                Grinding coffee beans
                Boiling water
                Mixing boiled water with crushed coffee beans
                Pouring coffee into the cup
                Pouring some milk into the cup
                Coffee is ready!""");
    }

}
