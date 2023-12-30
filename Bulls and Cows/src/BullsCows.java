import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class BullsCows {

    private final Scanner scanner;

    private final Consumer<String> output;

    private int[] secret;


    public BullsCows(Scanner scanner, Consumer<String> output) {
        this.scanner = scanner;
        this.output = output;
        startGame();
    }

    public void startGame(){
        output.accept("Input the length of the secret code(1-10): ");
        int length = scanner.nextInt();
        setSecret(length);
        output.accept("The secret is prepared: "+generateStars(length)+" (0-9)");
        output.accept("Okay, let's start a game!");

    }
    private void setSecret(int length){

        if(length>10){
            throw new IllegalArgumentException("wrong length");
        }
        int[] code = new int[length];
        Arrays.fill(code,-1);
        for(int i=0; i<length; i++){
            while (true) {
                final int randomDec = (int) (Math.random() * 10);
                if (Arrays.stream(code).noneMatch(c -> c == randomDec)) {
                    code[i] = randomDec;
                    break;
                }
            }
        }
        secret = code;
    }

    private void play(){
    }

    private int countCows(){
        return 0;
    }
    private int countBulls(){

        return 0;
    }
    private void displayResults(){

    }
    private String generateStars(int length){

        StringBuilder starsString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            starsString.append("*");
        }

        return starsString.toString();
    }
}
