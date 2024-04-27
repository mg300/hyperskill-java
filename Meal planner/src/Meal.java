import java.util.Arrays;

public class Meal {
    private final String name;
    private final String[] ingredients;

    private final String category;
    public Meal(String name, String category, String[] ingredients) {
        this.name = name;
        String[] categories = new String[]{"breakfast", "dinner", "lunch"};
        if(Arrays.asList(categories).contains(category)){
        this.category = category;

        }else{
            throw new IllegalArgumentException("Invalid category: " + category);
        }
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getCategory() {
        return category;
    }
}
