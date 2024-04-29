import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws SQLException {
        Consumer<String> output = System.out::println;
        Scanner scanner = new Scanner(System.in);
        DBconnection connection = DBconnection.getInstance();
        MealPlanner mealPlanner = new MealPlanner(scanner,output,connection);
        connection.closeConnection();
    }
}