public interface Storage {

    void addAmount(int water, int milk, int coffeeBeans, int cups);
    void takeMoney();
    String getAmount();
    String makeCoffee(Product product);
}
