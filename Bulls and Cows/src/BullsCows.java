import java.util.Arrays;
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
        output.accept("Input the length of the secret code(1-10): ");
        secretLength = scanner.nextInt();
        String secret = gameLogic.generateSecret(secretLength);
        output.accept("The secret is prepared: "+secret+" (0-9)");
        output.accept("Okay, let's start a game!");
        play();

    }

    public void play(){
        int turn = 1;
        while(true){
            output.accept("Turn" + turn+": ");
            turn++;
            String guessNumbersString = scanner.next();
            int[] guessNumbersArr = String.valueOf(guessNumbersString)
                    .chars()
                    .map(Character::getNumericValue)
                    .toArray();
            if(guessNumbersArr.length != secretLength) {
                output.accept("Secret and guess must have the same length");
                continue;
            }
            int cows = gameLogic.countCows(guessNumbersArr);
            int bulls = gameLogic.countBulls(guessNumbersArr);
            output.accept(("Cows: "+cows+" ") + ("Bulls: "+bulls));
            if(bulls==secretLength) {
                output.accept("Congratulations! You guessed the secret code!\nThe code is: "+ Arrays.toString(guessNumbersArr));
                break;
            }

        }
    }

}
