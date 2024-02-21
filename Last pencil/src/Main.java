public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        String[] players = {"John", "Jack"};

        System.out.println("How many pencils would you like to use:");
        int pencilsQuantity = reader.readNumeric();
        System.out.println("Who will be the first (John, Jack):");

        int currentPlayer = reader.getPlayerIndex(players);
            while(pencilsQuantity>0){
                System.out.println("|".repeat(pencilsQuantity));
                System.out.println(players[currentPlayer]+"'s turn:");
                int pencils = reader.readPencils(pencilsQuantity);
                pencilsQuantity-=pencils;
                currentPlayer = (currentPlayer == 0) ? 1 : 0;
        }
            System.out.println(players[currentPlayer]+" won");

    }
}
