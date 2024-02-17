import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Consumer;

public class BullsCows {

    private final Scanner scanner;

    private final Consumer<String> output;

    private int secretLength;

    private BullsCowsLogic gameLogic;


    public BullsCows(Scanner scanner, Consumer<String> output) {
        this.scanner = scanner;
        this.output = output;
        gameLogic = new BullsCowsLogic();
        startGame();

    }

    public void startGame(){
        output.accept("Input the length of the secret code(1-36): ");
        try{
            secretLength = scanner.nextInt();
            if (secretLength < 1 || secretLength > 36) {
                throw new IllegalArgumentException("Secret length is out of range (1-36)");
            }
            output.accept("Input the range of the secret code("+secretLength+"-36): ");
            int range = scanner.nextInt();
            if(range>36 || range <secretLength){
                throw new IllegalArgumentException("Secret length is out of range ("+secretLength+"-36)");
            }
            String secret = gameLogic.generateSecret(secretLength, range);

            output.accept("The secret is prepared: "+secret);
            output.accept("Okay, let's start a game!");
            play();

        }catch (InputMismatchException e) {
            System.out.println("Input mismatch!");
            System.out.println("Game over... :(");

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Game over... :(");

        }





    }

    public void play(){
        int turn = 1;
        while(true){
            output.accept("Turn" + turn+": ");
            turn++;
            String guessNumbersString = scanner.next();
            char[] guessCharsArr = guessNumbersString.toUpperCase().toCharArray();
            if(guessCharsArr.length != secretLength) {
                output.accept("Secret and guess must have the same length");
                continue;
            }
            int cows = gameLogic.countCows(guessCharsArr);
            int bulls = gameLogic.countBulls(guessCharsArr);
            output.accept(("Cows: "+cows+" ") + ("Bulls: "+bulls));
            if(bulls==secretLength) {
                output.accept("Congratulations! You guessed the secret code!\nThe code is: "+ guessNumbersString);
                break;
            }

        }
    }

}
