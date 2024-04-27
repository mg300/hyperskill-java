public interface Validator {
    <T> void setPossibleInputs(T[] elements);
    void setErrorMessage(String errorMessage);
    void setInputMessage(String inputMessage);
    void setMinValue(int minValue);
    void setMaxValue(int maxValue);
    int getInt();
    String getString();
    String[] getStrings();
    void clearOutput();
    void displayMeal(Meal meal);
}
