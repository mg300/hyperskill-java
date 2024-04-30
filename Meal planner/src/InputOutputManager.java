public class InputOutputManager {
    private final Validator validator;

    public InputOutputManager(Validator validator) {
        this.validator=validator;
    }
    public String showMenuGetOption(){
        validator.setPossibleInputs(new String[] {"add","show","exit","plan","save"});
        validator.setInputMessage("What would you like to do (add, show, plan, save, exit)?");
        validator.setErrorMessage("Choose between \"add\", \"show\", \"plan\", \"save\" and \"exit\"");
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
    public String getMealForCategory(String day,String category, String[] meals){
        validator.setPossibleInputs(meals);
        validator.displayMealOptions(day,meals);
        validator.setInputMessage("Choose the "+category+" for "+day+" from the list above:");
        validator.setErrorMessage("This meal doesn’t exist. Choose a meal from the list above.");
        return validator.getString();
    }

}
