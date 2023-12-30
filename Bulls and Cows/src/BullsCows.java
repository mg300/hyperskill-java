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
        play();
        gameEnd();

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
        System.out.println(Arrays.toString(code));

        secret = code;
    }

    private void play(){
        int i = 1;
        int[] guessArr;
        while(true){
            output.accept("Turn" + i);
            String guessStr = scanner.nextLine();
            guessArr = String.valueOf(guessStr)
                    .chars()
                    .map(Character::getNumericValue)
                    .toArray();
            if(guessArr.length != secret.length)
                continue;
            i++;
            int cows = countCows(guessArr);
            int bulls = countBulls(guessArr);
            output.accept((cows>0 ? "Cows: "+cows+" " : "") + (bulls>0 ? "Bulls: "+bulls : ""));
            if(bulls==secret.length) break;
        }


    }

    private int countCows(int[] guess){
        int cows = 0;
        for(int i=0; i<secret.length; i++){
            for(int j=0; j< secret.length; j++){
                if(secret[i] != guess[i] && secret[i] == guess[j]){
                    cows++;
                }
            }
        }
        return cows;
    }
    private int countBulls(int[] guess){
        int bulls = 0;
        for(int i=0; i<secret.length; i++){
            if(secret[i] == guess[i]){
                bulls++;
            }
        }
        return bulls;
    }
    private void gameEnd(){
        StringBuilder out= new StringBuilder();
        for(int i : secret) {
            out.append(i);
        }

        output.accept("Congratulations! You guessed the secret code!\nThe code is: "+out);
    }
    private String generateStars(int length){
        return "*".repeat(length);
    }
}
