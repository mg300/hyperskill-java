import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        File file = new File(path);
        System.out.println(path);
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String str;
            while ((str =buffer.readLine())!=null){
                System.out.println(str);
            }
        }catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input / output error: " + e.getMessage());
        }

    }
}