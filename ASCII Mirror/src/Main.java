import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        File file = new File(path);
        System.out.println(path);
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String str;
            while ((str =buffer.readLine())!=null){
                lines.add(str);
            }
        }catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input / output error: " + e.getMessage());
        }
         String maxLength = lines.stream()
                .max(Comparator.comparing(String::length))
                .orElse("");

        for(String line : lines){
            StringBuilder lineBuilder = new StringBuilder(line);
            while (lineBuilder.length()<maxLength.length()){
                lineBuilder.append(" ");
            }
            line = lineBuilder.toString();
            System.out.print(line);
            System.out.print(" | ");
            String mirrorLine = Mirror.mirrorLine(line);
            System.out.print(mirrorLine+"\n");
        }
    }

}

///Users/mateuszg/Desktop/hyperskill-java/ASCII Mirror/ASCII_Animals/MooFolder/MooFile.txt
