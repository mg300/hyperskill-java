import java.util.List;

public class InputOutputManager {
    private final Validator validator;

    public InputOutputManager(Validator validator) {
        this.validator=validator;
    }
    public String showMenuGetOption(){
        validator.setPossibleInputs(new String[] {"add","show","exit"});
        validator.setInputMessage("What would you like to do (add, show, exit)?");
        validator.setErrorMessage("Choose between \"add\", \"show\" and \"exit\"");
        return validator.getString();
    }
    public String getMealType(){
        validator.setPossibleInputs(new String[] {"breakfast","lunch","dinner"});
        validator.setInputMessage("Which meal do you want to add (breakfast, lunch, dinner)?");
        validator.setErrorMessage("Choose between \"breakfast\", \"lunch\" and \"dinner\"");
        return validator.getString();
    }
    public String getMealName(){
        validator.setInputMessage("Input the meal's name:?");
        return validator.getString();
    }
    public String[] getIngredients(){
        validator.setInputMessage("Input the ingredients:");
        return validator.getStrings();
    }
    public void showMeals(List<Meal> meals){
        for (Meal meal : meals){
            validator.displayMeal(meal);
        }
    }
}
