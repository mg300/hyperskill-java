import java.sql.*;

public class DBconnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "secret2020";
    private static Connection connection = null;
    private static DBconnection instance = null;

    private DBconnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(true);
            initializeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static synchronized DBconnection getInstance() {
        if (instance == null) {
            instance = new DBconnection();
        }
        return instance;
    }
    public void initializeDB() throws SQLException {
        String createMealsTableSQL = "CREATE TABLE IF NOT EXISTS meals (" +
                "category VARCHAR(255)," +
                "meal VARCHAR(255)," +
                "meal_id SERIAL PRIMARY KEY" +
                ")";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createMealsTableSQL);

        // Tworzenie tabeli ingredients
        String createIngredientsTableSQL = "CREATE TABLE IF NOT EXISTS ingredients (" +
                "ingredient VARCHAR(255)," +
                "ingredient_id SERIAL PRIMARY KEY," +
                "meal_id INT," +
                "FOREIGN KEY (meal_id) REFERENCES meals(meal_id)" +
                ")";
        statement.executeUpdate(createIngredientsTableSQL);
        statement.close();
    }
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Połączenie z bazą danych zostało zamknięte.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
