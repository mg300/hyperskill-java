import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int pencilsQuantity = scanner.nextInt();
        System.out.println("Who will be the first (John, Jack):");
        String[] players = {"John", "Jack"};
        scanner.nextLine();
        String firstPlayerName;
        int firstPlayerIndex;
        while(true){
            firstPlayerName = scanner.nextLine();
            firstPlayerName=firstPlayerName.substring(0, 1).toUpperCase() + firstPlayerName.substring(1);
            firstPlayerIndex= Arrays.binarySearch(players, firstPlayerName);
            if(firstPlayerIndex<0) {
                System.out.println("Name is not allowed");
                continue;
            };
            break;
        }

            while(pencilsQuantity>0){
                System.out.println("|".repeat(pencilsQuantity));
                System.out.println(players[firstPlayerIndex]+"'s turn:");
                int pencils = scanner.nextInt();
                if(firstPlayerIndex==0){
                    firstPlayerIndex=1;
                }else {
                    firstPlayerIndex=0;
                }
                pencilsQuantity-=pencils;
        }

    }
}
