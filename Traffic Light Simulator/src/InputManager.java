import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Consumer;

public class InputManager implements  TimeObserver{
    final Scanner scanner;
    final Stopwatch time;

    final Consumer<String> output;
    final InputValidator validator;

    final Config config;

    public InputManager(Scanner scanner, Consumer<String> output, Config config) {
        this.scanner = scanner;
        this.output = output;
        this.config = config;
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
        int[] times = updateRoadsTimers(elapsedTime);
        Iterator<String> iterator = config.getRoads().iterator();
        for(int i=0; i<times.length; i++){
            if(i==config.openIndex){
                output.accept(iterator.next() +" will be open for "+times[i]+".");
            }else{
                output.accept(iterator.next() +" will be close for "+times[i]+".");

            }
        }
    }

    public int[] updateRoadsTimers(long elapsedTime){
        int interval = config.getIntervals();
        int openIndex = config.openIndex;
        for (int i=0; i<config.getRoads().size();i++){
            config.times[i]-=1;
            if(config.times[i]==0 && openIndex==i){
                config.times[i]=interval*(config.times.length-1);

            }
            if(config.times[i]==0 && openIndex!=i){
                config.times[i]=interval;
                if(openIndex==config.times.length-1){
                    config.openIndex=0;
                }else{
                config.openIndex++;
                }
            }
        }

        return config.times;
    }
    public void addRoad(){
        validator.clearOutput();
        output.accept("Input road name: ");
        String road = validator.getString();
        boolean result=config.addRoad(road);
        if (result){
            output.accept("Road added");
        }else{
            output.accept("Queue is full");
        }

    }
    public void deleteRoad(){
        boolean result = config.deleteRoad();
        if (result){
            output.accept("Road deleted");
        }else{
            output.accept("Queue is empty");
        }
        scanner.nextLine();
    }
    public void showMenuReadInput(){
        while (true){
            int option = displayReadMenu();
            switch (option){
                case 1:
                    this.addRoad();
                    break;
                case 2:
                    this.deleteRoad();
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
