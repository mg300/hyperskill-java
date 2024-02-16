import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String endMessage = "See you later";
        Scanner scanner = new Scanner(System.in);
        String insertedString="EXIT";
        while(true){
            System.out.println("Please enter the name of the habitat you would like to view: ");
            for (Habitat value : Habitat.values()) {
                System.out.print( value +", ");
            }
            System.out.print(" EXIT:\n");

            try{
                insertedString = scanner.next().toUpperCase();
                if(insertedString.equals("EXIT")) break;
                Habitat habitat = Habitat.valueOf(insertedString);
                System.out.println(habitat.getHabitatDescription());
            }catch (IllegalArgumentException e){
                System.out.println("Wrong name");
            }

        }
        System.out.println(endMessage);
        }
}