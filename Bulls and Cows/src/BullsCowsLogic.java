import java.util.Arrays;

public class BullsCowsLogic {
    private int[] secret;

    public String generateSecret(int secretLength){

        if (secretLength > 10 || secretLength<1) {
            throw new IllegalArgumentException("Secret is too long");
        }
        int[] code = new int[secretLength];
        Arrays.fill(code,-1);
        for(int i=0; i<secretLength; i++){
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
        return "*".repeat(code.length);
    }
    public int countCows(int[] guess){
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
    public int countBulls(int[] guess){
        int bulls = 0;
        for(int i=0; i<secret.length; i++){
            if(secret[i] == guess[i]){
                bulls++;
            }
        }
        return bulls;
    }
}
