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
            this.initializeDB();
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
        Statement statement = connection.createStatement();
        String createMealsTableSQL = "CREATE TABLE IF NOT EXISTS meals (" +
                "category VARCHAR(255)," +
                "meal VARCHAR(255)," +
                "meal_id SERIAL PRIMARY KEY" +
                ")";
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
    public void saveMeal(Meal meal) {
        try {
            String insertMealSQL = "INSERT INTO meals (category, meal) VALUES (?, ?)";
            PreparedStatement mealStatement = connection.prepareStatement(insertMealSQL, Statement.RETURN_GENERATED_KEYS);
            mealStatement.setString(1, meal.getCategory());
            mealStatement.setString(2, meal.getName());
            mealStatement.executeUpdate();

            ResultSet generatedKeys = mealStatement.getGeneratedKeys();
            int mealId = -1;
            if (generatedKeys.next()) {
                mealId = generatedKeys.getInt(("meal_id"));
            }

            for (String ingredient : meal.getIngredients()) {
                String insertIngredientSQL = "INSERT INTO ingredients (ingredient, meal_id) VALUES (?, ?)";
                PreparedStatement ingredientStatement = connection.prepareStatement(insertIngredientSQL);
                ingredientStatement.setString(1, ingredient);
                ingredientStatement.setInt(2, mealId);
                ingredientStatement.executeUpdate();
                ingredientStatement.close();
            }

            mealStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getFormattedMeals() {
        StringBuilder formattedMeals = new StringBuilder();

        try {
            String selectMealsSQL = "SELECT category, meal FROM meals";
            PreparedStatement statement = connection.prepareStatement(selectMealsSQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String category = resultSet.getString("category");
                String meal = resultSet.getString("meal");

                formattedMeals.append("Category: ").append(category).append("\n");
                formattedMeals.append("Name: ").append(meal).append("\n");

                String selectIngredientsSQL = "SELECT ingredient FROM ingredients WHERE meal_id IN (SELECT meal_id FROM meals WHERE meal = ?)";
                PreparedStatement ingredientStatement = connection.prepareStatement(selectIngredientsSQL);
                ingredientStatement.setString(1, meal);
                ResultSet ingredientResultSet = ingredientStatement.executeQuery();

                formattedMeals.append("Ingredients:\n");
                while (ingredientResultSet.next()) {
                    String ingredient = ingredientResultSet.getString("ingredient");
                    formattedMeals.append(ingredient).append("\n");
                }
                formattedMeals.append("\n");

                ingredientStatement.close();
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return formattedMeals.toString();
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
