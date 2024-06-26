import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        String createIngredientsTableSQL = "CREATE TABLE IF NOT EXISTS ingredients (" +
                "ingredient VARCHAR(255)," +
                "ingredient_id SERIAL PRIMARY KEY," +
                "meal_id INT," +
                "FOREIGN KEY (meal_id) REFERENCES meals(meal_id)" +
                ")";
        statement.executeUpdate(createIngredientsTableSQL);

        String createPlanTableSQL = "CREATE TABLE IF NOT EXISTS plan (" +
                "day VARCHAR(255)," +
                "meal_id INT," +
                "category VARCHAR(255)," +
                "day_id SERIAL PRIMARY KEY," +
                "FOREIGN KEY (meal_id) REFERENCES meals(meal_id)" +
                ")";
        statement.executeUpdate(createPlanTableSQL);
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
    public String getMealsByCategory(String category) {
        StringBuilder result = new StringBuilder();

        try {

            String selectMealsSQL = "SELECT meal FROM meals WHERE category = ?";
            PreparedStatement statement = connection.prepareStatement(selectMealsSQL);
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();

            boolean foundMeals = false;

            while (resultSet.next()) {
                String meal = resultSet.getString("meal");

                String selectIngredientsSQL = "SELECT ingredient FROM ingredients WHERE meal_id IN (SELECT meal_id FROM meals WHERE meal = ?)";
                PreparedStatement ingredientStatement = connection.prepareStatement(selectIngredientsSQL);
                ingredientStatement.setString(1, meal);
                ResultSet ingredientResultSet = ingredientStatement.executeQuery();

                result.append("Category: ").append(category).append("\n");
                result.append("Name: ").append(meal).append("\n");

                result.append("Ingredients:\n");
                boolean foundIngredients = false;
                while (ingredientResultSet.next()) {
                    String ingredient = ingredientResultSet.getString("ingredient");
                    result.append(ingredient).append("\n");
                    foundIngredients = true;
                }
                if (!foundIngredients) {
                    result.append("No ingredients found.\n");
                }

                result.append("\n");

                ingredientStatement.close();
                foundMeals = true;
            }

            if (!foundMeals) {
                return "No meals found in category: " + category;
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred while fetching meals by category.";
        }

        return result.toString();
    }

    public String[] getMealArrByCategory(String category) {
        List<String> mealsList = new ArrayList<>();

        try {
            String selectMealsSQL = "SELECT meal FROM meals WHERE category = ?";
            PreparedStatement statement = connection.prepareStatement(selectMealsSQL);
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String meal = resultSet.getString("meal");
                mealsList.add(meal);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{};
        }

        String[] result = new String[mealsList.size()];
        return mealsList.toArray(result);
    }

    public void savePlanMeal(String day, String category, String meal){
        try {

            if(day=="Monday" && category=="breakfast"){
                String deleteAllSQL = "DELETE FROM plan" ;
                connection.prepareStatement(deleteAllSQL).executeUpdate();
            }
            int meal_id = -1;
            String selectMealIdSQL = "SELECT meal_id FROM meals WHERE meal = ?";
            PreparedStatement statement = connection.prepareStatement(selectMealIdSQL);
            statement.setString(1, meal);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                meal_id = resultSet.getInt("meal_id");
            }
            statement.close();

            String insertMealSQL = "INSERT INTO plan (day, category, meal_id) VALUES (?, ?,?)";
            PreparedStatement planStatement = connection.prepareStatement(insertMealSQL, Statement.RETURN_GENERATED_KEYS);
            planStatement.setString(1, day);
            planStatement.setString(2, category);
            planStatement.setInt(3, meal_id);
            planStatement.executeUpdate();
            planStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String[] getPlanIngredients() {
        try {
            String countRecordsSQL = "SELECT COUNT(*) AS count FROM plan";
            PreparedStatement countStatement = connection.prepareStatement(countRecordsSQL);
            ResultSet countResultSet = countStatement.executeQuery();
            if (countResultSet.next()) {
                int count = countResultSet.getInt("count");
                if (count < 21) {
                    return new String[]{"Plan your week first!"};
                }
            }

            List<String> ingredientsList = new ArrayList<>();

                String selectIngredientsSQL = "SELECT ingredient FROM ingredients INNER JOIN plan ON ingredients.meal_id = plan.meal_id";
                PreparedStatement statement = connection.prepareStatement(selectIngredientsSQL);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String ingredient = resultSet.getString("ingredient");
                    ingredientsList.add(ingredient);
                }

                statement.close();

            String[] ingredientsArray = new String[ingredientsList.size()];
            return ingredientsList.toArray(ingredientsArray);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
