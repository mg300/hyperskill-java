import java.util.Random;

public class BullsCowsLogic {
    private char[] secret;

//    public String generateSecret(int secretLength){
//
//        if (secretLength > 10 || secretLength<1) {
//            throw new IllegalArgumentException("Secret is too long");
//        }
//        int[] code = new int[secretLength];
//        for(int i=0; i<secretLength; i++){
//            while (true) {
//                final int randomDec = (int) (Math.random() * 10);
//                if (Arrays.stream(code).noneMatch(c -> c == randomDec)) {
//                    code[i] = randomDec;
//                    break;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(code));
//
//        secret = code;
//        return "*".repeat(code.length);
//    }
    public String generateSecret(int secretLength, int range){
        if (secretLength>range) {
            throw new IllegalArgumentException("Range can not be grater than secret");
        }
        String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] code = new char[secretLength];
        for (int i = 0; i < secretLength; i++) {
            outerLoop:
            while(true){
                Random random = new Random();
                int randomIndex = random.nextInt(range);
                char character = CHARACTERS.charAt(randomIndex);
                for (char chr : code){
                    if(chr == character){
                        continue outerLoop;
                    }
                }
                code[i] = character;
                break;

            }

        }
        System.out.println(code);
        secret=code;

        return "*".repeat(secretLength)+" (0-"+(range>9 ? '9' :CHARACTERS.charAt(range-1))+")"+(range==11 ? "(A-A)": "" )+(range>11 ? "(A-"+CHARACTERS.charAt(range-1)+")":"");
    }
    public int countCows(char[] guess){
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
    public int countBulls(char[] guess){
        int bulls = 0;
        for(int i=0; i<secret.length; i++){
            if(secret[i] == guess[i]){
                bulls++;
            }
        }
        return bulls;
    }
}
