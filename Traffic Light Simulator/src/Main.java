import java.util.Scanner;
import java.util.function.Consumer;

public class Main {


    public static void main(String[] args)  {
        Config config = Config.getInstance();
        Consumer<String> output = System.out::println;
        Scanner scanner = new Scanner(System.in);
        InputManager inputManager = new InputManager(scanner,output,config);
        config.setRoadsNum(inputManager.readRoads());
        config.setIntervals(inputManager.readIntervals());
        inputManager.showMenuReadInput();



}}
