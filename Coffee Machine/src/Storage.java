import java.util.HashMap;
import java.util.Map;

public class Storage {
    public int water;
    public int milk;
    public int coffeeBeans;
    public int money;

    public int cups;



    public void updateAmount(){
        water = 400;
        milk = 540;
        coffeeBeans = 120;
        money = 550;
        cups = 9;
    }

    public Storage() {
        updateAmount();
    }

    public void addAmount(int water, int milk, int coffeeBeans, int cups) {
        if(water<0 || milk<0 || coffeeBeans<0 || cups<0) return;
        this.water += water;
        this.milk += milk;
        this.coffeeBeans += coffeeBeans;
        this.cups += cups;
    }
    public void takeMoney (){
            this.money =0;
    }
    public String getAmount(){
        return ("The coffee machine has:\n" +
                +water+" ml of water\n" +
                +milk+" ml of milk\n" +
                +coffeeBeans+" g of coffee beans\n" +
                +cups+" disposable cups\n" +
                "$"+money+" of money\n");
    }
    public Map<String,Integer> takeIngredient(Product product){
        Map<String, Integer> productMap = product.getProperties();
        Map<String, Integer> notEnoughMap = new HashMap<>();
        if(productMap.get("WATER")>water){
            notEnoughMap.put("WATER", productMap.get("WATER"));

        }
        if (productMap.get("MILK")>milk){
            notEnoughMap.put("MILK", productMap.get("MILK"));
        }
        if (productMap.get("BEANS")>coffeeBeans){
            notEnoughMap.put("BEANS", productMap.get("BEANS"));


        }if (productMap.get("CUPS")>cups){
            notEnoughMap.put("CUPS", productMap.get("CUPS"));
        }
        if(notEnoughMap.isEmpty()){
            water-=productMap.get("WATER");
            milk-=productMap.get("MILK");
            coffeeBeans-=productMap.get("BEANS");
            cups-=productMap.get("CUPS");
            money+=productMap.get("COST");
        }
        return notEnoughMap;
    }
}
