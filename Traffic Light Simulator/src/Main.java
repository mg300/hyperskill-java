public class Main {
    public static void main(String[] args) {

    System.out.println("Welcome to the traffic management system!");
    InputValidator validator = new InputValidator();
    validator.setErrorMessage("Error! Incorrect Input. Try again: ");
    validator.setInputMessage("Input the number of roads: ");
    validator.setMinValue(1);
    validator.setMaxValue(10);
    int numOfRoads = validator.getInput();
    validator.setInputMessage("Input the interval: ");
    validator.setMaxValue(1000);
    int numOfInterval = validator.getInput();
    validator.clearOutput();
    validator.setErrorMessage("Incorrect option");
    validator.setInputMessage("Menu:\n"+
            "1. Add road\n" +
            "2. Delete road\n" +
            "3. Open system\n" +
            "0. Quit");
    validator.setMinValue(0);
    validator.setMaxValue(4);
    while (true){
        int option = validator.getInput();
        validator.clearOutput();
        switch (option){
            case 1:
                System.out.println("Road added");
                break;
            case 2:
                System.out.println("Road deleted");
                break;
            case 3:
                System.out.println("System opened");
                break;
            case 0:
                System.out.println("Bye");
                return;

        }
    }

}}
