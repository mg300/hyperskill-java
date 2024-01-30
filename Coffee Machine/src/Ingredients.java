import java.util.Arrays;

public class Ingredients {
    private int water;
    private int milk;
    private int coffeeBeans;

    public Ingredients(int water, int milk, int coffeeBeans) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
    }
    public int calcCups(){
        int fromWater = water/250;
        int fromMilk = milk/50;
        int fromBeans = coffeeBeans/15;
        int[] ingArr = {fromWater, fromMilk, fromBeans};
        Arrays.sort(ingArr);
        int result = ingArr[0];
        return result;
    }

}
