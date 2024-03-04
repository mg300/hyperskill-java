import java.util.InputMismatchException;
import java.util.Scanner;

public class Reader {
    final private Scanner scanner;
    public Reader(){
        scanner = new Scanner(System.in);
    }
    public int getPlayerIndex(String[] players){
        int playerIndex;
          while(true){
              String name= scanner.nextLine();
              name=name.substring(0, 1).toUpperCase() + name.substring(1);
              playerIndex = -1;
              for (int i = 0; i < players.length; i++) {
                  if (players[i].equals(name)) {
                      playerIndex = i;
                      break;
                  }
              }
              if(playerIndex<0) {
                  System.out.println("Choose between 'John' and 'Jack'");
                  continue;
              };
              break;
      }
        return playerIndex;
    }
    public int readPencils(int pencilsQuantity){
        int pencils;
        while (true) {
            pencils = scanner.nextInt();
            if (pencils > pencilsQuantity) {
                System.out.println("Too many pencils were taken");
            } else if (pencils < 1 || pencils > 3) {
                System.out.println("Possible values: '1', '2' or '3'");
            } else {
                break;
            }
        }
        return pencils;
    }
    public int readNumeric(){
        int pencilsQuantity;
        while(true){
            try{
                pencilsQuantity = scanner.nextInt();
                scanner.nextLine();
                if(pencilsQuantity<=0){
                    System.out.println("The number of pencils should be positive");
                }else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("The number of pencils should be numeric");
                scanner.nextLine();
            }}

        return pencilsQuantity;
    }
}
