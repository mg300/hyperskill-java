public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        String[] players = {"John", "Jack"};

        System.out.println("How many pencils would you like to use:");
        int pencilsLeft = reader.readNumeric();
        System.out.println("Who will be the first (John, Jack):");

        int currentPlayer = reader.getPlayerIndex(players);
            while(pencilsLeft>0){
                System.out.println("|".repeat(pencilsLeft));
                System.out.println(players[currentPlayer]+"'s turn:");
                int pencils;
                if(currentPlayer==1){
                    pencils = Bot.takePencil(pencilsLeft);
                    System.out.println(pencils);
                }else{
                 pencils = reader.readPencils(pencilsLeft);
                }
                pencilsLeft-=pencils;
                currentPlayer = (currentPlayer == 0) ? 1 : 0;
        }
            System.out.println(players[currentPlayer]+" won");

    }
}
