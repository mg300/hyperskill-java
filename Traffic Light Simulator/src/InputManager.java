import java.util.Scanner;
import java.util.function.Consumer;

public class InputManager implements  TimeObserver{
    final Scanner scanner;
    final Stopwatch time;

    final Consumer<String> output;
    final InputValidator validator;

    public InputManager(Scanner scanner, Consumer<String> output) {
        this.scanner = scanner;
        this.output = output;
        validator = new InputValidator(scanner,output);
        time = new Stopwatch();
    }
    public int readRoads(){
        output.accept("Welcome to the traffic management system!");
        validator.setErrorMessage("Error! Incorrect Input. Try again: ");
        validator.setInputMessage("Input the number of roads: ");
        validator.setMinValue(1);
        validator.setMaxValue(100);
        return validator.getInput();
    }
    public int readIntervals(){
        validator.setInputMessage("Input the interval: ");
        validator.setMaxValue(1000);
        return validator.getInput();
    }
    public int displayReadMenu(){
        validator.clearOutput();
        validator.setErrorMessage("Incorrect option");
        validator.setInputMessage("Menu:\n"+
                "1. Add road\n" +
                "2. Delete road\n" +
                "3. Open system\n" +
                "0. Quit");
        validator.setMinValue(0);
        validator.setMaxValue(3);
        return validator.getInput();
    }
    public void updateSystemMenu(long elapsedTime){
        validator.clearOutput();
        output.accept("! "+elapsedTime/1000+"s. have passed since system startup !\n" +
                "! Number of roads: 1 !\n" +
                "! Interval: 1 !\n" +
                "! Press \"Enter\" to open menu !");


    }
    public void showMenuReadInput(){
        while (true){
            int option = displayReadMenu();
            switch (option){
                case 1:
                    System.out.println("Road added");
                    break;
                case 2:
                    System.out.println("Road deleted");
                    break;
                case 3:
                    time.start();
                    time.addObserver(this);
                    scanner.nextLine();
                    validator.subscribeEnterButton(this::closeSystem);
                    return;
                case 0:
                    System.out.println("Bye");
                    endProgram();
                    return;

            }
            output.accept("Click enter to continue...");
            scanner.nextLine();
            scanner.nextLine();
            validator.clearOutput();

        }
    }
    private void closeSystem(){
        time.removeObserver(this);
        showMenuReadInput();
    }
    private void endProgram(){
        time.stop();
    }
}
