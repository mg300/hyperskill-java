import java.util.Scanner;

public class InputValidator {
    Scanner scanner = new Scanner(System.in);
    String errorMessage;
    String inputMessage;
    int minValue;
    int maxValue;


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getInput(){
        int choice;
            while (true) {
                System.out.println(inputMessage);
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if(choice>=minValue && choice<=maxValue){
                        return choice;
                    }
                    else{
                        System.out.println(errorMessage);
                    }
                }
                else{
                    scanner.next();
                    System.out.println(errorMessage);
                }
            }
    }
}
